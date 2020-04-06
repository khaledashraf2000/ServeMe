package logic.entities.restaurant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.entities.Order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;

@XmlRootElement(name = "reservations")
@XmlAccessorType(XmlAccessType.FIELD)
public class Reservations {

    static int reservationNumber;

    @XmlElement(name = "reservation")
    private static HashMap<String, Reservation> reservations = new HashMap<>();

    @XmlElement(name = "orderList")
    private static ObservableList<Order> orders = FXCollections.observableArrayList();

    private int lastReservationNumber;

    public static void addOrder(Order order) {
        orders.add(order);
    }

    public static int getReservationNumber() {
        return reservationNumber;
    }

    public static void setReservationNumber(int reservationNumber1) {
        reservationNumber = reservationNumber1;
    }

    public static ObservableList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ObservableList<Order> orders) {
        Reservations.orders = orders;
    }

    public static void addReservation(String name, Reservation reservation) {
        reservations.put(name, reservation);
    }

    public static void removeReservation(String name) {
        reservations.remove(name);
    }

    public static void removeOrder(Reservation reservation) {
        orders.remove(reservation.getOrder());
    }

    public static HashMap<String, Reservation> getReservations() {
        return reservations;
    }

}
