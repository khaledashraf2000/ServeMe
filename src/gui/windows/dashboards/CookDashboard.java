package gui.windows.dashboards;

import gui.handles.Window;
import gui.windows.AddDishScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import logic.entities.Order;
import logic.entities.restaurant.Reservation;
import logic.entities.users.employee.Cook;

import javax.xml.bind.JAXBException;

public class CookDashboard {

    AnchorPane anchor;
    Scene cookDashboard;
    Stage primaryStage;
    Scene loginScene;
    AddDishScene addDishScene;

    Cook cooker = new Cook("mostafa", "cook", 500, "mostafa", "mostafa");

    public CookDashboard(Stage primaryStage, Scene loginScene) {
        this.primaryStage = primaryStage;
        this.loginScene = loginScene;
        addDishScene = new AddDishScene(cooker);
    }

    public void setLoginScene(Scene loginScene) {
        this.loginScene = loginScene;
    }

    public void prepareDashboard() {

        Text text = new Text("Cook Dashboard");
        text.setTextAlignment(TextAlignment.CENTER);
        Font font = Font.loadFont(ManagerDashboard.class.getResourceAsStream("/resources/fonts/greeting-font.TTF"), 80);
        text.setFont(font);
        DropShadow ds = new DropShadow();
        VBox greetingBox = new VBox(text);
        greetingBox.setAlignment(Pos.CENTER);
        greetingBox.setPadding(new Insets(250, 0, 0, 200));
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        text.setEffect(ds);
        text.setId("manager");

        TableView<Reservation> tableView = cooker.viewOrders();

        Button viewOrderButton = new Button("  Orders", new ImageView("resources/orders-icon.png"));
        Button logOut = new Button("Log Out");

        //add dish feature:

        Button addDishButton = new Button("Add Dish");

        VBox left = new VBox(viewOrderButton, addDishButton, logOut);
        left.setMinHeight(200);
        VBox right = new VBox();
        right.getChildren().add(greetingBox);

        HBox root = new HBox();

        viewOrderButton.setAlignment(Pos.CENTER);
        logOut.setAlignment(Pos.CENTER);
        left.setAlignment(Pos.CENTER);

        root.getStylesheets().add("dashboards-styling.css");

        left.setId("left");
        root.getChildren().addAll(left, right);

        left.prefWidthProperty().bind(root.widthProperty().multiply(0.20));
        right.prefWidthProperty().bind(root.widthProperty().multiply(0.80));
        viewOrderButton.prefWidthProperty().bind(left.widthProperty());
        viewOrderButton.prefHeightProperty().bind(left.heightProperty());
        logOut.prefHeightProperty().bind(left.heightProperty());
        logOut.prefWidthProperty().bind(left.widthProperty());
        addDishButton.prefHeightProperty().bind(left.heightProperty());
        addDishButton.prefWidthProperty().bind(left.widthProperty());


        logOut.setOnAction(e -> {
            primaryStage.setScene(loginScene);
            primaryStage.show();
            primaryStage.setTitle("ServeMe");
            Window.setOnCenter(primaryStage);
        });

        addDishButton.setOnAction(e -> {
            try {
                addDishScene.prepareScene();
            } catch (JAXBException ex) {
                ex.printStackTrace();
            }
            root.getChildren().setAll(left, addDishScene.getAddDishBox());
        });

        viewOrderButton.setOnAction(e -> {
            right.getChildren().setAll(tableView);
            tableView.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.75));
            tableView.prefHeightProperty().bind(primaryStage.heightProperty());
            root.getChildren().setAll(left, right);
            primaryStage.setScene(cookDashboard);

        });

        cookDashboard = new Scene(root, 1010, 674);
        primaryStage.setScene(cookDashboard);
        primaryStage.setTitle("ServeMe");
    }

    public void displayCookDashboard() {
        prepareDashboard();
        primaryStage.show();
        Window.setOnCenter(primaryStage);
    }

    public Scene getScene() {
        return cookDashboard;
    }

}
