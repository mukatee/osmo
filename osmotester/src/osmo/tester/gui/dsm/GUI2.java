package osmo.tester.gui.dsm;

import osmo.tester.model.FSM;
import osmo.tester.model.FSMTransition;
import osmo.tester.model.VariableField;
import osmo.tester.model.dataflow.SearchableInput;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** @author Teemu Kanstren */
public class GUI2 extends JFrame {
  private JTextField trnRuleField;
  private JTextField varRuleField;
  private JTextField modelFactoryField;
  private List<String> transitionRules = new ArrayList<String>();
  private List<String> variableRules = new ArrayList<String>();

  public static void main(String[] args) {
    GUI2 g = new GUI2(new ArrayList<String>(), new ArrayList<String>());
    g.setVisible(true);
  }

  public GUI2(FSM fsm) {
    Collection<FSMTransition> fsmTransitions = fsm.getTransitions();
    Collection<SearchableInput> inputs = fsm.getSearchableInputs();
    Collection<VariableField> stateVariables = fsm.getStateVariables();

    List<String> transitions = new ArrayList<String>();
    for (FSMTransition transition : fsmTransitions) {
      transitions.add(transition.getName());
    }
    List<String> variables = new ArrayList<String>();
    for (VariableField stateVariable : stateVariables) {
      variables.add(stateVariable.getName());
    }
    for (SearchableInput input : inputs) {
      variables.add(input.getName());
    }
    init(transitions, variables);
  }

  public GUI2(List<String> transitions, List<String> variables) {
    init(transitions, variables);
  }

  public void init(List<String> transitions, List<String> variables) {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("DSM Script Builder");
    setBounds(100, 100, 740, 500);
    setResizable(false);

    JComboBox algorithmCombo = new JComboBox();
    algorithmCombo.setModel(new DefaultComboBoxModel(new String[]{"RandomAlgorithm", "BalancingAlgorithm", "WeightedAlgorithm"}));

    JButton btnWriteScript = new JButton("Write Script");
    JLabel lblTransitions = new JLabel("Transitions:");
    JScrollPane trnScrollPane = new JScrollPane();
    JLabel lblVariables = new JLabel("Variables:");
    JScrollPane varScrollPane = new JScrollPane();

    final JComboBox ruleCombo = new JComboBox();
    ruleCombo.setModel(new DefaultComboBoxModel(new String[]{">=", "<=", "=="}));

    JButton btnTrnAdd = new JButton("Add");

    trnRuleField = new JTextField();
    trnRuleField.setColumns(5);

    JLabel lblTransitionRules = new JLabel("Transition coverage requirements:");

    JLabel lblVariableRules = new JLabel("Variable coverage requirements:");

    JScrollPane trnRulePane = new JScrollPane();

    JScrollPane varRulePane = new JScrollPane();

    varRuleField = new JTextField();
    varRuleField.setColumns(9);

    JButton btnVarAdd = new JButton("Add");

    JLabel lblModelFactory = new JLabel("Model Factory:");

    modelFactoryField = new JTextField();
    modelFactoryField.setColumns(12);

    GroupLayout groupLayout = new GroupLayout(getContentPane());
    groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(algorithmCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(modelFactoryField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(btnWriteScript).addComponent(lblModelFactory)).addPreferredGap(ComponentPlacement.RELATED).addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblVariables).addComponent(lblTransitions).addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false).addComponent(varScrollPane, Alignment.LEADING, 0, 0, Short.MAX_VALUE).addComponent(trnScrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(btnTrnAdd).addGroup(groupLayout.createSequentialGroup().addComponent(ruleCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addComponent(trnRuleField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addComponent(varRuleField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(btnVarAdd)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblTransitionRules).addComponent(trnRulePane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE).addComponent(varRulePane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE).addComponent(lblVariableRules)).addGap(72)));
    groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addGap(4).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblTransitions).addComponent(lblTransitionRules)).addPreferredGap(ComponentPlacement.RELATED).addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addComponent(algorithmCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addComponent(lblModelFactory).addPreferredGap(ComponentPlacement.RELATED).addComponent(modelFactoryField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnWriteScript)).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(trnScrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE).addComponent(trnRulePane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblVariables).addComponent(lblVariableRules)).addPreferredGap(ComponentPlacement.RELATED).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(varScrollPane, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE).addComponent(varRulePane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)).addContainerGap()).addGroup(groupLayout.createSequentialGroup().addGap(49).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(ruleCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(trnRuleField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addPreferredGap(ComponentPlacement.RELATED).addComponent(btnTrnAdd).addPreferredGap(ComponentPlacement.RELATED, 200, Short.MAX_VALUE).addComponent(varRuleField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnVarAdd).addGap(114)));

    final JList varRuleList = new JList();
    varRulePane.setViewportView(varRuleList);

    final JList trnRuleList = new JList();
    trnRuleList.setModel(new StringListModel(transitionRules));
    trnRulePane.setViewportView(trnRuleList);

    final JList varList = new JList();
    varList.setModel(new StringListModel(variables));
    varScrollPane.setViewportView(varList);

    final JList trnList = new JList();
    trnList.setModel(new StringListModel(transitions));
    trnScrollPane.setViewportView(trnList);
    getContentPane().setLayout(groupLayout);

    btnTrnAdd.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String transition = trnList.getSelectedValue().toString();
        String rule = ruleCombo.getSelectedItem().toString();
        String value = trnRuleField.getText();
        transitionRules.add(transition + "," + rule + "," + value);
        trnRuleList.setModel(new StringListModel(transitionRules));
      }
    });
    btnVarAdd.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String variable = varList.getSelectedValue().toString();
        String value = varRuleField.getText();
        variableRules.add(variable + "," + value);
        varRuleList.setModel(new StringListModel(variableRules));
      }
    });
  }
}