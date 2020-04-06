package gui.windows;

import file.handling.dishes.Dish;
import file.handling.dishes.ListOfDishes;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;

import java.util.List;

public class DishesList {

    ListView<String> dishesList = new ListView<>();
    VBox root;

    public void prepareList(List<Dish> dishes) {
        for (Dish dish : ListOfDishes.getDishes()) {
            dishesList.getItems().add(dish.getName());
        }

        dishesList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        root = new VBox(dishesList);
        root.setAlignment(Pos.CENTER);
    }

    public VBox getRoot() {
        return root;
    }

    public ListView<String> getDishesList() {
        return dishesList;
    }
}
