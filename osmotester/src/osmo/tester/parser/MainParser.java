package osmo.tester.parser;

import osmo.common.Randomizer;
import osmo.common.Logger;
import osmo.tester.OSMOConfiguration;
import osmo.tester.annotation.*;
import osmo.tester.generator.testsuite.TestSuite;
import osmo.tester.model.FSM;
import osmo.tester.model.ModelFactory;
import osmo.tester.model.Requirements;
import osmo.tester.model.TestModels;
import osmo.tester.model.data.SearchableInput;
import osmo.tester.parser.annotation.*;
import osmo.tester.parser.annotation.TestStepParser;
import osmo.tester.parser.field.RandomizerParser;
import osmo.tester.parser.field.RequirementsParser;
import osmo.tester.parser.field.SearchableInputParser;
import osmo.tester.parser.field.TestSuiteParser;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The main parser that takes the given model object and parses it for specific registered annotations,
 * passes these to specific {@link AnnotationParser} implementations to update the {@link osmo.tester.model.FSM} 
 * representation according to the information for the specific annotation.
 *
 * @author Teemu Kanstren
 */
public class MainParser {
  private static final Logger log = new Logger(MainParser.class);
  /** Key = Annotation type, Value = The parser object for that annotation. */
  private final Map<Class<? extends Annotation>, AnnotationParser> annotationParsers = new LinkedHashMap<>();
  /** Key = Annotation type, Value = The parser object for that annotation. */
  private final Map<Class, AnnotationParser> fieldParsers = new LinkedHashMap<>();
  /** Generator configuration, can have also impact on parsing, mainly if method based naming is used. */
  private final OSMOConfiguration config;

  public MainParser(OSMOConfiguration config) {
    this.config = config;
    //we set up the parser objects for the different annotation types
    annotationParsers.put(TestStep.class, new TestStepParser());
    annotationParsers.put(Guard.class, new GuardParser());
    annotationParsers.put(LastStep.class, new LastStepParser());
    annotationParsers.put(AfterTest.class, new AfterTestParser());
    annotationParsers.put(BeforeTest.class, new BeforeTestParser());
    annotationParsers.put(AfterSuite.class, new AfterSuiteParser());
    annotationParsers.put(BeforeSuite.class, new BeforeSuiteParser());
    annotationParsers.put(Pre.class, new PreParser());
    annotationParsers.put(BeforeStep.class, new PreParser());
    annotationParsers.put(Post.class, new PostParser());
    annotationParsers.put(AfterStep.class, new PostParser());
    annotationParsers.put(EndCondition.class, new EndConditionParser());
    annotationParsers.put(OnError.class, new OnErrorParser());
    annotationParsers.put(CoverageValue.class, new CoverageValueParser());
    annotationParsers.put(Variable.class, new VariableParser());
    annotationParsers.put(ExplorationEnabler.class, new ExplorationEnablerParser());
    annotationParsers.put(GenerationEnabler.class, new GenerationEnablerParser());
    annotationParsers.put(Group.class, new GroupParser());
    annotationParsers.put(Description.class, new DescriptionParser());
    
    fieldParsers.put(SearchableInput.class, new SearchableInputParser());
    fieldParsers.put(Requirements.class, new RequirementsParser());
    fieldParsers.put(TestSuite.class, new TestSuiteParser());
    fieldParsers.put(Randomizer.class, new RandomizerParser());
  }

  /**
   * Initiates parsing the given model object for the annotations that define the test model elements.
   *
   * @param seed Randomization seed to initialize parsed objects.
   * @param factory Factory to create the model objects to be parsed.
   * @param suite Test suite to initialize parsed objects.
   * @return The model structure with references to the object instances.
   */
  public ParserResult parse(long seed, ModelFactory factory, TestSuite suite) {
    log.d("parsing");
    FSM fsm = new FSM();
    ParserResult result = new ParserResult(fsm);
    ParserParameters parameters = new ParserParameters();
    parameters.setSuite(suite);
    parameters.setSeed(seed);
    parameters.setConfig(config);
    StringBuilder errors = new StringBuilder();
    TestModels models = new TestModels();
    factory.createModelObjects(models);
    if (models.size() == 0) {
      errors.append("No model objects given. Cannot generate anything.\n");
    }
    for (ModelObject mo : models.getModels()) {
      parameters.reset();
      String prefix = mo.getPrefix();
      parameters.setPrefix(prefix);
      Object obj = mo.getObject();
      parameters.setModel(obj);
      //first we parse generic annotations from class level
      parseClass(result, parameters, errors);
      //next we check any annotated fields that are relevant
      parseFields(result, parameters, errors);
      //finally we check any annotated methods that are relevant
      parseMethods(result, parameters, errors);
    }
    //finally we check that the generated FSM itself is valid
    fsm.checkFSM(errors);
    result.postProcess();
    return result;
  }

