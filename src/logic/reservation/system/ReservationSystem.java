package logic.reservation.system;

import logic.entities.Order;
import logic.entities.Orders;

public class ReservationSystem {
    private static Orders allOrders;
    private int tableNumber;
    private int chairsNumber;
    private String time;

    public ReservationSystem(int tableNumber, int chairsNumber, String time) {
        this.tableNumber = tableNumber;
        this.chairsNumber = chairsNumber;
        this.time = time;
    }

    public static Orders getAllOrders() {
        return allOrders;
    }

    public static void setAllOrders(Orders orders) {
        allOrders = orders;
    }

    public static void placeOrder(Order order) {
        Orders.addOrder(order);
    }

    public static Orders getOrders() {
        return allOrders;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getChairsNumber() {
        return chairsNumber;
    }

    public void setChairsNumber(int chairsNumber) {
        this.chairsNumber = chairsNumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
