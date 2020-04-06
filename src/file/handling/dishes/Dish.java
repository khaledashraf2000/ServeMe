package file.handling.dishes;

import logic.TaxesComputation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "dish")
@XmlAccessorType(XmlAccessType.FIELD)
public class Dish implements TaxesComputation {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "price")
    private Double price;

    @XmlElement(name = "type")
    private String type;

    private int dishNumber;

    public Dish() {

    }

    public Dish(String name, Double price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public int getDishNumber() {
        return dishNumber;
    }

    public void setDishNumber(int dishNumber) {
        this.dishNumber = dishNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public double calculateTotalPrice() {
        return 10;
    }
}
