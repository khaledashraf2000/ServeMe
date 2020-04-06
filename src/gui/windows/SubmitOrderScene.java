package gui.windows;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SubmitOrderScene {
    Stage submitOrderStage = new Stage();
    Scene submitScene;
    List<Label> labels = new ArrayList<>();
    List<Spinner<Integer>> spinners = new ArrayList<>();
    List<HBox> orders = new ArrayList<>();

    public void prepareScene(ObservableList<String> selectedItems) {
        for (String name : selectedItems) {
            labels.add(new Label(name));
            spinners.add(new Spinner<>());
        }

    }
}
