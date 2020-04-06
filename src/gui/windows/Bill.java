package gui.windows;

import file.handling.dishes.Dish;
import file.handling.dishes.DishesHash;
import gui.tables.DishesTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Bill {

    Stage stage;
    ObservableList<Dish> choosenDishes = FXCollections.observableArrayList();
    Scene billScene;
    DishesTable dishes;

    public void clearOldBill() {
        choosenDishes.clear();
    }

    public void prepareBill(String name) {
        choosenDishes.add(DishesHash.getDishesHash().get(name));
    }

    public void displayBill() {
        dishes = new DishesTable();
        dishes.prepareTable(choosenDishes);
        VBox root = new VBox();
        root.getChildren().add(dishes.getTable());
        billScene = new Scene(root, 600, 200);
        stage = new Stage();
        stage.setScene(billScene);
        stage.show();
    }


    public Scene getBillScene() {
        return billScene;
    }
}
