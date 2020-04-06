package gui.windows.dashboards;

import file.handling.dishes.ListOfDishes;
import file.handling.users.User;
import gui.handles.Window;
import gui.windows.DishesList;
import gui.windows.MakeOrderScene;
import gui.windows.TableInput;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import logic.entities.restaurant.Reservation;
import logic.entities.restaurant.Reservations;
import logic.entities.users.Customer;

import java.util.HashMap;

public class CustomerDashboard {

    Stage customerStage;
    TableInput tableInputScene = new TableInput();
    Scene customerScene;
    HashMap<String, Reservation> reservationHashMap = Reservations.getReservations();

    Button reserveButton = new Button("Reserve a Table");
    Button logOutButton = new Button("Logout");

    VBox left = new VBox(reserveButton, logOutButton);
    VBox right = new VBox();
    HBox root = new HBox(left, right);

    public void prepareScene(Stage primaryStage, Scene loginScene, User user) {

        MakeOrderScene makeOrderScene = new MakeOrderScene((Customer) user);

        DishesList dishesList = new DishesList();
        Button seePrices = new Button("See Prices");
        Button changeOrder = new Button("Change your Order");
        Label chooseDishLabel = new Label("Choose Dish");
        HBox hBox = new HBox();
        HBox buttons = new HBox(seePrices, changeOrder);
        buttons.setSpacing(20);
        Customer customer = (Customer) user;

        reserveButton.prefHeightProperty().bind(left.heightProperty());
        logOutButton.prefHeightProperty().bind(left.heightProperty());
        reserveButton.prefWidthProperty().bind(left.widthProperty());
        logOutButton.prefWidthProperty().bind(left.widthProperty());
        left.prefWidthProperty().bind(root.widthProperty().multiply(0.20));
        left.setMinWidth(200);
        right.prefWidthProperty().bind(root.widthProperty().multiply(0.80));
        right.prefHeightProperty().bind(root.heightProperty());
        right.setMinWidth(810);

        Text text = new Text("Customer Dashboard");
        text.setTextAlignment(TextAlignment.CENTER);
        Font font = Font.loadFont(ManagerDashboard.class.getResourceAsStream("/resources/fonts/greeting-font.TTF"), 80);
        text.setFont(font);
        VBox greetingBox = new VBox(text);
        greetingBox.setAlignment(Pos.CENTER);
        greetingBox.setPadding(new Insets(300, 0, 0, 0));
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        text.setEffect(ds);
        text.setId("manager");

        right.getChildren().add(greetingBox);
        if (customerScene == null) customerScene = new Scene(root, 1010, 674);
        customerStage = primaryStage;
        customerStage.setScene(customerScene);
        customerScene.getStylesheets().add("dashboards-styling.css");

        reserveButton.setOnAction(e -> {
            if (reservationHashMap.containsKey(customer.getUserName())) {
                int reservationNumber;
                Reservation reservation;
                right.getChildren().clear();
                dishesList.prepareList(ListOfDishes.getDishes());
                hBox.getChildren().addAll(dishesList.getRoot());
                hBox.setAlignment(Pos.TOP_RIGHT);
                dishesList.getRoot().setAlignment(Pos.TOP_RIGHT);
                hBox.setSpacing(4);
                buttons.setAlignment(Pos.BOTTOM_CENTER);
                buttons.setPadding(new Insets(100));
                chooseDishLabel.setAlignment(Pos.TOP_CENTER);
                chooseDishLabel.setPadding(new Insets(100));
                dishesList.getRoot().prefWidthProperty().bind(right.widthProperty());
                right.getChildren().addAll(chooseDishLabel, hBox, buttons);
                customerStage.setScene(customerScene);
            } else {
                right.getChildren().clear();
                tableInputScene.prepareScene(customer, right);
                right.getChildren().addAll(tableInputScene.getTableLayout());
                right.setAlignment(Pos.CENTER);
                customerStage.setScene(customerScene);
            }

        });

        changeOrder.setOnAction(e -> {
            makeOrderScene.prepareScene(dishesList);

            right.getChildren().setAll(makeOrderScene.getScene());
        });

        logOutButton.setOnAction(e -> {
            customerStage.setScene(loginScene);
            customerStage.show();
            Window.setOnCenter(customerStage);
            customerStage.setTitle("ServeMe");
        });

    }


    public void customerSceneDisplay() {
        customerStage.setTitle("ServeMe");
        customerStage.show();
        Window.setOnCenter(customerStage);
    }
}