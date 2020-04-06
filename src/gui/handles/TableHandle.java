package gui.handles;

import file.handling.tables.Table;
import file.handling.tables.TablesList;
import file.handling.users.User;
import file.handling.users.UsersHash;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import logic.entities.Order;
import logic.entities.restaurant.Reservation;
import logic.entities.restaurant.Reservations;
import logic.entities.users.Customer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class TableHandle {

    HashMap<String, User> usersHash = UsersHash.getUserData();
    ObservableList<User> users = FXCollections.observableArrayList();
    HashMap<String, Reservation> reservations = Reservations.getReservations();
    User user;
    Reservation reservation;

    public void feedCustomerTable(TableView<Customer> customerTableView) {

        Iterator hmIterator = usersHash.entrySet().iterator();

        while (hmIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry) hmIterator.next();

            user = (User) mapElement.getValue();

            if (user.getRole().equalsIgnoreCase("client")) {
                customerTableView.getItems().add(new Customer(user.getName()));
            }

        }
    }

    public void feedEmployeeTable(TableView<User> employeeTableView) {
        Iterator hmIterator = usersHash.entrySet().iterator();

        while (hmIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry) hmIterator.next();

            user = (User) mapElement.getValue();

            if (!user.getRole().equalsIgnoreCase("client")) {
                employeeTableView.getItems().add(user);
            }

        }
    }

    public void feedTablesTable(TableView<Table> tableTableView) {


        for (Table table : TablesList.getTables()) {
            if (table.getNumberOfSeats() == 2) table.setType("Couple");
            else if (table.getNumberOfSeats() > 2) table.setType("Family");
            else table.setType("Single");
            tableTableView.getItems().add(table);
        }
    }

    public void feedReservationsTable(TableView<Reservation> reservationTableView) {

        Iterator reservationHash = reservations.entrySet().iterator();

        reservationTableView.getItems().clear();

        while (reservationHash.hasNext()) {

            Map.Entry mapElement = (Map.Entry) reservationHash.next();
            reservation = (Reservation) mapElement.getValue();
            if (reservation.getTable().isReserved())
                reservationTableView.getItems().add(reservation);
        }


    }

    public Double feedRevenueTable(TableView<Order> revenueTableView) {
        Iterator revenueHash = reservations.entrySet().iterator();

        Double revenue = 0.0;

        while (revenueHash.hasNext()) {
            Map.Entry mapElement = (Map.Entry) revenueHash.next();
            reservation = (Reservation) mapElement.getValue();
            revenueTableView.getItems().add(reservation.getOrder());
            revenue += reservation.getOrder().getPrices();
        }

        return revenue;
    }

}
