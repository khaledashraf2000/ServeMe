package logic.entities.restaurant;

import file.handling.tables.Table;
import logic.entities.Order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "reservation")
@XmlAccessorType(XmlAccessType.FIELD)
public class Reservation {

    private int reservationNumber = 0;

    @XmlElement(name = "customerName")
    private String customerName;

    @XmlElement(name = "orderString")
    private String orderString;

    @XmlElement(name = "order")
    private Order order;

    @XmlElement(name = "table")
    private Table table;

    private Date date;

    private double prices;

    public int getNumber() {
        return reservationNumber;
    }

    public double getPrices() {
        return prices;
    }

    public void setPrices(double prices) {
        this.prices = prices;
    }

    public Integer getTableNumber() {
        return table.getTableNumber();
    }

    public int getOrderNumber() {
        return order.getNumber();
    }

    public String getOrderString() {
        return orderString;
    }

    public void setOrderString(String orderString) {
        this.orderString = orderString;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int orderNumber1) {
        reservationNumber = orderNumber1;
    }

    public String getCustomerName() {
        return customerName;
    } //return username

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
