package gui.windows;

import gui.handles.SignUpHandle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;

public class SignUpScene {

    Scene scene;
    Stage primaryStage;
    SignUpHandle signUpHandle;

    public SignUpScene(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void prepareScene() {

        //Text fields
        TextField nameTextField = new TextField();
        ComboBox roleField = new ComboBox<>();
        roleField.getItems().addAll("Client", "Waiter", "Cooker", "Manager");
        TextField usernameTextField = new TextField();
        PasswordField passwordTextField = new PasswordField();
        PasswordField confirmPasswordTextField = new PasswordField();

        //Labels
        Label nameLabel = new Label("Name\t\t");
        Label roleLabel = new Label("Role\t\t\t");
        Label usernameLabel = new Label("Username\t");
        Label passwordLabel = new Label("Password\t\t");
        Label confirmPasswordLabel = new Label("Confirm\t\t");

        //Button
        Button signUpButton = new Button("Sign up");
        Button backButton = new Button("", new ImageView("resources/back-arrow.png"));

        //Button handling
        signUpButton.setOnAction(e -> {
            signUpHandle = new SignUpHandle(primaryStage);
            try {
                signUpHandle.signUpButtonHandle(nameTextField.getText(), (String) roleField.getValue(), usernameTextField.getText(), passwordTextField.getText(), confirmPasswordTextField.getText());
            } catch (JAXBException ex) {
                ex.printStackTrace();
            }
        });
        backButton.setOnAction(e -> {
            signUpHandle = new SignUpHandle(primaryStage);
            try {
                signUpHandle.backButtonHandle();
            } catch (JAXBException ex) {
                ex.printStackTrace();
            }
        });

        //Layouts
        HBox nameHbox = new HBox(5);
        nameHbox.getChildren().addAll(nameLabel, nameTextField);
        nameHbox.setAlignment(Pos.CENTER);
        HBox roleHbox = new HBox(5);
        roleHbox.getChildren().addAll(roleLabel, roleField);
        roleHbox.setAlignment(Pos.CENTER);
        HBox usernameHbox = new HBox(5);
        usernameHbox.getChildren().addAll(usernameLabel, usernameTextField);
        usernameHbox.setAlignment(Pos.CENTER);
        HBox passwordHbox = new HBox(5);
        passwordHbox.getChildren().addAll(passwordLabel, passwordTextField);
        passwordHbox.setAlignment(Pos.CENTER);
        HBox confirmPasswordHbox = new HBox(5);
        confirmPasswordHbox.getChildren().addAll(confirmPasswordLabel, confirmPasswordTextField);
        confirmPasswordHbox.setAlignment(Pos.CENTER);
        VBox fields = new VBox(10);
        fields.getChildren().addAll(nameHbox, roleHbox, usernameHbox, passwordHbox, confirmPasswordHbox);
        VBox vBox = new VBox(30);
        vBox.getChildren().addAll(fields, signUpButton);
        HBox hBox = new HBox(325);
        hBox.getChildren().addAll(backButton, vBox);
        StackPane root = new StackPane();
        root.getChildren().add(hBox);

        //Styling
        backButton.setStyle("-fx-background-color: #0F4C81");
        backButton.setPadding(new Insets(20, 0, 0, 20));
        backButton.setAlignment(Pos.TOP_LEFT);
        roleField.setMinWidth(187);
        signUpButton.setAlignment(Pos.CENTER);
        signUpButton.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
        nameLabel.setStyle("-fx-text-fill: white");
        passwordLabel.setStyle("-fx-text-fill: white");
        roleLabel.setStyle("-fx-text-fill: white");
        confirmPasswordLabel.setStyle("-fx-text-fill: white");
        usernameLabel.setStyle("-fx-text-fill: white");
        fields.setMaxWidth(300);
        fields.setAlignment(Pos.CENTER);
        vBox.setMaxWidth(300);
        vBox.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: linear-gradient(#0F4C81, #1460A3)");
        root.setAlignment(Pos.CENTER);

        //Final
        this.scene = new Scene(root, 1010, 674);
        scene.getStylesheets().add("dashboards-styling.css");
    }

}
