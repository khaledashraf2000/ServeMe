package file.handling.users;

import logic.entities.users.Customer;
import logic.entities.users.employee.Cook;
import logic.entities.users.employee.Manager;
import logic.entities.users.employee.Waiter;

import java.util.HashMap;
import java.util.List;

public class UsersHash {

    static HashMap<String, User> usersHash = new HashMap<>();
    List<User> userData;
    Customer customer;

    public static HashMap<String, User> getUserData() {
        return usersHash;
    }

    public static HashMap<String, User> getUsersHash() {
        return usersHash;
    }

    public void addUser(User user) {

        usersHash.put(user.getUserName(), user);
    }

    public void loadData(Users users) {
        this.userData = users.getUsers();

        for (User user : this.userData) {
            if (user.getRole().equalsIgnoreCase("manager")) {
                Manager manager = new Manager(user.getUserName(), user.getSalary(), user.getRole(), user.getPassword(), user.getName());
                manager.setName(user.getName());
                addUser(manager);
            } else if (user.getRole().equalsIgnoreCase("cooker")) {
                Cook cook = new Cook(user.getUserName(), user.getRole(), user.getSalary(), user.getPassword(), user.getName());
                cook.setName(user.getName());
                addUser(cook);
            } else if (user.getRole().equalsIgnoreCase("client")) {
                Customer customer = new Customer(user.getUserName(), user.getPassword());
                customer.setName(user.getName());
                addUser(customer);
            } else if (user.getRole().equalsIgnoreCase("waiter")) {
                Waiter waiter = new Waiter(user.getUserName(), user.getSalary(), user.getRole(), user.getPassword(), user.getName());
                waiter.setName(user.getName());
                addUser(waiter);
            }
        }
    }

    public Boolean userExists(String userName) {
        return usersHash.containsKey(userName);
    }

    public Boolean isPasswordValid(String userName, String password) {
        return password.compareTo(usersHash.get(userName).getPassword()) == 0;
    }

    public User getUserByName(String name) {
        if (userExists(name) && isPasswordValid(name, usersHash.get(name).getPassword())) {
            return usersHash.get(name);
        }

        return null;
    }
}
