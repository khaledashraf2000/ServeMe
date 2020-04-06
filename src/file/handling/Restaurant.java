package file.handling;

import file.handling.dishes.Dishes;
import file.handling.tables.Tables;
import file.handling.users.Users;
import logic.entities.restaurant.Reservations;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "restaurant")
@XmlAccessorType(XmlAccessType.FIELD)
public class Restaurant {

    @XmlElement(name = "reservations")
    Reservations reservations;
    @XmlElement(name = "users")
    private Users users;
    @XmlElement(name = "tables")
    private Tables tables;
    @XmlElement(name = "dishes")
    private Dishes dishes;
    @XmlElement(name = "revenue")
    private Double revenue = 0.0;

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    public Reservations getReservations() {
        return reservations;
    }

    public void setReservations(Reservations reservations) {
        this.reservations = reservations;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Tables getTables() {
        return tables;
    }

    public void setTables(Tables tables1) {
        tables = tables1;
    }

    public Dishes getDishes() {
        return dishes;
    }

    public void setDishes(Dishes dishes) {
        this.dishes = dishes;
    }

    public void calculateRevenue(Double price) {
        this.revenue += price;
    }

    public void modifyRevenue(Double price) {
        this.revenue -= price;
    }
}

