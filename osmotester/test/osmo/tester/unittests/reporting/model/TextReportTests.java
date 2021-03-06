package osmo.tester.unittests.reporting.model;

import org.junit.Test;
import osmo.common.NullPrintStream;
import osmo.tester.generator.ReflectiveModelFactory;
import osmo.tester.generator.SingleInstanceModelFactory;
import osmo.tester.model.Requirements;
import osmo.tester.reporting.model.ModelPrinter;
import osmo.tester.unittests.testmodels.ValidTestModel1;
import osmo.tester.unittests.testmodels.ValidTestModel2;
import osmo.tester.unittests.testmodels.ValidTestModel3;
import osmo.tester.unittests.testmodels.ValidTestModel4;
import osmo.tester.unittests.testmodels.ValidTestModel5;
import osmo.tester.unittests.testmodels.ValidTestModel6;

import static junit.framework.Assert.*;

/** @author Teemu Kanstren */
public class TextReportTests {
  @Test
  public void validModel1() {
    ModelPrinter mv = new ModelPrinter();
    mv.setModelFactory(new ReflectiveModelFactory(ValidTestModel1.class));
    String actual = mv.write();
    String expected = "BeforeSuites: \n" +
        "AfterSuites: \n" +
        "BeforeTests: \n" +
        "AfterTests: \n" +
        "Last Steps: \n" +
        "Model EndConditions: \n" +
        "On Errors: osmo.tester.unittests.testmodels.ValidTestModel1.error(), \n" +
        "Exploration Enablers: \n" +
        "Generation Enablers: \n" +
        "Coverage Value Methods: \n" +
        "Requirements: null\n" +
        "Variables: \n" +
        "\n" +
        "STEP: world, WEIGHT=10\n" +
        "GUARDS: osmo.tester.unittests.testmodels.ValidTestModel1.listCheck(), \n" +
        "GROUP: \n" +
        "POST: \n" +
        "PRE: \n" +
        "\n" +
        "STEP: epixx, WEIGHT=10\n" +
        "GUARDS: osmo.tester.unittests.testmodels.ValidTestModel1.kitted(), \n" +
        "GROUP: \n" +
        "POST: \n" +
        "PRE: \n" +
        "\n";
    assertEquals("Text report for model", expected, actual);
  }

  @Test
  public void validModel2() {
    ModelPrinter mv = new ModelPrinter();
    SingleInstanceModelFactory factory = new SingleInstanceModelFactory();
    factory.add(new ValidTestModel2(new Requirements(), NullPrintStream.stream));
    mv.setModelFactory(factory);
    String actual = mv.write();
    String expected = "BeforeSuites: osmo.tester.unittests.testmodels.ValidTestModel2.firstOfAll(), \n" +
        "AfterSuites: osmo.tester.unittests.testmodels.ValidTestModel2.lastOfAll(), \n" +
        "BeforeTests: osmo.tester.unittests.testmodels.ValidTestModel2.setup(), \n" +
        "AfterTests: osmo.tester.unittests.testmodels.ValidTestModel2.bob(), \n" +
        "Last Steps: osmo.tester.unittests.testmodels.ValidTestModel2.last(), \n" +
        "Model EndConditions: \n" +
        "On Errors: \n" +
        "Exploration Enablers: \n" +
        "Generation Enablers: \n" +
        "Coverage Value Methods: \n" +
        "Requirements: Requirements{reqs=[]} ([])\n" +
        "Variables: \n" +
        "\n" +
        "STEP: world, WEIGHT=10\n" +
        "GUARDS: osmo.tester.unittests.testmodels.ValidTestModel2.worldCheck(), \n" +
        "GROUP: \n" +
        "POST: \n" +
        "PRE: \n" +
        "\n" +
        "STEP: epixx, WEIGHT=10\n" +
        "GUARDS: osmo.tester.unittests.testmodels.ValidTestModel2.kitted(), \n" +
        "GROUP: \n" +
        "POST: osmo.tester.unittests.testmodels.ValidTestModel2.epixxO(), \n" +
        "PRE: osmo.tester.unittests.testmodels.ValidTestModel2.epixxPre(), \n" +
        "\n" +
        "STEP: hello, WEIGHT=10\n" +
        "GUARDS: osmo.tester.unittests.testmodels.ValidTestModel2.helloCheck(), \n" +
        "GROUP: \n" +
        "POST: \n" +
        "PRE: \n" +
        "\n";
    assertEquals("Text report for model", expected, actual);
  }

