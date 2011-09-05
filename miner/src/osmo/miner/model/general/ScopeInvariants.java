package osmo.miner.model.general;

import osmo.miner.model.dataflow.DataFlowInvariant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Teemu Kanstren
 */
public class ScopeInvariants {
  private final String name;
  private final Map<String, Collection<DataFlowInvariant>> variables = new HashMap<String, Collection<DataFlowInvariant>>();

  public ScopeInvariants(String name) {
    this.name = name;
  }

  public void add(DataFlowInvariant invariant) {
    String variable = invariant.getVariable();
    Collection<DataFlowInvariant> data = variables.get(variable);
    if (data == null) {
      data = new ArrayList<DataFlowInvariant>();
      variables.put(variable, data);
    }
    data.add(invariant);
  }

  public Collection<DataFlowInvariant> getInvariantsFor(String variable) {
    return variables.get(variable);
  }

  public String getName() {
    return name;
  }

  public Map<String, Collection<DataFlowInvariant>> getVariables() {
    return variables;
  }
}
