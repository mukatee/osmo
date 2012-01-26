package osmo.tester.junit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** @author Teemu Kanstren */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OSMOConfigurationFactory {
}
