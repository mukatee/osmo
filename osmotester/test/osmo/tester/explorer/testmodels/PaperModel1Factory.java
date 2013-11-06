package osmo.tester.explorer.testmodels;

import osmo.tester.model.ModelFactory;
import osmo.tester.model.TestModels;
import osmo.tester.parser.ModelObject;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;

/** @author Teemu Kanstren */
public class PaperModel1Factory implements ModelFactory {
  private final PrintStream ps;

  public PaperModel1Factory() {
    this.ps = System.out;
  }

  public PaperModel1Factory(PrintStream ps) {
    this.ps = ps;
  }

  @Override
  public TestModels createModelObjects() {
    TestModels result = new TestModels();
    result.add(new PaperModel1(ps));
    return result;
  }
}