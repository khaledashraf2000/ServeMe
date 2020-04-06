package gui.windows;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TableDoesntExistMsg {
    Stage stage;
    Scene msgScene;

    public void prepareMsg() {
        stage = new Stage();
        Label alertMsg = new Label("Table doesn't exist");
        HBox root = new HBox(alertMsg);
        alertMsg.setAlignment(Pos.CENTER);
        root.setAlignment(Pos.CENTER);
        msgScene = new Scene(root, 300, 100);
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getMsgScene() {
        return msgScene;
    }

    public void setMsgScene(Scene msgScene) {
        this.msgScene = msgScene;
    }

    public void displayMsg() {
        prepareMsg();
        stage.setScene(msgScene);
        stage.show();
    }


}
