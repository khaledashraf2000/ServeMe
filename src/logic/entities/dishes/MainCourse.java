package logic.entities.dishes;

import file.handling.dishes.Dish;
import logic.TaxesComputation;

public class MainCourse extends Dish implements TaxesComputation {

    public MainCourse(String name, String type, Double price, int dishNumber) {
        super();
        super.setName(name);
        super.setPrice(price);
        super.setType(type);
        super.setDishNumber(dishNumber);
    }

    public double calculateTotalPrice() {
        return (this.getPrice() * this.getDishNumber()) * (1.15);
    }
}
