package osmo.tester.endconditions;

import org.junit.Before;
import org.junit.Test;
import osmo.common.log.Logger;
import osmo.tester.OSMOTester;
import osmo.tester.generator.endcondition.Length;
import osmo.tester.generator.endcondition.data.DataCoverage;
import osmo.tester.generator.endcondition.data.DataCoverageRequirement;
import osmo.tester.generator.testsuite.TestCase;
import osmo.tester.generator.testsuite.TestSuite;
import osmo.tester.testmodels.VariableModel2;

import java.util.List;
import java.util.Random;

import static junit.framework.Assert.*;
import static junit.framework.Assert.assertEquals;

/** @author Teemu Kanstren */
public class DataCoverageTests {
  private OSMOTester osmo = null;

  @Before
  public void testSetup() {
    Logger.debug = true;
    osmo = new OSMOTester();
    osmo.setRandom(new Random(123));
  }

  @Test
  public void rangeWith3RequiredDataForSingleTest() {
    VariableModel2 model = new VariableModel2();
    osmo.addModelObject(model);
    DataCoverage dc = new DataCoverage();
    DataCoverageRequirement req = new DataCoverageRequirement("range");
    req.addRequirement(1);
    req.addRequirement(2);
    req.addRequirement(3);
    dc.addRequirement(req);
    Length length1 = new Length(1);
    osmo.addTestEndCondition(dc);
    osmo.addSuiteEndCondition(length1);
    osmo.generate();
    TestSuite suite = model.getSuite();
    List<TestCase> tests = suite.getFinishedTestCases();
    TestCase test = tests.get(0);
    String expected = "[5, 3, 2, 1]";
    String actual = test.getVariables().get("range").getValues().toString();
    assertEquals("Expected value for range", expected, actual);
  }

  @Test
  public void rangeWith5RequiredDataForSingleTest() {
    VariableModel2 model = new VariableModel2();
    osmo.addModelObject(model);
    DataCoverage dc = new DataCoverage();
    DataCoverageRequirement req = new DataCoverageRequirement("range");
    req.addRequirement(1);
    req.addRequirement(2);
    req.addRequirement(2);
    req.addRequirement(2);
    req.addRequirement(3);
    dc.addRequirement(req);
    Length length1 = new Length(1);
    osmo.addTestEndCondition(dc);
    osmo.addSuiteEndCondition(length1);
    osmo.generate();
    TestSuite suite = model.getSuite();
    List<TestCase> tests = suite.getFinishedTestCases();
    TestCase test = tests.get(0);
    String expected = "[5, 3, 2, 1, 4, 3, 2, 4, 5, 1, 4, 1, 5, 3, 2]";
    String actual = test.getVariables().get("range").getValues().toString();
    assertEquals("Expected value for range", expected, actual);
    //suite should be exactly same for one test
    String actualSuite = suite.getVariables().get("range").getValues().toString();
    assertEquals("Expected value for suite range", expected, actualSuite);
  }

  @Test
  public void rangeAndSetWith2DataRequirements() {
    VariableModel2 model = new VariableModel2();
    osmo.addModelObject(model);
    DataCoverage dc = new DataCoverage();
    DataCoverageRequirement req = new DataCoverageRequirement("range");
    req.addRequirement(1);
    req.addRequirement(2);
    req.addRequirement(2);
    req.addRequirement(2);
    req.addRequirement(3);
    dc.addRequirement(req);
    DataCoverageRequirement req1 = new DataCoverageRequirement("set");
    req1.addRequirement("v1");
    req1.addRequirement("v1");
    req1.addRequirement("v2");
    dc.addRequirement(req1);
    Length length1 = new Length(1);
    osmo.addTestEndCondition(dc);
    osmo.addSuiteEndCondition(length1);
    osmo.generate();
    TestSuite suite = model.getSuite();
    List<TestCase> tests = suite.getFinishedTestCases();
    TestCase test = tests.get(0);
    String expected = "[5, 3, 2, 1, 4, 3, 2, 4, 5, 1, 4, 1, 5, 3, 2]";
    String actual = test.getVariables().get("range").getValues().toString();
    assertEquals("Expected value for range", expected, actual);
    //suite should be exactly same for one test
    String actualSuite = suite.getVariables().get("range").getValues().toString();
    assertEquals("Expected value for suite range", expected, actualSuite);
    String expected2 = "[v1, v3, v3, v3, v3, v2, v3, v1, v3, v3, v1, v1, v1, v3, v1]";
    String actual2 = test.getVariables().get("set").getValues().toString();
    assertEquals("Expected value for range", expected2, actual2);
    //suite should be exactly same for one test
    String actualSuite2 = suite.getVariables().get("set").getValues().toString();
    assertEquals("Expected value for suite range", expected2, actualSuite2);
  }

