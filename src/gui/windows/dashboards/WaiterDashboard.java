package gui.windows.dashboards;

import file.handling.SaveData;
import file.handling.tables.Table;
import file.handling.tables.TablesList;
import file.handling.users.UsersHash;
import gui.handles.TableHandle;
import gui.handles.Window;
import gui.windows.LoginScreen;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import logic.entities.restaurant.Reservation;
import logic.entities.restaurant.Reservations;

import javax.xml.bind.JAXBException;
import java.util.Iterator;
import java.util.Map;

public class WaiterDashboard {

    TableView<Reservation> reservationTableView;
    private Scene scene;
    private Stage primaryStage;
    private TableHandle tableHandle;

    public WaiterDashboard(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void prepareScene() throws JAXBException {

        Text text = new Text("Waiter Dashboard");
        text.setTextAlignment(TextAlignment.CENTER);
        Font font = Font.loadFont(ManagerDashboard.class.getResourceAsStream("/resources/fonts/greeting-font.TTF"), 80);
        text.setFont(font);
        DropShadow ds = new DropShadow();
        HBox greetingBox = new HBox(text);
        greetingBox.setAlignment(Pos.CENTER);
        greetingBox.setPadding(new Insets(0, 0, 0, 200));
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        text.setEffect(ds);
        text.setId("manager");

        //saving data
        SaveData<Reservation> removeReservation = new SaveData<>();
        SaveData<Table> tableSaveData = new SaveData<>();

        //Buttons
        Button reservationsButton = new Button("  Reservations", new ImageView("resources/reservations-icon.png"));
        Button setReservationsButton = new Button("Set Reservations");
        reservationsButton.setAlignment(Pos.CENTER);
        Button logoutButton = new Button("Logout");

        //Buttons Styling
        reservationsButton.setMinSize(200, 224);
        setReservationsButton.setMinSize(200, 224);
        logoutButton.setMinSize(200, 224);
        setReservationsButton.setAlignment(Pos.CENTER);
        reservationsButton.setAlignment(Pos.CENTER);
        logoutButton.setAlignment(Pos.CENTER);

        //Layouts
        VBox left = new VBox(0);
        left.setAlignment(Pos.TOP_CENTER);
        left.setPrefWidth(200);
        left.setMinHeight(674);
        left.getChildren().addAll(reservationsButton, setReservationsButton, logoutButton);
        left.setStyle("-fx-background-color: #0F4C81");
        HBox root = new HBox();
        root.setMinHeight(674);
        root.getChildren().add(left);
        root.getChildren().add(greetingBox);

        left.prefWidthProperty().bind(root.widthProperty().multiply(0.2));
        reservationsButton.prefWidthProperty().bind(left.widthProperty());
        reservationsButton.prefHeightProperty().bind(left.heightProperty());
        logoutButton.prefHeightProperty().bind(left.heightProperty());
        logoutButton.prefWidthProperty().bind(left.widthProperty());

        tableHandle = new TableHandle();

        //Button Handling
        {
            reservationsButton.setOnAction(e -> {
                TableColumn<Reservation, Integer> reservationNumberColumn = new TableColumn<>("Reservation Number");
                reservationNumberColumn.setMinWidth(202);
                reservationNumberColumn.setSortType(TableColumn.SortType.ASCENDING);
                reservationNumberColumn.setCellValueFactory(new PropertyValueFactory<>("reservationNumber"));
                TableColumn<Reservation, String> customerNameColumn = new TableColumn<>("Customer Name");
                customerNameColumn.setMinWidth(202);
                customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
                TableColumn<Reservation, Integer> tableNumberColumn = new TableColumn<>("Table Number");
                tableNumberColumn.setMinWidth(202);
                tableNumberColumn.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
                TableColumn<Reservation, String> timeColumn = new TableColumn<>("Time of reservation");
                timeColumn.setMinWidth(202);
                timeColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
                reservationTableView = new TableView<>();
                reservationTableView.getColumns().addAll(reservationNumberColumn, customerNameColumn, tableNumberColumn, timeColumn);
                tableHandle.feedReservationsTable(reservationTableView);
                reservationTableView.getSortOrder().add(reservationNumberColumn);
                root.getChildren().setAll(left, reservationTableView);
            });

            setReservationsButton.setOnAction(e -> {
                ComboBox<Integer> tableNumberComboBox = new ComboBox<>();
                for (Table table : TablesList.getTables()) {
                    if (table.isReserved())
                        tableNumberComboBox.getItems().add(table.getTableNumber());
                }

                Button clearReservationButton = new Button("Clear Reservation");
                clearReservationButton.setOnAction(a -> {
                    Reservation reservation;
                    for (Table table : TablesList.getTables()) {
                        if (table.getTableNumber() == tableNumberComboBox.getValue()) {
                            Iterator hmIterator = Reservations.getReservations().entrySet().iterator();
                            while (hmIterator.hasNext()) {
                                Map.Entry mapElement = (Map.Entry) hmIterator.next();
                                reservation = (Reservation) mapElement.getValue();
                                if (reservation.getTable().getTableNumber() == table.getTableNumber()) {
                                    removeReservation.removeData(reservation);
                                    reservation.getTable().setReserved(false);
                                    table.setReserved(false);
                                    try {
                                        tableSaveData.saveData(table);
                                    } catch (JAXBException ex) {
                                        ex.printStackTrace();
                                    }
                                    break;
                                }
                            }
                        }
                    }
                });
                HBox hBox = new HBox(10);
                hBox.getChildren().addAll(tableNumberComboBox, clearReservationButton);
                StackPane right = new StackPane();
                right.getChildren().add(hBox);
                hBox.setAlignment(Pos.CENTER);
                right.setAlignment(Pos.CENTER);
                right.setPrefWidth(810);
                root.getChildren().setAll(left, right);
            });

            logoutButton.setOnAction(e -> {
                LoginScreen loginScreen1 = new LoginScreen(UsersHash.getUserData(), primaryStage);
                loginScreen1.prepareScene();
                primaryStage.setScene(loginScreen1.getScene());
                Window.setOnCenter(primaryStage);
            });
        }

        root.getStylesheets().add("dashboards-styling.css");

        //Final
        this.scene = new Scene(root, 1010, 674);
        scene.getStylesheets().add("dashboards-styling.css");
        Window.setOnCenter(primaryStage);
    }

    public Scene getScene() {
        return scene;
    }
}
