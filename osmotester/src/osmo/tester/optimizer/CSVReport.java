package osmo.tester.optimizer;

import osmo.tester.coverage.ScoreCalculator;
import osmo.tester.coverage.TestCoverage;
import osmo.tester.generator.testsuite.TestCase;

import java.util.Collection;

/**
 * For creating a coverage report in comma separated value format.
 * Separator for columns is ";" which works to load the data into excel. Row separator is linefeed.
 * Builds a set of separate strings for different coverage criteria and pulls them together for a final report.
 * 
 * @author Teemu Kanstren 
 */
public class CSVReport {
  /** For calculating coverage scores. */
  private final ScoreCalculator scoreCalculator;
  /** String for overall cumulative coverage. */
  private String coverage = "cumulative coverage per test\n";
  /** String for how much each test gained coverage score compared to previous one in suite. */
  private String gain = "gained coverage per test\n";
  /** The number of tests in suite. Mainly useful as a quick overall view for large suites. */
  private String testCount = "number of tests in suite\n";
  /** Total coverage score of the suite. Not shown for all tests but once at the end. */
  private String totalScore = "total score\n";
  /** Number of unique steps in a the test suite up to that test case (each column in matrix = test case in suite). */
  private String steps = "number of steps\n";
  /** Number of step-pairs in the test suite up to the test case. */
  private String stepPairs = "number of step pairs\n";
  /** Number of unique values in the test suite up to the test case. */
  private String values = "number of values\n";
  /** Number of covered requirements up to the test case. */
  private String reqs = "number of requirements\n";
  /** Number of all steps in a the test suite up to the test case. */
  private String length = "length\n";

  public CSVReport(ScoreCalculator scoreCalculator) {
    this.scoreCalculator = scoreCalculator;
  }

  public String report() {
    String report = "";
    report += coverage;
    report += gain;
    report += testCount;
    report += totalScore;
    report += steps;
    report += stepPairs;
    report += values;
    report += reqs;
    report += length;
    return report;
  }

  /**
   * CSV row of coverage for each test, to use in excel etc.
   *
   * @param tests Create coverage CSV for these.
   */
  public void process(Collection<TestCase> tests) {
    TestCoverage tc = new TestCoverage();
    for (TestCase test : tests) {
      int old = scoreCalculator.calculateScore(tc);
      tc.addTestCoverage(test);
      int now = scoreCalculator.calculateScore(tc);
      int gain = now - old;
      this.gain += gain + "; ";

      coverage += scoreCalculator.calculateScore(tc) + "; ";
      reqs += tc.getRequirements().size() + "; ";
      length += tc.getSteps().size() + "; ";
      steps += tc.getSingles().size() + "; ";
      stepPairs += tc.getStepPairs().size() + "; ";
      values += tc.getValueCount()+"; ";
    }
    this.gain += "\n";
    coverage += "\n";
    reqs += "\n";
    length += "\n";
    steps += "\n";
    stepPairs += "\n";
    values += "\n";

    testCount += tests.size();
    testCount += "\n";

    totalScore += scoreCalculator.calculateScore(tc);
    totalScore += "\n";
  }
}