  @Test
  public void validModel3() {
    ModelPrinter mv = new ModelPrinter();
    SingleInstanceModelFactory factory = new SingleInstanceModelFactory();
    factory.add(new ValidTestModel3(NullPrintStream.stream));
    mv.setModelFactory(factory);
    String actual = mv.write();
    String expected = "BeforeSuites: osmo.tester.unittests.testmodels.ValidTestModel3.empty(), \n" +
        "AfterSuites: osmo.tester.unittests.testmodels.ValidTestModel3.empty(), \n" +
        "BeforeTests: osmo.tester.unittests.testmodels.ValidTestModel3.reset(), \n" +
        "AfterTests: osmo.tester.unittests.testmodels.ValidTestModel3.empty(), \n" +
        "Last Steps: \n" +
        "Model EndConditions: \n" +
        "On Errors: \n" +
        "Exploration Enablers: \n" +
        "Generation Enablers: \n" +
        "Coverage Value Methods: \n" +
        "Requirements: Requirements{reqs=[]} ([])\n" +
        "Variables: \n" +
        "\n" +
        "STEP: world, WEIGHT=10\n" +
        "GUARDS: osmo.tester.unittests.testmodels.ValidTestModel3.worldCheck(), \n" +
        "GROUP: \n" +
        "POST: osmo.tester.unittests.testmodels.ValidTestModel3.stateCheck(), \n" +
        "PRE: \n" +
        "\n" +
        "STEP: epixx, WEIGHT=10\n" +
        "GUARDS: osmo.tester.unittests.testmodels.ValidTestModel3.kitted(), \n" +
        "GROUP: \n" +
        "POST: osmo.tester.unittests.testmodels.ValidTestModel3.epixxO(), osmo.tester.unittests.testmodels.ValidTestModel3.stateCheck(), \n" +
        "PRE: osmo.tester.unittests.testmodels.ValidTestModel3.epixxPre(), \n" +
        "\n" +
        "STEP: hello, WEIGHT=10\n" +
        "GUARDS: osmo.tester.unittests.testmodels.ValidTestModel3.helloCheck(), \n" +
        "GROUP: \n" +
        "POST: osmo.tester.unittests.testmodels.ValidTestModel3.stateCheck(), \n" +
        "PRE: \n" +
        "\n";
    assertEquals("Text report for model", expected, actual);
  }

  @Test
  public void validModel4() {
    ModelPrinter mv = new ModelPrinter();
    SingleInstanceModelFactory factory = new SingleInstanceModelFactory();
    factory.add(new ValidTestModel4(NullPrintStream.stream));
    mv.setModelFactory(factory);
    String actual = mv.write();
    String expected = "BeforeSuites: \n" +
        "AfterSuites: \n" +
        "BeforeTests: osmo.tester.unittests.testmodels.ValidTestModel4.reset(), \n" +
        "AfterTests: \n" +
        "Last Steps: \n" +
        "Model EndConditions: \n" +
        "On Errors: \n" +
        "Exploration Enablers: \n" +
        "Generation Enablers: \n" +
        "Coverage Value Methods: my-state[osmo.tester.unittests.testmodels.ValidTestModel4.state1(osmo.tester.generator.testsuite.TestCaseStep)], \n" +
        "Requirements: Requirements{reqs=[]} ([])\n" +
        "Variables: \n" +
        "\n" +
        "STEP: world, WEIGHT=10\n" +
        "GUARDS: osmo.tester.unittests.testmodels.ValidTestModel4.worldCheck(), \n" +
        "GROUP: \n" +
        "POST: osmo.tester.unittests.testmodels.ValidTestModel4.savePostState(), osmo.tester.unittests.testmodels.ValidTestModel4.sharedCheck(), osmo.tester.unittests.testmodels.ValidTestModel4.stateCheck(), \n" +
        "PRE: osmo.tester.unittests.testmodels.ValidTestModel4.savePreState(), \n" +
        "\n" +
        "STEP: epixx, WEIGHT=10\n" +
        "GUARDS: osmo.tester.unittests.testmodels.ValidTestModel4.kitted(), \n" +
        "GROUP: \n" +
        "POST: osmo.tester.unittests.testmodels.ValidTestModel4.epixxO(), osmo.tester.unittests.testmodels.ValidTestModel4.savePostState(), osmo.tester.unittests.testmodels.ValidTestModel4.stateCheck(), \n" +
        "PRE: osmo.tester.unittests.testmodels.ValidTestModel4.savePreState(), \n" +
        "\n" +
        "STEP: hello, WEIGHT=10\n" +
        "GUARDS: osmo.tester.unittests.testmodels.ValidTestModel4.helloCheck(), \n" +
        "GROUP: \n" +
        "POST: osmo.tester.unittests.testmodels.ValidTestModel4.savePostState(), osmo.tester.unittests.testmodels.ValidTestModel4.sharedCheck(), osmo.tester.unittests.testmodels.ValidTestModel4.stateCheck(), \n" +
        "PRE: osmo.tester.unittests.testmodels.ValidTestModel4.savePreState(), \n" +
        "\n";
    assertEquals("Text report for model", expected, actual);
  }

