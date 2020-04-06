package gui.tables;

import file.handling.dishes.Dish;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class DishesTable {

    Stage dishStage = new Stage();
    VBox vbox = new VBox();
    Scene dishScene;
    private TableView<Dish> dishItems = new TableView<>();
    private TableColumn<Dish, String> nameColumn = new TableColumn("Name");
    private TableColumn<Dish, String> typeColumn = new TableColumn("Dish Type");
    private TableColumn<Dish, Double> priceColumn = new TableColumn("Price");

    public void prepareTable(ObservableList<Dish> dishes) {
        nameColumn.impl_setWidth(270);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        typeColumn.impl_setWidth(270);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        priceColumn.impl_setWidth(270);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        dishItems.getItems().addAll(dishes);
        dishItems.getColumns().addAll(nameColumn, typeColumn, priceColumn);

    }

    public TableView<Dish> getTable() {
        return dishItems;
    }

}
