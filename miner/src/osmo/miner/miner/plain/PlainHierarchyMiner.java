package osmo.miner.miner.plain;

import osmo.miner.gui.attributetable.ValuePair;
import osmo.miner.miner.Miner;
import osmo.miner.model.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlainHierarchyMiner implements Miner {
  private Node root = new Node(null, "root", new ArrayList<ValuePair>());
  private Node current = null;

  public PlainHierarchyMiner() {
    current = root;
  }

  @Override
  public void startElement(String name, Map<String, String> attributes) {
    List<ValuePair> pairs = new ArrayList<ValuePair>();
    for (Map.Entry<String, String> entry : attributes.entrySet()) {
      pairs.add(new ValuePair(entry.getKey(), entry.getValue()));
    }
    current = current.addChild(name, pairs);
  }

  @Override
  public void endElement(String name) {
    current = current.getParent();
  }

  public Node getRoot() {
    return root;
  }
}