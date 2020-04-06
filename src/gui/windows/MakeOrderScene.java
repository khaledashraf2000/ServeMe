package gui.windows;

import file.handling.dishes.Dish;
import file.handling.dishes.DishesHash;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.entities.users.Customer;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MakeOrderScene {

    VBox orderList = new VBox();
    List<HBox> dataEntry = new ArrayList<>();
    Button submitOrder = new Button("Submit Order");
    Customer customer;
    HashMap<String, Dish> dishHashMap = DishesHash.getDishesHash();

    public MakeOrderScene(Customer customer) {
        this.customer = customer;

    }

    public Button getSubmitOrder() {
        return submitOrder;
    }

    public void setSubmitOrder(Button submitOrder) {
        this.submitOrder = submitOrder;
    }

    public void prepareScene(DishesList dishesList) {

        Spinner<Integer> spinnerItem;
        HBox temp;
        List<Dish> dishes = new ArrayList<>();
        Label dishNameLabel;
        Label titleLabel = new Label("Make your order");
        titleLabel.setId("title");
        HBox titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);
        HBox buttonBox = new HBox(submitOrder);
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonBox.setPadding(new Insets(100));
        buttonBox.setMaxHeight(10);
        submitOrder.getStyleClass().add("confirm");
        titleLabel.setPadding(new Insets(50));
        orderList.getChildren().add(titleBox);
        for (String dishName : dishesList.getDishesList().getSelectionModel().getSelectedItems()) {
            spinnerItem = new Spinner<>();
            SpinnerValueFactory<Integer> tempFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
            spinnerItem.setValueFactory(tempFactory);
            dishNameLabel = new Label(dishName);
            temp = new HBox(dishNameLabel, spinnerItem);
            temp.setAlignment(Pos.CENTER);
            temp.setSpacing(30);
            orderList.getChildren().add(temp);
            dataEntry.add(temp);
        }
        orderList.getChildren().add(buttonBox);
        submitOrder.setPadding(new Insets(50));
        buttonBox.setAlignment(Pos.CENTER);
        orderList.setSpacing(15);

        submitOrder.setOnAction(e -> {
            List<Dish> dishes1 = new ArrayList<>();
            Dish dish;
            Label label;
            double price = 0;
            Spinner<Integer> dishQuantity;
            String dishName;
            for (HBox tempBox : dataEntry) {
                label = (Label) tempBox.getChildren().get(0);
                dishName = label.getText();
                dish = dishHashMap.get(dishName);

                dishQuantity = (Spinner<Integer>) tempBox.getChildren().get(1);

                dish.setDishNumber(dishQuantity.getValue());

                price += dish.calculateTotalPrice();
                dishes1.add(dish);
            }

            try {
                customer.placeOrder(dishes1, price);
            } catch (JAXBException ex) {
                ex.printStackTrace();
            }
            Alert.display("Reservation successfully added");
        });
    }


    public VBox getScene() {
        return orderList;
    }
}


