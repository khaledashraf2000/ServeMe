package gui.windows.dashboards;

import file.handling.Restaurant;
import file.handling.SaveData;
import file.handling.tables.Table;
import file.handling.users.User;
import file.handling.users.UsersHash;
import gui.handles.TableHandle;
import gui.handles.Window;
import gui.windows.LoginScreen;
import gui.windows.SignUpScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import logic.entities.Order;
import logic.entities.restaurant.Reservation;
import logic.entities.users.Customer;
import logic.entities.users.employee.Manager;

import javax.xml.bind.JAXBException;

public class ManagerDashboard {

    Scene scene;
    Stage primaryStage;
    Scene loginScreen;
    TableHandle tableHandle;
    Manager manager;
    TableView<Reservation> reservationTableView;
    TableView<Table> tableTableView;
    TableView<Customer> customerTableView;
    TableView<User> employeeTableView;
    Font font;
    Restaurant restaurant;
    SaveData<Object> saveData = new SaveData<>();

    public ManagerDashboard(Stage primaryStage, User user) throws JAXBException {
        this.primaryStage = primaryStage;
        font = Font.loadFont(ManagerDashboard.class.getResourceAsStream("/resources/fonts/greeting-font.TTF"), 80);
        manager = (Manager) user;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Scene getLoginScreen() {
        return loginScreen;
    }

    public void setLoginScreen(Scene loginScreen) {
        this.loginScreen = loginScreen;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void prepareScene() {

        //Buttons
        Button reservationsButton = new Button("  Reservations", new ImageView("resources/reservations-icon.png"));
        Button tablesButton = new Button("  Tables", new ImageView("resources/tables-icon.png"));
        Button customersButton = new Button("  Customers", new ImageView("resources/customers-icon.png"));
        Button employeesButton = new Button("  Employees", new ImageView("resources/employees-icon.png"));
        Button revenueButton = new Button("  Revenue", new ImageView("resources/revenue-icon.png"));
        Button logoutButton = new Button("Logout");
        Button addTable = new Button("Add table", new ImageView("resources/white-add-icon.png"));
        Button removeTable = new Button("Remove Table", new ImageView("resources/white-delete-icon.png"));
        Button changeSalaryButton = new Button("Change Salary", new ImageView("resources/salary-icon.png"));
        Button addUser = new Button("Add User", new ImageView("resources/add-user-icon.png"));

        removeTable.prefHeightProperty().bind(addTable.heightProperty());

        Label numberOfSeats = new Label("Choose number of Seats");
        numberOfSeats.setPadding(new Insets(0, 10, 0, 0));
        Spinner<Integer> seatsNumberSpinner = new Spinner<>();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50, 1);
        seatsNumberSpinner.setValueFactory(valueFactory);
        CheckBox smoking = new CheckBox("Smoking");

        HBox settingsBox = new HBox();
        HBox blank = new HBox();
        blank.setPrefWidth(150);
        HBox title = new HBox();
        HBox controlTableBox = new HBox();
        HBox buttonsBox = new HBox(addTable, removeTable);
        buttonsBox.setSpacing(10);
        removeTable.setPadding(new Insets(0, 10, 0, 0));
        VBox right1 = new VBox();

        controlTableBox.getChildren().addAll(numberOfSeats, seatsNumberSpinner, smoking);
        controlTableBox.setSpacing(10);
        settingsBox.getChildren().addAll(controlTableBox, blank, title, buttonsBox);
        settingsBox.setAlignment(Pos.TOP_RIGHT);

        TableColumn<Table, Integer> tableNumber = new TableColumn<>("Table Number");
        tableNumber.setMinWidth(202);
        tableNumber.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
        tableNumber.setSortType(TableColumn.SortType.ASCENDING);

        reservationsButton.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.20));
        tablesButton.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.20));
        customersButton.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.20));
        employeesButton.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.20));
        revenueButton.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.20));
        logoutButton.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.20));
        tablesButton.prefHeightProperty().bind(primaryStage.heightProperty());
        customersButton.prefHeightProperty().bind(primaryStage.heightProperty());
        employeesButton.prefHeightProperty().bind(primaryStage.heightProperty());
        revenueButton.prefHeightProperty().bind(primaryStage.heightProperty());
        logoutButton.prefHeightProperty().bind(primaryStage.heightProperty());
        reservationsButton.prefHeightProperty().bind(primaryStage.heightProperty());
        addUser.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.20));
        addUser.prefHeightProperty().bind(primaryStage.heightProperty());

        //Layouts
        VBox left = new VBox(0);
        left.setPrefWidth(200);
        left.getChildren().addAll(reservationsButton, customersButton, employeesButton, tablesButton, revenueButton, addUser, logoutButton);
        left.setAlignment(Pos.TOP_LEFT);
        Text text = new Text("Manager Dashboard");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(font);
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        text.setEffect(ds);
        text.setId("manager");
        HBox textBox = new HBox(text);
        textBox.setPadding(new Insets(0, 0, 0, 120));
        VBox right = new VBox();
        HBox root = new HBox();
        right.getChildren().add(textBox);
        right.setAlignment(Pos.CENTER);
        root.getChildren().add(left);
        root.getChildren().add(right);

        tableHandle = new TableHandle();

        //Button Handling
        {
            reservationsButton.setOnAction(e -> {
                TableColumn<Reservation, Integer> idColumn = new TableColumn<>("Reservation Number");
                idColumn.setMinWidth(202.5);
                idColumn.setSortType(TableColumn.SortType.ASCENDING);
                idColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
                TableColumn<Reservation, String> customerIDColumn = new TableColumn<>("Customer Name");
                customerIDColumn.setMinWidth(202.5);
                customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
                TableColumn<Reservation, Integer> tableIDColumn = new TableColumn<>("Table");
                tableIDColumn.setMinWidth(202.5);
                tableIDColumn.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
                TableColumn<Reservation, String> timeColumn = new TableColumn<>("Time of reservation");
                timeColumn.setMinWidth(202.5);
                timeColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
                TableColumn<Reservation, String> orderColumn = new TableColumn<>("Order");
                orderColumn.setMinWidth(202.5);
                orderColumn.setCellValueFactory(new PropertyValueFactory<>("orderToString"));
                reservationTableView = new TableView<>();
                reservationTableView.getColumns().addAll(idColumn, customerIDColumn, tableIDColumn, timeColumn);
                tableHandle.feedReservationsTable(reservationTableView);
                reservationTableView.getSortOrder().add(idColumn);
                reservationTableView.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.80));
                root.getChildren().setAll(left, reservationTableView);
            });

            tablesButton.setOnAction(e -> {
                TableColumn<Table, Integer> seatsColumn = new TableColumn<>("Number of Seats");
                seatsColumn.setMinWidth(202);
                seatsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));
                TableColumn<Table, Boolean> smokingColumn = new TableColumn<>("Smoking");
                smokingColumn.setMinWidth(202);
                smokingColumn.setCellValueFactory(new PropertyValueFactory<>("smoking"));
                TableColumn<Table, String> typeColumn = new TableColumn<>("Type");
                typeColumn.setMinWidth(202);
                typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
                tableTableView = new TableView<>();
                tableTableView.getColumns().addAll(tableNumber, seatsColumn, typeColumn, smokingColumn);
                tableHandle.feedTablesTable(tableTableView);
                settingsBox.setPadding(new Insets(20));
                right1.getChildren().clear();
                right1.getChildren().addAll(settingsBox, tableTableView);
                right1.setSpacing(15);
                tableTableView.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.80));
                tableTableView.getSortOrder().add(tableNumber);
                root.getChildren().setAll(left, right1);
            });

            addTable.setOnAction(e -> {
                int tableNumber1 = seatsNumberSpinner.getValue();
                Boolean smoking1 = smoking.isSelected();
                try {
                    manager.addTable(tableNumber1, smoking1);
                } catch (JAXBException ex) {
                    ex.printStackTrace();
                }
                tableTableView.getItems().clear();
                tableHandle.feedTablesTable(tableTableView);
                tableTableView.getSortOrder().add(tableNumber);
                root.getChildren().setAll(left, right1);
            });

            //add user buttn:

            addUser.setOnAction(e -> {
                SignUpScene signUpScene = new SignUpScene(primaryStage);
                signUpScene.prepareScene();
                primaryStage.setScene(signUpScene.getScene());
                Window.setOnCenter(primaryStage);
            });

            customersButton.setOnAction(e -> {
                TableColumn<Customer, String> nameColumn = new TableColumn<>("Customer Name");
                nameColumn.setMinWidth(810);
                nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                customerTableView = new TableView<>();
                customerTableView.getColumns().add(nameColumn);
                tableHandle.feedCustomerTable(customerTableView);
                customerTableView.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.80));
                root.getChildren().setAll(left, customerTableView);
            });

            employeesButton.setOnAction(e -> {
                TableColumn<User, String> nameColumn = new TableColumn<>("Name");
                nameColumn.setMinWidth(270);
                nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                TableColumn<User, String> roleColumn = new TableColumn<>("Role");
                roleColumn.setMinWidth(270);
                roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
                TableColumn<User, Double> salaryColumn = new TableColumn<>("Salary");
                salaryColumn.setMinWidth(270);
                salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
                employeeTableView = new TableView<>();
                employeeTableView.getColumns().addAll(nameColumn, roleColumn, salaryColumn);
                tableHandle.feedEmployeeTable(employeeTableView);
                employeeTableView.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.80));
                TextField salaryInput = new TextField();
                salaryInput.setPromptText("salary");
                changeSalaryButton.setOnAction(a -> {
                    User user = employeeTableView.getSelectionModel().getSelectedItem();
                    Double salary = Double.parseDouble(salaryInput.getText());
                    manager.modifySalary(user, salary);
                    employeeTableView.getItems().clear();
                    tableHandle.feedEmployeeTable(employeeTableView);
                    try {
                        saveData.modifySalary(user);
                    } catch (JAXBException ex) {
                        ex.printStackTrace();
                    }
                });
                HBox hBox = new HBox(10);
                Label label = new Label("Modify selected employee salary: ");
                label.setStyle("-fx-font-size: 12");
                hBox.getChildren().addAll(label, salaryInput, changeSalaryButton);
                hBox.setPadding(new Insets(15));
                hBox.setAlignment(Pos.BOTTOM_RIGHT);
                VBox vBox = new VBox();
                vBox.setSpacing(20);
                vBox.getChildren().addAll(hBox, employeeTableView);
                StackPane stackPane = new StackPane();
                stackPane.getChildren().addAll(vBox);
                root.getChildren().setAll(left, stackPane);
            });

            revenueButton.setOnAction(e -> {
                restaurant = SaveData.getSavedRestaurant();
                Text totalText = new Text("Total Revenue" + " : " + restaurant.getRevenue());
                totalText.setTextAlignment(TextAlignment.CENTER);
                totalText.setId("revenue");
                totalText.setFont(font);
                totalText.setTextAlignment(TextAlignment.CENTER);
                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(totalText);
                stackPane.setAlignment(Pos.CENTER);
                stackPane.setPrefWidth(810);
                root.getChildren().setAll(left, stackPane);
            });

            logoutButton.setOnAction(e -> {
                LoginScreen loginScreen1 = new LoginScreen(UsersHash.getUserData(), primaryStage);
                loginScreen1.prepareScene();
                primaryStage.setScene(loginScreen1.getScene());
                Window.setOnCenter(primaryStage);
            });
        }

        removeTable.setOnAction(e -> {
            Table table = tableTableView.getSelectionModel().getSelectedItem();
            try {
                manager.removeTable(table);
            } catch (JAXBException ex) {
                ex.printStackTrace();
            }

            tableTableView.getItems().clear();
            tableHandle.feedTablesTable(tableTableView);
            tableTableView.getSortOrder().add(tableNumber);
            root.getChildren().setAll(left, right1);
        });

        //Styling
        root.getStylesheets().add("dashboards-styling.css");

        //Final
        this.scene = new Scene(root, 1115, 674);
        scene.getStylesheets().add("dashboards-styling.css");
        Window.setOnCenter(primaryStage);
    }
}


