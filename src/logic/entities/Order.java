package logic.entities;


import file.handling.dishes.Dish;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {

    @XmlElement(name = "orderNumber")
    private int orderNumber;

    @XmlElement(name = "dish")
    private List<Dish> dishes;

    private String order;

    private Dish dish;

    @XmlElement(name = "price")
    private double prices;
    private Double totalPrice;

    public Order() {

    }

    public Order(List<Dish> dishes) {
        this.dishes = dishes;
        Double totalPrice = new Double(0);
        for (Dish temp : dishes) {
            totalPrice += temp.getPrice();
        }
        this.totalPrice = totalPrice;
    }

    public Order(Dish dish) {
        this.dish = dish;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public double getPrices() {
        return prices;
    }

    public void setPrices(double prices) {
        this.prices = prices;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void orderToString(Order order) {
        String string = "";
        for (Dish dish : order.getDishes()) {
            string += dish.getDishNumber() + " * "
                    + dish.getName() + " , "
                    + dish.getType()
                    + "\n";
        }
        this.order = string;
    }

    public int getNumber() {
        return orderNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    //
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;

    }
}
