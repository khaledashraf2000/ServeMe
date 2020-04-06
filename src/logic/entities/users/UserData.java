package logic.entities.users;

public class UserData {

    protected String name;
    protected String userName;
    protected String role;
    protected String password;
    protected double salary;

    public UserData(String userName, String role, double salary, String password) {
        this.userName = userName;
        this.salary = salary;
        this.role = role;
        this.password = password;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
