package osmo.tester.examples.tutorial.basic;

import osmo.tester.OSMOConfiguration;
import osmo.tester.OSMOTester;
import osmo.tester.generator.endcondition.And;
import osmo.tester.generator.endcondition.Length;
import osmo.tester.generator.endcondition.Probability;

/** @author Teemu Kanstren */
public class Main4 {
  public static void main(String[] args) {
    OSMOConfiguration.setSeed(52);
    OSMOTester tester = new OSMOTester(new HelloModel2());
    tester.addTestEndCondition(new And(new Length(5), new Probability(0.33)));
    tester.addSuiteEndCondition(new Length(6));
    tester.generate();
  }
}