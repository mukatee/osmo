package osmo.tester.generation;

import org.junit.Ignore;
import org.junit.Test;
import osmo.common.TestUtils;
import osmo.tester.OSMOConfiguration;
import osmo.tester.generator.endcondition.Length;
import osmo.tester.generator.endcondition.Time;
import osmo.tester.generator.multi.MultiOSMO;
import osmo.tester.model.ModelFactory;
import osmo.tester.model.Requirements;
import osmo.tester.model.TestModels;
import osmo.tester.testmodels.ValidTestModel2;

import java.util.List;

import static org.junit.Assert.*;

/** @author Teemu Kanstren */
public class MultiOSMOTests {
  @Test
  public void generate4() {
    TestUtils.recursiveDelete("osmo-output");
    MultiOSMO mosmo = new MultiOSMO(4, 444);
    OSMOConfiguration config = mosmo.getConfig();
    config.setTraceRequested(true);
    config.setFactory(new MyModelFactory());
    config.setTestEndCondition(new Length(10));
    config.setSuiteEndCondition(new Time(2));
    mosmo.generate(new Time(1));
    List<String> reports = TestUtils.listFiles("osmo-output", ".csv", false);
    assertEquals("Number of reports generated", 4, reports.size());
    List<String> traces = TestUtils.listFiles("osmo-output", ".html", false);
    assertEquals("Number of HTML traces generated", 4, traces.size());
    List<String> xmls = TestUtils.listFiles("osmo-output", ".xml", false);
    assertEquals("Number of XML traces generated", 4, xmls.size());
  }

  @Ignore
  @Test
  public void generate4times3() {
    TestUtils.recursiveDelete("osmo-output");
    MultiOSMO mosmo = new MultiOSMO(4, 444);
    OSMOConfiguration config = mosmo.getConfig();
    config.setTraceRequested(true);
    config.setFactory(new MyModelFactory());
    config.setTestEndCondition(new Length(10));
    config.setSuiteEndCondition(new Time(2));
    mosmo.generate(new Time(6));
    List<String> reports = TestUtils.listFiles("osmo-output", ".csv", false);
    assertEquals("Number of reports generated", 5, reports.size());
    List<String> traces = TestUtils.listFiles("osmo-output", ".html", false);
    assertEquals("Number of traces generated", 4, traces.size());
  }

  private static class MyModelFactory implements ModelFactory {
    @Override
    public TestModels createModelObjects() {
      TestModels result = new TestModels();
      result.add(new ValidTestModel2(new Requirements()));
      return result;
    }
  }
}