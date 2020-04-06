package file.handling;

import file.handling.dishes.Dish;
import file.handling.dishes.DishesHash;
import file.handling.dishes.ListOfDishes;
import file.handling.tables.Table;
import file.handling.tables.TablesList;
import file.handling.users.UsersHash;
import logic.entities.Orders;
import logic.entities.restaurant.Reservations;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileHandling {

    TablesList tablesList;
    UsersHash usersHash;
    DishesHash dishesHash;

    JAXBContext jaxbContext = JAXBContext.newInstance(Restaurant.class);
    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

    Restaurant restaurant = (Restaurant) unmarshaller.unmarshal(new File("data.xml"));

    public FileHandling() throws JAXBException {

    }

    public Restaurant fetchData() {
        return this.restaurant;
    }

    public void loadData(TablesList tablesList, UsersHash usersHash, DishesHash dishesHash, ListOfDishes dishes) {

        Reservations.setReservationNumber(Reservations.getReservations().size());

        this.tablesList = tablesList;
        this.usersHash = usersHash;
        this.dishesHash = dishesHash;

        TablesList.loadTableData(restaurant.getTables());
        usersHash.loadData(restaurant.getUsers());
        dishesHash.loadDishesData(restaurant.getDishes());

        Orders.setSize(restaurant.getDishes().getDishes().size());

        for (Dish dish : restaurant.getDishes().getDishes()) {
            dishes.addDish(dish);
        }

        for (Table table : restaurant.getTables().getTables()) {
            if (table.getNumberOfSeats() == 2) table.setType("Couple");
            else if (table.getNumberOfSeats() > 2) table.setType("Family");
            else table.setType("Single");
        }

        for (Table table : restaurant.getTables().getTables()) {
            if (table.getNumberOfSeats() == 2) table.setType("Couple");
            else if (table.getNumberOfSeats() > 2) table.setType("Family");
            else table.setType("Single");
        }
    }


    public TablesList getTablesList() {
        return tablesList;
    }

    public void setTablesList(TablesList tablesList) {
        this.tablesList = tablesList;
    }

    public UsersHash getUsersHash() {
        return usersHash;
    }

    public void setUsersHash(UsersHash usersHash) {
        this.usersHash = usersHash;
    }

    public DishesHash getDishesHash() {
        return dishesHash;
    }

    public void setDishesHash(DishesHash dishesHash) {
        this.dishesHash = dishesHash;
    }

    public JAXBContext getJaxbContext() {
        return jaxbContext;
    }

    public void setJaxbContext(JAXBContext jaxbContext) {
        this.jaxbContext = jaxbContext;
    }

    public Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
