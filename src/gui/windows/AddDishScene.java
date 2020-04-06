package gui.windows;

import file.handling.SaveData;
import file.handling.dishes.Dish;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import logic.entities.users.employee.Cook;

import javax.xml.bind.JAXBException;

public class AddDishScene {

    VBox addDishBox;
    Cook cook;

    public AddDishScene(Cook cook) {
        this.cook = cook;
    }

    public void prepareScene() throws JAXBException {

        SaveData<Dish> saveDish = new SaveData<>();

        Button addDishButton = new Button("Add Dish");

        Label dishNameLabel = new Label("Dish Name");
        Label dishPriceLabel = new Label("Dish Price");
        Label dishTypeLabel = new Label("Dish Type");
        TextField dishNameField = new TextField();
        TextField dishPriceField = new TextField();
        ComboBox<String> dishType = new ComboBox<>();
        dishType.getItems().addAll("Main Course", "Appetizer", "Dessert");
        dishPriceField.setPromptText("enter dish price");
        dishNameField.setPromptText("enter dish name");

        GridPane gridPane = new GridPane();

        gridPane.add(dishNameLabel, 0, 0);
        gridPane.add(dishNameField, 1, 0);
        gridPane.add(dishPriceLabel, 0, 1);
        gridPane.add(dishPriceField, 1, 1);
        gridPane.add(dishTypeLabel, 0, 2);
        gridPane.add(dishType, 1, 2);

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        addDishButton.setOnAction(e -> {
            String dishName = dishNameField.getText();
            String dishTypeString = dishType.getValue();
            Double price = 0.0;

            if(dishNameField.getText().isEmpty()|| dishType.getValue() == null || dishPriceField.getText().isEmpty()) {
                Alert.display("Please fill all fields");
            } else  {
                if(dishTypeString.equalsIgnoreCase("main course")) {
                    dishTypeString = "main_course";
                } else if(dishTypeString.equalsIgnoreCase("appetizer")) {
                    dishTypeString = "appetizer";
                } else {
                    dishTypeString = "desert";
                }

                try{
                    price = new Double(Double.parseDouble(dishPriceField.getText()));
                    Dish dish = new Dish(dishName, price, dishTypeString);
                    cook.addDish(dish);
                    saveDish.saveData(dish);
                    Alert.display("Dish is added successfully");
                } catch (NumberFormatException | JAXBException a) {
                    Alert.display("Please enter a valid price");
                }
            }

        });

        addDishBox = new VBox(gridPane, addDishButton);
        addDishBox.setSpacing(20);
        addDishBox.setAlignment(Pos.CENTER);
        addDishBox.setMinWidth(810);

    }

    public VBox getAddDishBox() {
        return this.addDishBox;
    }
}
