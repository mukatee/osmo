package osmo.tester.scripter.internal;

import osmo.common.TestUtils;
import osmo.tester.generator.testsuite.TestCase;

import java.util.List;

/**
 * For writing a set of tests to a file in a format that can be later re-loaded and re-executed on the generator.
 * Matching reader is {@link TestLoader}.
 * 
 * @author Teemu Kanstren
 */
public class TestWriter {
  /** Where to save the scripts to. Relative to working directory. */
  private final String outputDir;

  public TestWriter(String outputDir) {
    this.outputDir = outputDir;
  }
  
  public void write(List<TestCase> tests) {
    for (TestCase test : tests) {
      write(test);
    }
  }

  public void write(TestCase test) {
    String output = "";
    output += "#OSMO Tester test case. Format v0.1.\n";
    output += "seed:" + test.getSeed() + "\n";
    List<String> stepNames = test.getAllStepNames();
    for (String step : stepNames) {
      output += step + "\n";
    }
    TestUtils.write(output, outputDir+"/"+test.getName()+".tc");
  }
}
