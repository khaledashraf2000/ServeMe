package file.handling.tables;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "table")
@XmlAccessorType(XmlAccessType.FIELD)
public class Table {

    @XmlElement(name = "number")
    private int tableNumber;

    @XmlElement(name = "number_of_seats")
    private int numberOfSeats;

    @XmlElement(name = "smoking")
    private Boolean smoking;

    @XmlElement(name = "reserved")
    private Boolean reserved;

    private String type;

    public Table() {

    }

    public Table(int numberOfSeats, Boolean smoking) {
        this.numberOfSeats = numberOfSeats;
        this.smoking = smoking;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getSmoking() {
        return smoking;
    }

    public void setSmoking(Boolean smoking) {
        this.smoking = smoking;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Boolean getReserved() {
        return reserved;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }

    public Boolean isReserved() {
        return reserved;
    }

    public Boolean isSmoking() {
        return smoking;
    }
}
