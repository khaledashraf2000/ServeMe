package gui.windows;

import file.handling.dishes.ListOfDishes;
import file.handling.tables.Table;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.entities.users.Customer;

import java.util.ArrayList;
import java.util.List;

public class TableInput {

    VBox tableLayout;
    MakeOrderScene makeOrderScene;

    public void prepareScene(Customer customer, VBox right) {

        makeOrderScene = new MakeOrderScene(customer);
        DishesList dishesList = new DishesList();
        Table table = new Table();
        Label seatsLabel = new Label("Choose number of seats");
        Label chooseDishLabel = new Label("Choose Dish");

        seatsLabel.setPadding(new Insets(20));
        chooseDishLabel.setId("title");

        Button confirm = new Button("Confirm");
        Button seePrices = new Button("See Prices");
        Button makeOrder = new Button("Make Order");
        Button submitOrder = new Button("Submit Order");

        List<HBox> dataEntry = new ArrayList<>();

        HBox buttons = new HBox(seePrices, makeOrder);
        HBox hBox = new HBox();

        buttons.setAlignment(Pos.BOTTOM_CENTER);

        buttons.setSpacing(20);
        confirm.getStyleClass().add("confirm");
        seePrices.getStyleClass().add("confirm");
        makeOrder.getStyleClass().add("confirm");

        Spinner<Integer> seatsNumberSpinner = new Spinner<>();

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, 1);

        seatsNumberSpinner.setValueFactory(valueFactory);

        GridPane grid = new GridPane();

        grid.add(seatsLabel, 0, 0);
        grid.add(seatsNumberSpinner, 1, 0);

        grid.setHgap(10);

        seatsNumberSpinner.setPadding(new Insets(120));

        CheckBox smoking = new CheckBox("Smoking");

        VBox root = new VBox();

        root.getChildren().addAll(grid, smoking, confirm);

        root.setAlignment(Pos.CENTER);

        root.setSpacing(50);
        tableLayout = root;

        Bill bill = new Bill();

        SubmitOrderScene submitOrderScene = new SubmitOrderScene();

        seatsNumberSpinner.setEditable(true);

        confirm.setOnAction(e -> {
            if (smoking.isSelected()) {
                table.setSmoking(true);
            } else {
                table.setSmoking(false);
            }
            table.setNumberOfSeats(seatsNumberSpinner.getValue());
            if (customer.exist(table)) {

                right.getChildren().clear();
                customer.reserveTable(table);
                dishesList.prepareList(ListOfDishes.getDishes());
                hBox.getChildren().addAll(dishesList.getRoot());
                hBox.setAlignment(Pos.TOP_RIGHT);
                dishesList.getRoot().setAlignment(Pos.TOP_RIGHT);

                hBox.setSpacing(4);
                buttons.setAlignment(Pos.BOTTOM_CENTER);
                buttons.setPadding(new Insets(100));
                chooseDishLabel.setAlignment(Pos.TOP_CENTER);
                right.getChildren().addAll(chooseDishLabel, hBox, buttons);
                chooseDishLabel.setPadding(new Insets(100));
                dishesList.getRoot().prefWidthProperty().bind(right.widthProperty());

            } else Alert.display("Table doesn't exists");
        });

        seePrices.setOnAction(e -> {

            bill.clearOldBill();

            for (String choosenDishes : dishesList.getDishesList().getSelectionModel().getSelectedItems()) {
                bill.prepareBill(choosenDishes);
            }
            bill.displayBill();
        });

        makeOrder.setOnAction(e -> {
            makeOrderScene.prepareScene(dishesList);

            right.getChildren().setAll(makeOrderScene.getScene());
        });
    }

    public VBox getTableLayout() {
        return tableLayout;
    }

}

