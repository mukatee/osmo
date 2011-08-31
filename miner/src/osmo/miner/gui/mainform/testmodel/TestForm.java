package osmo.miner.gui.mainform.testmodel;

import osmo.miner.Config;
import osmo.miner.gui.TreeForm;
import osmo.miner.miner.Miner;
import osmo.miner.miner.program.ProgramModelMiner;
import osmo.miner.parser.Parser;
import osmo.miner.parser.xml.XmlParser;

/**
 * @author Teemu Kanstren
 */
public class TestForm extends TreeForm {
  public TestForm() {
  }

  @Override
  public Parser createParser() {
    return new XmlParser();
  }

  @Override
  public Miner createMiner() {
    return new ProgramModelMiner();
  }
}