  @Test
  public void validModel5() {
    ModelPrinter mv = new ModelPrinter();
    SingleInstanceModelFactory factory = new SingleInstanceModelFactory();
    factory.add(new ValidTestModel5(NullPrintStream.stream));
    mv.setModelFactory(factory);
    String actual = mv.write();
    String expected = "BeforeSuites: \n" +
        "AfterSuites: \n" +
        "BeforeTests: osmo.tester.unittests.testmodels.ValidTestModel5.reset(), \n" +
        "AfterTests: \n" +
        "Last Steps: osmo.tester.unittests.testmodels.ValidTestModel5.last(), \n" +
        "Model EndConditions: osmo.tester.unittests.testmodels.ValidTestModel5.end(), \n" +
        "On Errors: osmo.tester.unittests.testmodels.ValidTestModel5.error(), \n" +
        "Exploration Enablers: \n" +
        "Generation Enablers: \n" +
        "Coverage Value Methods: \n" +
        "Requirements: Requirements{reqs=[]} ([])\n" +
        "Variables: \n" +
        "\n" +
        "STEP: world, WEIGHT=10\n" +
        "GUARDS: osmo.tester.unittests.testmodels.ValidTestModel5.worldCheck(), \n" +
        "GROUP: \n" +
        "POST: osmo.tester.unittests.testmodels.ValidTestModel5.sharedCheck(), osmo.tester.unittests.testmodels.ValidTestModel5.stateCheck(), \n" +
        "PRE: \n" +
        "\n" +
        "STEP: epixx, WEIGHT=10\n" +
        "GUARDS: osmo.tester.unittests.testmodels.ValidTestModel5.kitted(), \n" +
        "GROUP: \n" +
        "POST: osmo.tester.unittests.testmodels.ValidTestModel5.epixxO(), osmo.tester.unittests.testmodels.ValidTestModel5.stateCheck(), \n" +
        "PRE: \n" +
        "\n" +
        "STEP: hello, WEIGHT=10\n" +
        "GUARDS: osmo.tester.unittests.testmodels.ValidTestModel5.helloCheck(), \n" +
        "GROUP: \n" +
        "POST: osmo.tester.unittests.testmodels.ValidTestModel5.sharedCheck(), osmo.tester.unittests.testmodels.ValidTestModel5.stateCheck(), \n" +
        "PRE: \n" +
        "\n";
    assertEquals("Text report for model", expected, actual);
  }

  @Test
  public void validModel6() {
    ModelPrinter mv = new ModelPrinter();
    mv.setModelFactory(new ReflectiveModelFactory(ValidTestModel6.class));
    String actual = mv.write();
    String expected = "BeforeSuites: \n" +
        "AfterSuites: \n" +
        "BeforeTests: \n" +
        "AfterTests: \n" +
        "Last Steps: osmo.tester.unittests.testmodels.ValidTestModel6.check(), \n" +
        "Model EndConditions: \n" +
        "On Errors: \n" +
        "Exploration Enablers: \n" +
        "Generation Enablers: \n" +
        "Coverage Value Methods: \n" +
        "Requirements: null\n" +
        "Variables: index, \n" +
        "\n" +
        "STEP: t4, WEIGHT=10\n" +
        "GUARDS: \n" +
        "GROUP: \n" +
        "POST: osmo.tester.unittests.testmodels.ValidTestModel6.savePostState(), \n" +
        "PRE: osmo.tester.unittests.testmodels.ValidTestModel6.savePreState(), \n" +
        "\n" +
        "STEP: t1, WEIGHT=10\n" +
        "GUARDS: \n" +
        "GROUP: \n" +
        "POST: osmo.tester.unittests.testmodels.ValidTestModel6.savePostState(), \n" +
        "PRE: osmo.tester.unittests.testmodels.ValidTestModel6.savePreState(), \n" +
        "\n" +
        "STEP: t3, WEIGHT=10\n" +
        "GUARDS: \n" +
        "GROUP: \n" +
        "POST: osmo.tester.unittests.testmodels.ValidTestModel6.savePostState(), \n" +
        "PRE: osmo.tester.unittests.testmodels.ValidTestModel6.savePreState(), \n" +
        "\n" +
        "STEP: t2, WEIGHT=10\n" +
        "GUARDS: \n" +
        "GROUP: \n" +
        "POST: osmo.tester.unittests.testmodels.ValidTestModel6.savePostState(), \n" +
        "PRE: osmo.tester.unittests.testmodels.ValidTestModel6.savePreState(), \n" +
        "\n";
    assertEquals("Text report for model", expected, actual);
  }
}
