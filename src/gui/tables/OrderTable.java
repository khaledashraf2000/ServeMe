package gui.tables;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.entities.Order;
import logic.entities.restaurant.Reservation;
import logic.entities.restaurant.Reservations;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OrderTable {

    private static ObservableList<Reservation> reservation = FXCollections.observableArrayList();
    HashMap<String, Reservation> reservations = Reservations.getReservations();
    private TableView<Reservation> orderItems = new TableView<>();
    private TableColumn<Reservation, Integer> quantityColumn = new TableColumn<Reservation, Integer>("Table Number");
    private TableColumn<Reservation, String> orderColumn = new TableColumn("Order");

    public void prepareTable() {

        Reservation reservation;
        Iterator reservationHash = reservations.entrySet().iterator();
        OrderTable.reservation.clear();

        while (reservationHash.hasNext()) {

            Map.Entry mapElement = (Map.Entry) reservationHash.next();
            reservation = (Reservation) mapElement.getValue();
            OrderTable.reservation.add(reservation);
        }

        quantityColumn.impl_setWidth(200);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
        quantityColumn.setSortType(TableColumn.SortType.ASCENDING);
        orderColumn.setPrefWidth(605);
        orderColumn.setCellValueFactory(new PropertyValueFactory<>("orderString"));

        orderColumn.setStyle("-fx-alignment: CENTER;");

        orderItems.setItems(OrderTable.reservation);
        orderItems.getColumns().addAll(quantityColumn, orderColumn);
        orderItems.getSortOrder().add(quantityColumn);

    }


    public TableView<Reservation> getTable() {
        return orderItems;
    }
}
