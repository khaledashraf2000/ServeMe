package gui.handles;

import file.handling.SaveData;
import file.handling.users.User;
import file.handling.users.UsersHash;
import gui.windows.Alert;
import gui.windows.LoginScreen;
import gui.windows.dashboards.ManagerDashboard;
import javafx.stage.Stage;
import logic.entities.users.Customer;
import logic.entities.users.employee.Cook;
import logic.entities.users.employee.Manager;
import logic.entities.users.employee.Waiter;

import javax.xml.bind.JAXBException;
import java.util.HashMap;

public class SignUpHandle {

    HashMap<String, User> users = UsersHash.getUserData();
    Stage primaryStage;
    LoginScreen loginScreen;
    ManagerDashboard managerDashboard;

    public SignUpHandle(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void signUpButtonHandle(String name, String role, String username, String password, String confirmPassword) throws JAXBException {

        if(name.isEmpty() || role == null || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Alert.display("Please fill all fields");
            return;
        }

        SaveData<User> saveData = new SaveData();

        if (users.containsKey(username)) {
            Alert.display("Username already taken");
            return;
        }
        if (!password.equals(confirmPassword)) {
            Alert.display("Passwords don't match");
            return;
        }
        User user = new User();
        user.setName(name);
        user.setRole(role);
        user.setPassword(password);
        user.setUserName(username);
        HashMap<String, User> usersHash = UsersHash.getUsersHash();

        if (user.getRole().equalsIgnoreCase("manager")) {
            Manager manager = new Manager(user.getUserName(), 0.0, user.getRole(), user.getPassword(), user.getName());
            manager.setName(user.getName());
            usersHash.put(user.getUserName(), manager);
        } else if (user.getRole().equalsIgnoreCase("cooker")) {
            Cook cook = new Cook(user.getUserName(), user.getRole(), 0.0, user.getPassword(), user.getName());
            cook.setName(user.getName());
            usersHash.put(user.getUserName(), cook);
        } else if (user.getRole().equalsIgnoreCase("client")) {
            Customer customer = new Customer(user.getUserName(), user.getPassword());
            customer.setName(user.getName());
            usersHash.put(user.getUserName(), customer);
        } else if (user.getRole().equalsIgnoreCase("waiter")) {
            Waiter waiter = new Waiter(user.getUserName(), 0.0, user.getRole(), user.getPassword(), user.getName());
            waiter.setName(user.getName());
            usersHash.put(user.getUserName(), waiter);
        }

        managerDashboard = new ManagerDashboard(primaryStage, users.get("mazen"));
        managerDashboard.prepareScene();
        primaryStage.setScene(managerDashboard.getScene());
        Window.setOnCenter(primaryStage);
        saveData.saveData(user);
    }

    public void backButtonHandle() throws JAXBException {
        managerDashboard = new ManagerDashboard(primaryStage, users.get("mazen"));
        managerDashboard.prepareScene();
        primaryStage.setScene(managerDashboard.getScene());
        Window.setOnCenter(primaryStage);
    }
}
