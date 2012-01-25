package osmo.tester.gui.manualdrive;

import osmo.tester.model.dataflow.ReadableChars;
import osmo.tester.model.dataflow.ReadableWords;
import osmo.tester.model.dataflow.SearchableInput;
import osmo.tester.model.dataflow.ValueSet;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * GUI to manually define value for a value set.
 *
 * @author Teemu Kanstren
 */
public class ValueSetGUI extends ValueGUI {
  /** For the user to define the value. */
  private JComboBox combo;

  public ValueSetGUI(SearchableInput input) throws HeadlessException {
    super(input);
  }

  @Override
  protected String createValueLabel() {
    return "Pick option from list";
  }

  @Override
  protected JComponent createValueComponent() {
    ValueSet set = (ValueSet) input;
    Collection options = set.getOptions();
    List choices = new ArrayList(options);
    combo = new JComboBox(choices.toArray());
    return combo;
  }

  @Override
  protected Object value() {
    return combo.getSelectedItem();
  }

  public static void main(String[] args) {
    setNimbus();
    ReadableWords words = new ReadableWords(5,7);
    words.asciiLettersAndNumbersOnly();
    ValueSet<String> set = new ValueSet<String>();
    set.add(words.next());
    set.add(words.next());
    set.add(words.next());
    set.setName("user");
    ValueSetGUI gui = new ValueSetGUI(set);
    gui.setVisible(true);
  }
}