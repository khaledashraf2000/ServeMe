package logic.entities.dishes;

import file.handling.dishes.Dish;
import logic.TaxesComputation;

public class Appetizers extends Dish implements TaxesComputation {

    public Appetizers(String name, String type, Double price, int dishNumber) {
        super();
        super.setName(name);
        super.setPrice(price);
        super.setType(type);
        super.setDishNumber(dishNumber);
    }

    @Override
    public double calculateTotalPrice() {
        return (this.getPrice() * this.getDishNumber()) * (1.1);
    }


}
