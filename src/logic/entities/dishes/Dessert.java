package logic.entities.dishes;

import file.handling.dishes.Dish;
import logic.TaxesComputation;

public class Dessert extends Dish implements TaxesComputation {

    public Dessert(String name, String type, Double price, int dishNumber) {
        super();
        super.setName(name);
        super.setPrice(price);
        super.setType(type);
        super.setDishNumber(dishNumber);
    }

    @Override
    public double calculateTotalPrice() {
        return (this.getDishNumber() * this.getPrice()) * (1.2);
    }
}
