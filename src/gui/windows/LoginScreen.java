package gui.windows;

import file.handling.users.User;
import gui.handles.Window;
import gui.windows.dashboards.CookDashboard;
import gui.windows.dashboards.CustomerDashboard;
import gui.windows.dashboards.ManagerDashboard;
import gui.windows.dashboards.WaiterDashboard;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import logic.DetermineDashboard;

import javax.xml.bind.JAXBException;
import java.util.HashMap;

public class LoginScreen {

    Scene loginScene;
    HashMap<String, User> users;
    DetermineDashboard determineDashboard;
    CookDashboard cookDashboard;
    CustomerDashboard customerDashboard = new CustomerDashboard();
    WaiterDashboard waiterDashboard;
    ManagerDashboard managerDashboard;
    Stage primaryStage;
    SignUpScene signUpScene;

    public LoginScreen(HashMap<String, User> users, Stage primaryStage) {
        this.users = users;
        this.primaryStage = primaryStage;
    }

    public void prepareScene() {

        //Labels:
        Label welcomeLabel = new Label("Welcome to ServeMe");
        Label userNameLabel = new Label("username ");
        Label passwordLabel = new Label("password ");
        userNameLabel.setPadding(new Insets(0, 0, 0, 10));
        passwordLabel.setPadding(new Insets(0, 0, 0, 10));

        //Input Fields:
        TextField userNameTxtField = new TextField();
        PasswordField password = new PasswordField();

        //Buttons:
        Button loginButton = new Button("Login");

        loginButton.setOnAction(e -> {
            String name = userNameTxtField.getText();
            String pass = password.getText();
            determineDashboard = new DetermineDashboard(primaryStage, name, pass, loginScene);
            try {
                determineDashboard.displayUsersDashboard();
            } catch (JAXBException ex) {
                ex.printStackTrace();
            }
        });
        loginButton.setDefaultButton(true);
        loginButton.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                loginButton.fire();
            }
        });


        //Styling
        welcomeLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; ");

        VBox fields = new VBox();
        fields.getChildren().addAll(userNameTxtField, password);
        fields.setSpacing(10);
        VBox leftVbox = new VBox(welcomeLabel, fields, loginButton);
        leftVbox.setSpacing(20d);
        leftVbox.setPrefWidth(300);
        leftVbox.setPrefHeight(300);
        leftVbox.setAlignment(Pos.CENTER);
        leftVbox.setPadding(new Insets(0, 30, 0, 30));
        leftVbox.setStyle("-fx-background-color: #0F4C81");
        VBox left = new VBox();
        left.setAlignment(Pos.CENTER);
        left.setStyle("-fx-background-color: #0F4C81");
        left.getChildren().addAll(leftVbox);
        HBox root = new HBox();
        HBox right = new HBox();
        VBox rightComponents = new VBox();
        rightComponents.setAlignment(Pos.CENTER);
        Text rightText = new Text("ServeMe");
        right.getStylesheets().add("dashboards-styling.css");
        right.setId("revenue");
        rightText.setStyle("-fx-font-size: 60; -fx-font-style: italic; -fx-font-weight: bold; -fx-fill: #0F4C81;");
        right.setAlignment(Pos.CENTER);
        rightComponents.getChildren().add(rightText);
        right.setPrefWidth(550);
        right.getChildren().add(rightComponents);
        right.setStyle("-fx-background-color: linear-gradient(#F5B895, #A58D7F)");
        root.getChildren().addAll(left, right);
        root.setPadding(new Insets(0, 0, 0, 0));

        userNameTxtField.setPromptText("username");
        password.setPromptText("password");

        loginScene = new Scene(root, 800, 600);
        cookDashboard = new CookDashboard(primaryStage, loginScene);

    }


    public Scene getScene() {
        return loginScene;
    }
}