  private void parseClass(ParserResult result, ParserParameters parameters, StringBuilder errors) {
    Class clazz = parameters.getModelClass();
    Annotation[] annotations = clazz.getAnnotations();
    for (Annotation annotation : annotations) {
      Class<? extends Annotation> annotationClass = annotation.annotationType();
      log.d("class annotation:" + annotationClass);
      AnnotationParser parser = annotationParsers.get(annotationClass);
      if (parser == null) {
        //unsupported annotation (e.g. for some completely different tool)
        continue;
      }
      log.d("parser:" + parser);
      //set the annotation itself as a parameter to the used parser object
      parameters.setAnnotation(annotation);
      //and finally parse it
      parser.parse(result, parameters, errors);
    }
  }
  /**
   * Parse the relevant annotated fields and pass these to correct {@link AnnotationParser} objects.
   *
   * @param result The parse results will be provided here.
   * @return A string listing all found errors.
   */
  private void parseFields(ParserResult result, ParserParameters parameters, StringBuilder errors) {
    Object obj = parameters.getModel();
    //first we find all declared fields of any scope and type (private, protected, ...)
    Collection<Field> fields = getAllFields(obj.getClass());
    log.d("fields " + fields.size());
    //now we loop through all fields defined in the model object
    for (Field field : fields) {
      log.d("field:" + field);
      //set the field to be accessible from the parser objects
      parameters.setField(field);
      Annotation[] annotations = field.getAnnotations();
      parameters.setFieldAnnotations(annotations);
      //loop through all defined annotations for each field
      for (Annotation annotation : annotations) {
        Class<? extends Annotation> annotationClass = annotation.annotationType();
        log.d("field annotation:" + annotationClass);
        AnnotationParser parser = annotationParsers.get(annotationClass);
        if (parser == null) {
          //unsupported annotation (e.g. for some completely different tool)
          continue;
        }
        log.d("parser:" + parser);
        //set the annotation itself as a parameter to the used parser object
        parameters.setAnnotation(annotation);
        //and finally parse it
        parser.parse(result, parameters, errors);
      }
      //parse specific types of fields, without annotations (searchableinput)
      parseField(field, result, parameters, errors);
    }
  }

  private void parseField(Field field, ParserResult result, ParserParameters parameters, StringBuilder errors) {
    log.d("parsefield");
    Class fieldClass = field.getType();
    for (Class parserType : fieldParsers.keySet()) {
      if (parserType.isAssignableFrom(fieldClass)) {
        AnnotationParser fieldParser = fieldParsers.get(parserType);
        if (fieldParser != null) {
          log.d("field parser invocation:" + parameters);
          fieldParser.parse(result, parameters, errors);
        }
      }
    }
  }

  public static Collection<Field> getAllFields(Class clazz) {
    Class<?> superclass = clazz.getSuperclass();
    Collection<Field> fields = new ArrayList<>();
    if (superclass != null) {
      fields.addAll(getAllFields(superclass));
    }
    Collections.addAll(fields, clazz.getDeclaredFields());
    return fields;
  }

  /**
   * Parse the relevant annotated methods and pass these to correct {@link AnnotationParser} objects.
   *
   * @param result This is where the parsing results are given.
   * @return String representing any errors encountered.
   */
  private void parseMethods(ParserResult result, ParserParameters parameters, StringBuilder errors) {
    Object obj = parameters.getModel();
    //first we get all methods defined in the test model object (also all scopes -> private, protected, ...)
    Collection<Method> methods = getAllMethods(obj.getClass());
    //there are always some methods inherited from java.lang.Object so we checking them here is pointless. FSM.check will do it
    log.d("methods " + methods.size());
    //loop through all the methods defined in the given object
    for (Method method : methods) {
      log.d("method:" + method);
      parameters.setMethod(method);
      Annotation[] annotations = method.getAnnotations();
      //check all annotations for supported ones, use the given object to process them
      for (Annotation annotation : annotations) {
        Class<? extends Annotation> annotationClass = annotation.annotationType();
        log.d("class:" + annotationClass);
        AnnotationParser parser = annotationParsers.get(annotationClass);
        if (parser == null) {
          //unsupported annotation (e.g. for some completely different aspect)
          continue;
        }
        log.d("parser:" + parser);
        //set the annotation itself as a parameter to the used parser object
        parameters.setAnnotation(annotation);
        //and finally parse it
        parser.parse(result, parameters, errors);
      }
    }
  }

  private Collection<Method> getAllMethods(Class clazz) {
    Class<?> superclass = clazz.getSuperclass();
    List<Method> methods = new ArrayList<>();
    if (superclass != null) {
      methods.addAll(getAllMethods(superclass));
    }
    Collections.addAll(methods, clazz.getMethods());
    //sort them by names and hope for a more deterministic result..
    Collections.sort(methods, (o1, o2) -> o1.getName().compareTo(o2.getName()));
    return methods;
  }
}
