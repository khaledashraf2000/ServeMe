package logic.entities;

public class TableData {

    private int tableNumber;

    private int numberOfSeats;

    private Boolean smoking;
    private Boolean reserved;

    public TableData(int chairNumber, Boolean smoking) {
        this.numberOfSeats = chairNumber;
        this.smoking = smoking;
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
