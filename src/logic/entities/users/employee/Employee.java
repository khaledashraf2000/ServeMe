package logic.entities.users.employee;

import file.handling.users.User;

public class Employee extends User {

    public Employee(String name, Double salary, String role) {
        this.name = name;
        this.role = role;
    }

    public Employee(String userName, String role, Double salary, String password, String name) {
        this.userName = userName;
        this.role = role;
        this.salary = salary;
        this.password = password;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
