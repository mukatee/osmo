package osmo.tester.explorer.testmodels;

import osmo.tester.OSMOConfiguration;
import osmo.tester.explorer.ExplorationConfiguration;
import osmo.tester.generator.ReflectiveModelFactory;
import osmo.tester.generator.endcondition.LengthProbability;
import osmo.tester.generator.endcondition.Probability;
import osmo.tester.generator.testsuite.TestCase;
import osmo.tester.optimizer.GreedyOptimizer;
import osmo.tester.testmodels.RandomValueModel3;

import java.util.List;

/** @author Teemu Kanstren */
public class CounterOfflineMain {
  public static void main(String[] args) {
    ExplorationConfiguration gc = new ExplorationConfiguration(null, 1, 52);
    gc.setStepWeight(30);
    gc.setStepPairWeight(20);
    gc.setDefaultValueWeight(7);
    gc.setVariableCountWeight(5);
    gc.setRequirementWeight(20);
    gc.setMaxTestLength(10);
    gc.setMinSuiteScore(50);
    gc.setMaxSuiteLength(10);
    gc.setSuitePlateauThreshold(50);
    OSMOConfiguration oc = new OSMOConfiguration();
    oc.setFactory(new ReflectiveModelFactory(CounterModel.class));
    oc.setTestEndCondition(new Probability(0.2));
    GreedyOptimizer greedy = new GreedyOptimizer(oc, gc);
    List<TestCase> cases = greedy.search(200, 52);
    for (TestCase test : cases) {
      System.out.println(test.getAttribute("test-script"));
    }
  }
}