  @Test
  public void allCoverageRequirement() {
    VariableModel2 model = new VariableModel2();
    osmo.addModelObject(model);
    DataCoverage dc = new DataCoverage();
    DataCoverageRequirement req = new DataCoverageRequirement("range");
    req.requireAll();
    dc.addRequirement(req);
    DataCoverageRequirement req2 = new DataCoverageRequirement("set");
    req2.requireAll();
    dc.addRequirement(req2);
    Length length1 = new Length(1);
    osmo.addTestEndCondition(dc);
    osmo.addSuiteEndCondition(length1);
    osmo.generate();
    TestSuite suite = model.getSuite();
    List<TestCase> tests = suite.getFinishedTestCases();
    TestCase test = tests.get(0);
    String expected = "[5, 3, 2, 1, 4, 3]";
    String actual = test.getVariables().get("range").getValues().toString();
    assertEquals("Expected value for range", expected, actual);
    //suite should be exactly same for one test
    String actualSuite = suite.getVariables().get("range").getValues().toString();
    assertEquals("Expected value for suite range", expected, actualSuite);

    String expected2 = "[v1, v3, v3, v3, v3, v2]";
    String actual2 = test.getVariables().get("set").getValues().toString();
    assertEquals("Expected value for range", expected, actual);
    //suite should be exactly same for one test
    String actualSuite2 = suite.getVariables().get("set").getValues().toString();
    assertEquals("Expected value for suite range", expected2, actualSuite2);
  }

  @Test
  public void coverageForOnePlainVariable() {
    VariableModel2 model = new VariableModel2();
    osmo.addModelObject(model);
    DataCoverage dc = new DataCoverage();
    DataCoverageRequirement req = new DataCoverageRequirement("first");
    req.addRequirement(true);
    req.addRequirement(false);
    dc.addRequirement(req);
    Length length1 = new Length(1);
    osmo.addTestEndCondition(dc);
    osmo.addSuiteEndCondition(length1);
    osmo.generate();
    TestSuite suite = model.getSuite();
    List<TestCase> tests = suite.getFinishedTestCases();
    TestCase test = tests.get(0);
    assertEquals("Length of generated test", 1, test.getSteps().size());
    String expected = "[false, true]";
    String actual = test.getVariables().get("first").getValues().toString();
    assertEquals("Expected value for range", expected, actual);
    //suite should be exactly same for one test
    String actualSuite = suite.getVariables().get("first").getValues().toString();
    assertEquals("Expected value for suite range", expected, actualSuite);
  }

