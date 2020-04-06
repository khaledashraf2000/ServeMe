package file.handling.dishes;

import logic.entities.dishes.Appetizers;
import logic.entities.dishes.Dessert;
import logic.entities.dishes.MainCourse;

import java.util.HashMap;

public class DishesHash {

    static HashMap<String, Dish> dishesHash = new HashMap<>();

    public static HashMap<String, Dish> getDishesHash() {
        return dishesHash;
    }

    public void addDish(Dish dish) {
        dishesHash.put(dish.getName(), dish);
    }

    public void loadDishesData(Dishes dishes) {
        for (Dish dish : dishes.getDishes()) {
            if (dish.getType().equalsIgnoreCase("appetizer")) {
                Appetizers appetizers = new Appetizers(dish.getName(), dish.getType(), dish.getPrice(), dish.getDishNumber());
                addDish(appetizers);
            } else if (dish.getType().equalsIgnoreCase("desert")) {
                Dessert dessert = new Dessert(dish.getName(), dish.getType(), dish.getPrice(), dish.getDishNumber());
                addDish(dessert);
            } else {
                MainCourse mainCourse = new MainCourse(dish.getName(), dish.getType(), dish.getPrice(), dish.getDishNumber());
                addDish(mainCourse);
            }
        }
    }
}
