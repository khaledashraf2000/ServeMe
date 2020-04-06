package logic.entities.users.employee;

import file.handling.dishes.Dish;
import file.handling.dishes.DishesHash;
import file.handling.dishes.ListOfDishes;
import gui.tables.OrderTable;
import javafx.scene.control.TableView;
import logic.entities.Order;
import logic.entities.restaurant.Reservation;

public class Cook extends Employee {

    OrderTable orderTable;
    Order order;

    public Cook(String userName, String role, double salary, String password, String name) {
        super(userName, role, salary, password, name);
    }

    public TableView<Reservation> viewOrders() {

        orderTable = new OrderTable();
        orderTable.prepareTable();

        return orderTable.getTable();
    }

    public void addDish(Dish dish) {
        ListOfDishes.getDishes().add(dish);
        DishesHash.getDishesHash().put(dish.getName(), dish);
    }
}
