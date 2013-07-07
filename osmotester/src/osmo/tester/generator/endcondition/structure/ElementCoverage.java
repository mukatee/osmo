package osmo.tester.generator.endcondition.structure;

import osmo.tester.generator.endcondition.EndCondition;
import osmo.tester.generator.testsuite.TestSuite;
import osmo.tester.model.FSM;

/** 
 * An end condition for test generation that defines a set of requirements related to the model structure.
 * For example, cover at least 3 steps, 5 step-pairs and 3 requirements.
 * 
 * @author Teemu Kanstren 
 */
public class ElementCoverage implements EndCondition {
  /** Defines what needs to be covered for this to end. */
  private final ElementCoverageRequirement requirement;

  public ElementCoverage(int steps, int pairs, int requirements) {
    this(new ElementCoverageRequirement(steps, pairs, requirements));
  }

  public ElementCoverage(ElementCoverageRequirement requirement) {
    this.requirement = requirement;
  }

  /**
   * If set to true, the tool tries to check if the model contains the required elements and the criteria seems
   * satisfiable.
   * 
   * @param check Try to check if given requirements are possible to cover or not.
   */
  public void setCheck(boolean check) {
    requirement.setCheck(check);
  }

  @Override
  public boolean endSuite(TestSuite suite, FSM fsm) {
    return requirement.checkCoverage(suite);
  }

  @Override
  public boolean endTest(TestSuite suite, FSM fsm) {
    return requirement.checkCoverage(suite.getCurrentTest());
  }

  @Override
  public void init(long seed, FSM fsm) {
    requirement.init(fsm);
  }
}
