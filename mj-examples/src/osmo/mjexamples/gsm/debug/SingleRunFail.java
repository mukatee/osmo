package osmo.mjexamples.gsm.debug;

import osmo.common.NullPrintStream;
import osmo.mjexamples.gsm.GSMModelFactory;
import osmo.tester.OSMOConfiguration;
import osmo.tester.OSMOTester;
import osmo.tester.generator.endcondition.Length;
import osmo.tester.generator.endcondition.Time;

/**
 * @author Teemu Kanstren
 */
public class SingleRunFail {
  public static void main(String[] args) {
    OSMOTester tester = new OSMOTester();
    OSMOConfiguration config = tester.getConfig();
    config.setFactory(new GSMModelFactory(NullPrintStream.stream));
    config.setSequenceTraceRequested(true);
    config.setStopTestOnError(true);
    config.setTestEndCondition(new Length(50));
    config.setSuiteEndCondition(new Length(1));
    tester.generate(4275387207638575936l);
  }
}
