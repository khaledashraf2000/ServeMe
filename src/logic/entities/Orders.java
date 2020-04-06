package logic.entities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collections;
import java.util.Comparator;

public class Orders {
    static ObservableList<Order> orders = FXCollections.observableArrayList();
    static int orderNumber = 0;
    private static int size;
    Boolean orderExist;

    public static void setSize(int size1) {
        size = size1;
    }

    public static void addOrder(Order order) {
        orders.add(order);
    }

    public static ObservableList<Order> getOrders() {
        return orders;
    }

    public static void setOrders(ObservableList<Order> orders) {
        Orders.orders = orders;
    }

    public int getOrderNumber(Order order) {
        return orders.indexOf(order);
    }

    public Boolean getOrderExist() {
        return orderExist;
    }

    public void setOrderExist(Boolean orderExist) {
        this.orderExist = orderExist;
    }

    public void deleteOrder(int orderNumber) {
        for (Order order : orders) {
            if (order.getNumber() == orderNumber) {
                orders.remove(order);
            }

            setOrderExist(false);
        }

    }

    public Order getOrder(int orderNumber) {
        for (Order order : orders) {
            if (orders.indexOf(order) == orderNumber) {
                return order;
            }

            setOrderExist(false);
        }
        return null;
    }

    public void sortOrders() {
        Collections.sort(orders, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                if (o1.getNumber() != o2.getNumber()) {
                    return o1.getNumber() - o2.getNumber();
                }

                return 0;
            }
        });
    }

    public void changeOrder(Order oldOrder, Order newOrder) {
        sortOrders();
        oldOrder = getOrder(orders.indexOf(oldOrder));
        if (newOrder != null) {
            orders.remove(oldOrder);
            orders.add(newOrder);
        }


    }
}
