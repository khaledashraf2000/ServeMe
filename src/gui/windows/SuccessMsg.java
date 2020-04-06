package gui.windows;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SuccessMsg {

    Stage successStage = new Stage();
    Scene successScene;
    Label successMsg = new Label("Order is Submitted");
    HBox hBox = new HBox(successMsg);

    public void displayScene() {
        successMsg.setAlignment(Pos.CENTER);
        successScene = new Scene(hBox, 340, 130);
        hBox.setAlignment(Pos.CENTER);
        successStage.setScene(successScene);
        successStage.setTitle("Success message");
        successStage.show();
    }

}
