package osmo.tester.gui.jfx.configurationtab;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import osmo.tester.gui.jfx.configurationtab.endconditions.ECDescription;
import osmo.tester.gui.jfx.configurationtab.endconditions.EndlessDescription;
import osmo.tester.gui.jfx.configurationtab.endconditions.LengthDescription;
import osmo.tester.gui.jfx.configurationtab.endconditions.LengthProbabilityDescription;
import osmo.tester.gui.jfx.configurationtab.endconditions.ProbabilityDescription;
import osmo.tester.gui.jfx.configurationtab.endconditions.TimeDescription;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Teemu Kanstren
 */
public class BasicsPane extends GridPane {
  private ECDescription chosenTestEC = null;
  private ECDescription chosenSuiteEC = null;
  private List<ECDescription> testECs = new ArrayList<>();
  private List<ECDescription> suiteECs = new ArrayList<>();
  private ComboBox<ECDescription> oldTestBox = null;
  private ComboBox<ECDescription> oldSuiteBox = null;
  private Button testButton;
  private Button suiteButton;
  public static final int labelY = 0;
  public static final int testY = 1;
  public static final int suiteY = 2;
  public static final int algoY = 3;

  public BasicsPane() {
    setHgap(10);
    setVgap(10);
    init();
    int y = 0;
    add(new Label("Basic Settings"), 0, labelY, 3, 1);
    createTestECPane();
    createSuiteECPane();
    createAlgorithmPane();
  }
  
  private void init() {
    testECs.add(new LengthDescription());
    ProbabilityDescription probabilityT = new ProbabilityDescription();
    chosenTestEC = probabilityT;
    testECs.add(probabilityT);
    testECs.add(new LengthProbabilityDescription());
    testECs.add(new EndlessDescription());
    testECs.add(new TimeDescription());

    suiteECs.add(new LengthDescription());
    ProbabilityDescription probabilityS = new ProbabilityDescription();
    chosenSuiteEC = probabilityS;
    suiteECs.add(probabilityS);
    suiteECs.add(new LengthProbabilityDescription());
    suiteECs.add(new EndlessDescription());
    suiteECs.add(new TimeDescription());
  }

  public void createTestECPane() {
    if (oldTestBox != null) {
      getChildren().remove(oldTestBox);
    } else {
      Text testECLabel = new Text("Test endcondition:");
      add(testECLabel, 0, testY);

      testButton = new Button("...");
      testButton.setOnAction(event -> {
        Stage stage = new Stage();
        stage.setScene(new Scene(chosenTestEC.createEditor(this, stage)));
        stage.show();
      });
      add(testButton, 2, testY);
    }

    ComboBox<ECDescription> ecBox = new ComboBox<>();
    ObservableList<ECDescription> items = ecBox.getItems();
    items.addAll(testECs);
    ecBox.setValue(chosenTestEC);
    ecBox.setOnAction(event1 -> {
      chosenTestEC = ecBox.getValue();
      testButton.setDisable(!chosenTestEC.supportsEditing());
    });
    add(ecBox, 1, testY);
    oldTestBox = ecBox;
    oldTestBox.setMaxWidth(200);
  }

  public void createSuiteECPane() {
    if (oldSuiteBox != null) {
      getChildren().remove(oldSuiteBox);
    } else {
      Text suiteECLabel = new Text("Suite endcondition:");
      add(suiteECLabel, 0, suiteY);

      suiteButton = new Button("...");
      suiteButton.setOnAction(event -> {
        Stage stage = new Stage();
        stage.setScene(new Scene(chosenSuiteEC.createEditor(this, stage)));
        stage.show();
      });
      add(suiteButton, 2, suiteY);
    }

    ComboBox<ECDescription> ecBox = new ComboBox<>();
    ObservableList<ECDescription> items = ecBox.getItems();
    items.addAll(suiteECs);
    ecBox.setValue(chosenSuiteEC);
    ecBox.setOnAction(event1 -> {
      chosenSuiteEC = ecBox.getValue();
      suiteButton.setDisable(!chosenSuiteEC.supportsEditing());
    });
    add(ecBox, 1, suiteY);
    oldSuiteBox = ecBox;
    oldSuiteBox.setMaxWidth(200);
  }

  private void createAlgorithmPane() {
    Text label = new Text("Algorithm:");
    add(label, 0, algoY);
    ComboBox<String> box = new ComboBox<>();
    ObservableList<String> items = box.getItems();
    items.addAll("Random", "Balancing", "Weighted Random", "Weighted Balancing");
    box.setValue("Random");
    add(box, 1, algoY);
  }
}
