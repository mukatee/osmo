package osmo.tester.examples.tutorial.basic;

import osmo.tester.OSMOConfiguration;
import osmo.tester.OSMOTester;
import osmo.tester.generator.endcondition.Length;

/** @author Teemu Kanstren */
public class Main7 {
  public static void main(String[] args) {
    OSMOConfiguration.setSeed(52);
    OSMOTester tester = new OSMOTester(new HelloModel5());
    tester.addTestEndCondition(new Length(5));
    tester.addSuiteEndCondition(new Length(2));
    tester.generate();
  }
}
