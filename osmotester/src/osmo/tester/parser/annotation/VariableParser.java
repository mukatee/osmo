package osmo.tester.parser.annotation;

import osmo.common.log.Logger;
import osmo.tester.annotation.Variable;
import osmo.tester.model.VariableField;
import osmo.tester.model.data.SearchableInput;
import osmo.tester.parser.AnnotationParser;
import osmo.tester.parser.ParserParameters;
import osmo.tester.parser.ParserResult;

import java.lang.reflect.Field;

/**
 * Parses {@link osmo.tester.annotation.Variable} annotations from the given model object.
 *
 * @author Teemu Kanstren
 */
public class VariableParser implements AnnotationParser {
  private static Logger log = new Logger(VariableParser.class);

  @Override
  public String parse(ParserResult result, ParserParameters parameters) {
    String errors = "";
    String annotationName = "@" + Variable.class.getSimpleName();
//    Variable annotation = (Variable) parameters.getAnnotation();
    Field field = parameters.getField();
    //we bypass the private etc. modifiers to access it
    field.setAccessible(true);
    Object model = parameters.getModel();
    Variable annotation = (Variable) parameters.getAnnotation();
    String name = annotation.value();
    VariableField var = new VariableField(model, field, name);
    Object o = null;
    try {
      o = field.get(model);
    } catch (IllegalAccessException e) {
      throw new RuntimeException("Failed to check variable type for " + field.getName(), e);
    }
    result.getFsm().addStateVariable(var);
    log.debug("Parsed variable:" + annotationName);
    return errors;
  }

}
