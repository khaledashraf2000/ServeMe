package logic;

import file.handling.users.User;
import file.handling.users.UsersHash;
import gui.handles.Window;
import gui.windows.Alert;
import gui.windows.dashboards.CookDashboard;
import gui.windows.dashboards.CustomerDashboard;
import gui.windows.dashboards.ManagerDashboard;
import gui.windows.dashboards.WaiterDashboard;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.util.HashMap;

public class DetermineDashboard {

    String userName;
    String password;
    HashMap<String, User> users = UsersHash.getUserData();
    CookDashboard cookDashboard;
    CustomerDashboard customerDashboard = new CustomerDashboard();
    WaiterDashboard waiterDashboard;
    ManagerDashboard managerDashboard;
    Alert alert;
    Stage primaryStage;
    Scene loginScene;

    public DetermineDashboard(Stage primaryStage, String userName, String password, Scene loginScene) {
        this.userName = userName;
        this.password = password;
        this.primaryStage = primaryStage;
        this.loginScene = loginScene;
    }

    public void displayUsersDashboard() throws JAXBException {


        if (users.containsKey(userName)) {
            String role = users.get(userName).getRole();
            if (password.compareTo(users.get(userName).getPassword()) == 0) {
                if (role.equalsIgnoreCase("cooker")) {
                    cookDashboard = new CookDashboard(primaryStage, loginScene);
                    cookDashboard.displayCookDashboard();
                } else if (role.equalsIgnoreCase("client")) {
                    customerDashboard.prepareScene(primaryStage, loginScene, users.get(userName));
                    customerDashboard.customerSceneDisplay();
                } else if (role.equalsIgnoreCase("manager")) {
                    managerDashboard = new ManagerDashboard(primaryStage, users.get(userName));
                    managerDashboard.prepareScene();
                    primaryStage.setScene(managerDashboard.getScene());
                    Window.setOnCenter(primaryStage);
                } else if (role.equalsIgnoreCase("waiter")) {
                    waiterDashboard = new WaiterDashboard(primaryStage);
                    waiterDashboard.prepareScene();
                    primaryStage.setScene(waiterDashboard.getScene());
                    Window.setOnCenter(primaryStage);
                }
            } else {
                Alert.display("Please enter correct credentials");
            }
        } else {
            alert = new Alert();
            Alert.display("User doesn't exist");
        }
    }
}
