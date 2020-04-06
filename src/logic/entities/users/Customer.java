package logic.entities.users;

//import file.handling.SavingData;

import file.handling.SaveData;
import file.handling.dishes.Dish;
import file.handling.tables.Table;
import file.handling.tables.Tables;
import file.handling.tables.TablesList;
import file.handling.users.User;
import gui.windows.TableDoesntExistMsg;
import logic.Time;
import logic.entities.Order;
import logic.entities.Orders;
import logic.entities.restaurant.Reservation;
import logic.entities.restaurant.Reservations;
import logic.reservation.system.ReservationSystem;

import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.List;


public class Customer extends User {

    private ReservationSystem reservationSystem;
    private String name;
    private String userName;
    private String password;
    private Table table;
    private Tables tables;
    private Order order;
    private List<Order> orders;
    private double priceRequiredToPay;
    private Reservation reservation;
    private Time time;
    private HashMap<String, Reservation> reservations;

    public Customer(String userName, String password) {
        super(userName, password);

        reservations = Reservations.getReservations();

        this.userName = userName;
        this.password = password;
        time = new Time();
        reservation = new Reservation();
        this.tables = new Tables();
        this.role = "client";
    }


    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public double getPriceRequiredToPay() {
        return priceRequiredToPay;
    }

    public void setPriceRequiredToPay(double priceRequiredToPay) {
        this.priceRequiredToPay = priceRequiredToPay;
    }

    public Boolean exist(Table table) {
        for (Table table2 : TablesList.getTables()) {
            if (!table2.getReserved() && table2.getNumberOfSeats() == table.getNumberOfSeats() && table2.getSmoking() == table.getSmoking()) {
                return true;
            }
        }
        return false;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Tables getTables() {
        return tables;
    }

    public void setTables(Tables tables) {
        this.tables = tables;
    }

    public void reserveTable(Table table) {
        TableDoesntExistMsg tableDoesntExistMsg = new TableDoesntExistMsg();

        for (Table table1 : TablesList.getTables()) {

            if (!table1.getReserved() && table1.getNumberOfSeats() == table.getNumberOfSeats()) {
                reservation.setCustomerName(userName);
                reservation.setTable(table1);
                setTable(table1);
                return;
            }

        }
    }

    public ReservationSystem getReservationSystem() {
        return reservationSystem;
    }

    public void setReservationSystem(ReservationSystem reservationSystem) {
        this.reservationSystem = reservationSystem;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void placeOrder(List<Dish> dishes, double priceRequiredToPay) throws JAXBException {
        SaveData<Double> revenueData = new SaveData<>();
        if (!reservations.containsKey(userName)) {
            int orderNum = Reservations.getReservationNumber();
            order = new Order(dishes);
            order.orderToString(order);
            order.setOrderNumber(++orderNum);
            order.setOrder(order.getOrder());
            order.setPrices(priceRequiredToPay);
            Orders.addOrder(order);
            reservation.setReservationNumber(orderNum);
            Reservations.setReservationNumber(orderNum);
            reservation.setDate(time.getDate());
            reservation.setOrderString(order.getOrder());
            reservation.setOrder(order);
            reservation.setPrices(priceRequiredToPay);
            reservation.getTable().setReserved(true);
            Reservations.addOrder(order);
            Reservations.addReservation(userName, reservation);
            revenueData.saveData(order.getPrices());
            SaveData saveData = new SaveData();
            saveData.saveData(reservation);
        } else {
            reservation = reservations.get(userName);
            order = new Order(dishes);
            order.orderToString(order);
            order.setOrder(order.getOrder());
            order.setPrices(priceRequiredToPay);
            Orders.addOrder(order);
            reservation.setDate(time.getDate());
            reservation.setOrderString(order.getOrder());
            reservation.setOrder(order);
            revenueData.modifyRevenue(reservation.getPrices());
            reservation.setPrices(priceRequiredToPay);
            Reservations.addOrder(order);
            revenueData.saveData(order.getPrices());
            SaveData saveData = new SaveData();
            saveData.saveData(reservation);
        }
    }
}
