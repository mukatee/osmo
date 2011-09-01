package osmo.miner.miner.program;

import osmo.miner.Config;
import osmo.miner.gui.attributetable.ValuePair;
import osmo.miner.log.Logger;
import osmo.miner.model.Node;
import osmo.miner.model.program.Program;
import osmo.miner.model.program.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Teemu Kanstren
 */
public class ProgramModelMiner {
  private static final Logger log = new Logger(ProgramModelMiner.class);
  private final Program mainProgram = new Program(null, "program");
  private Program currentProgram = mainProgram;

  public ProgramModelMiner() {
    Config.validate();
  }

  public Program getMainProgram() {
    return mainProgram;
  }

  public void startElement(String element, Map<String, String> attributes) {
    if (element.equals(Config.variableId)) {
      String name = attributes.get(Config.variableNameId);
      String value = attributes.get(Config.variableValueId);
      Variable var = currentProgram.createVariable(name);
      var.addValue(value);
    }
    if (element.equals(Config.stepId)) {
      String name = attributes.get(Config.stepNameId);
//      log.debug("step start:"+name);
      currentProgram = currentProgram.createStep(name);
    }
  }

  public void endElement(String element) {
    if (element.equals(Config.stepId)) {
      currentProgram = currentProgram.getParent();
//      log.debug("Ending step:"+ currentProgram.getName());
    }
  }

  public Node getRoot() {
    Node root = new Node(null, mainProgram.getName(), new ArrayList<ValuePair>());
    createVariableNode(root);
    addChildren(root, mainProgram);
    return root;
  }

  private void createVariableNode(Node root) {
    Map<String, Variable> variables = mainProgram.getGlobalVariables();
    Node variableNode = root.addChild("Variables", new ArrayList<ValuePair>());
    for (Variable variable : variables.values()) {
      List<ValuePair> pairs = new ArrayList<ValuePair>();
      pairs.add(new ValuePair("Values", variable.getValues().toString()));
      variableNode.addChild(variable.getName(), pairs);
    }
  }

  private List<ValuePair> parameters(Program program) {
    Map<String, Variable> variables = program.getVariables();
    List<ValuePair> pairs = new ArrayList<ValuePair>();
    for (Variable variable : variables.values()) {
      pairs.add(new ValuePair(variable.getName(), variable.getValues()));
    }
    return pairs;
  }

  private void addChildren(Node node, Program program) {
    Map<String, Program> steps = program.getSteps();
    for (Program subProgram : steps.values()) {
      Node child = node.addChild(subProgram.getName(), parameters(subProgram));
      addChildren(child, subProgram);
    }
  }

}
