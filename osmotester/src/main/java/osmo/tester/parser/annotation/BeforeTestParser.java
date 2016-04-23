package osmo.tester.parser.annotation;

import osmo.common.Logger;
import osmo.tester.annotation.BeforeTest;
import osmo.tester.model.InvocationTarget;
import osmo.tester.parser.AnnotationParser;
import osmo.tester.parser.ParserParameters;
import osmo.tester.parser.ParserResult;

import java.lang.reflect.Method;

/**
 * Parses {@link osmo.tester.annotation.BeforeTest} annotations from the given model object.
 *
 * @author Teemu Kanstren
 */
public class BeforeTestParser implements AnnotationParser {
  private static final Logger log = new Logger(BeforeTestParser.class);

  @Override
  public void parse(ParserResult result, ParserParameters parameters, StringBuilder errors) {
    BeforeTest before = (BeforeTest) parameters.getAnnotation();
    result.getFsm().addBefore(new InvocationTarget(parameters, BeforeTest.class));
    Method method = parameters.getMethod();
    Class<?>[] parameterTypes = method.getParameterTypes();
    if (parameterTypes.length > 0) {
      errors.append("@" + BeforeTest.class.getSimpleName() + " methods are not allowed to have parameters: \"" + method.getName() + "()\" has " + parameterTypes.length + " parameters.\n");
    }
  }
}
