package osmo.tester.generator.endcondition.logical;

import osmo.tester.OSMOConfiguration;
import osmo.tester.generator.endcondition.EndCondition;
import osmo.tester.generator.testsuite.TestSuite;
import osmo.tester.model.FSM;

import java.util.Arrays;

/**
 * Allows one to compose several end conditions into a single one, where both of the conditions must be met before
 * test generation should end.
 *
 * @author Teemu Kanstren
 */
public class And implements EndCondition {
  /** The set of combined end conditions to be checked. */
  private final EndCondition[] conditions;

  /** @param conditions The set of combined end conditions to be checked. */
  public And(EndCondition... conditions) {
    this.conditions = conditions;
  }

  @Override
  public boolean endSuite(TestSuite suite, FSM fsm) {
    for (EndCondition condition : conditions) {
      if (!condition.endSuite(suite, fsm)) {
        //if any return "false", the AND composition becomes false as well.
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean endTest(TestSuite suite, FSM fsm) {
    for (EndCondition condition : conditions) {
      if (!condition.endTest(suite, fsm)) {
        //if any return "false", the AND composition becomes false as well.
        return false;
      }
    }
    return true;
  }

  @Override
  public void init(long seed, FSM fsm, OSMOConfiguration config) {
    for (EndCondition condition : conditions) {
      condition.init(seed, fsm, config);
    }
  }

  @Override
  public String toString() {
    return "And{" +
            "conditions=" + (conditions == null ? null : Arrays.asList(conditions)) +
            '}';
  }

  @Override
  public EndCondition cloneMe() {
    EndCondition[] clones = new EndCondition[conditions.length];
    for (int i = 0 ; i < conditions.length ; i++) {
      clones[i] = conditions[i].cloneMe();
    }
    And clone = new And(clones);
    return clone;
  }
}