  @Test
  public void coverageForTwoPlainVariables() {
    VariableModel2 model = new VariableModel2();
    osmo.addModelObject(model);
    DataCoverage dc = new DataCoverage();
    DataCoverageRequirement req = new DataCoverageRequirement("first");
    req.addRequirement(true);
    req.addRequirement(false);
    dc.addRequirement(req);
    DataCoverageRequirement req2 = new DataCoverageRequirement("second");
    req2.addRequirement(true);
    req2.addRequirement(false);
    dc.addRequirement(req2);
    Length length1 = new Length(1);
    osmo.addTestEndCondition(dc);
    osmo.addSuiteEndCondition(length1);
    osmo.generate();
    TestSuite suite = model.getSuite();
    List<TestCase> tests = suite.getFinishedTestCases();
    TestCase test = tests.get(0);
    assertEquals("Length of generated test", 2, test.getSteps().size());
    String expected = "[false, true]";
    String actual = test.getVariables().get("first").getValues().toString();
    assertEquals("Expected value for range", expected, actual);
    //suite should be exactly same for one test
    String actualSuite = suite.getVariables().get("first").getValues().toString();
    assertEquals("Expected value for suite range", expected, actualSuite);

    String expected2 = "[false, true]";
    String actual2 = test.getVariables().get("second").getValues().toString();
    assertEquals("Expected value for range", expected2, actual2);
    //suite should be exactly same for one test
    String actualSuite2 = suite.getVariables().get("second").getValues().toString();
    assertEquals("Expected value for suite range", expected2, actualSuite2);
  }

  @Test
  public void nonExistentVariable() {
    VariableModel2 model = new VariableModel2();
    osmo.addModelObject(model);
    DataCoverage dc = new DataCoverage();
    DataCoverageRequirement req = new DataCoverageRequirement("non-existent");
    dc.addRequirement(req);
    Length length1 = new Length(1);
    osmo.addTestEndCondition(dc);
    osmo.addSuiteEndCondition(length1);
    try {
      osmo.generate();
      fail("Generation with coverage for non-existent variable should fail.");
    } catch (IllegalStateException e) {
      //Expected
      assertEquals("Reported error", "Impossible coverage requirements, defined variables [non-existent] not found.", e.getMessage());
    }
  }

  @Test
  public void invalidCoverageReq() {
    VariableModel2 model = new VariableModel2();
    osmo.addModelObject(model);
    DataCoverage dc = new DataCoverage();
    DataCoverageRequirement req = new DataCoverageRequirement("range");
    req.addRequirement(6);
    dc.addRequirement(req);
    Length length1 = new Length(1);
    osmo.addTestEndCondition(dc);
    osmo.addSuiteEndCondition(length1);
    try {
      osmo.generate();
      fail("Generation with coverage for invalid values should fail.");
    } catch (IllegalArgumentException e) {
      //Expected
      assertEquals("Reported error", "Impossible coverage requirements, defined variables [range] can not have value 6.", e.getMessage());
    }
  }

  @Test
  public void rangeWith5RequiredDataForSuite() {
    VariableModel2 model = new VariableModel2();
    osmo.addModelObject(model);
    DataCoverage dc = new DataCoverage();
    DataCoverageRequirement req = new DataCoverageRequirement("range");
    req.addRequirement(1);
    req.addRequirement(2);
    req.addRequirement(2);
    req.addRequirement(2);
    req.addRequirement(3);
    dc.addRequirement(req);
    Length length5 = new Length(5);
    osmo.addTestEndCondition(length5);
    osmo.addSuiteEndCondition(dc);
    osmo.generate();
    TestSuite suite = model.getSuite();
    List<TestCase> tests = suite.getFinishedTestCases();
    assertEquals("Number of tests", 4, tests.size());

    assertRange(tests.get(0), "[5, 3, 2]");
    assertRange(tests.get(1), "[1, 4, 3, 2, 4]");
    assertRange(tests.get(2), "[5, 1, 4, 1, 5]");
    assertRange(tests.get(3), "[3, 2, 2, 1, 5]");

    String expectedSuite = "[5, 3, 2, 1, 4, 3, 2, 4, 5, 1, 4, 1, 5, 3, 2, 2, 1, 5]";
    String actualSuite = suite.getVariables().get("range").getValues().toString();
    assertEquals("Expected value for suite range", expectedSuite, actualSuite);
  }

  private void assertRange(TestCase test, String expected) {
    assertEquals("Test length", 5, test.getSteps().size());
    String actual = test.getVariables().get("range").getValues().toString();
    assertEquals("Expected value for range", expected, actual);
  }
}
