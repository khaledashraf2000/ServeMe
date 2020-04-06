package file.handling.dishes;

import java.util.ArrayList;
import java.util.List;

public class ListOfDishes {

    private static List<Dish> dishes = new ArrayList<>();

    public static List<Dish> getDishes() {
        return dishes;
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }
}
