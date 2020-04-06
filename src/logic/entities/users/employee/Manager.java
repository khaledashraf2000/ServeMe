package logic.entities.users.employee;

import file.handling.Restaurant;
import file.handling.SaveData;
import file.handling.tables.Table;
import file.handling.tables.TablesList;
import file.handling.users.User;
import file.handling.users.UsersHash;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class Manager extends Employee {

    public Manager(String userName, Double salary, String role, String password, String name) {
        super(userName, role, salary, password, name);
    }


    public void addTable(int numberOfSeats, Boolean smoking) throws JAXBException {

        SaveData<Table> saveData = new SaveData<>();

        int tableNumber = TablesList.getTables().size() + 1;

        for (int i = 0; i < TablesList.getTables().size(); i++) {
            Boolean flag = false;
            for (Table table : TablesList.getTables()) {
                if (table.getTableNumber() == i + 1) {
                    flag = true;
                    break;
                }
            }
            if (!flag) tableNumber = i + 1;
        }


        Table table = new Table(numberOfSeats, smoking);
        table.setReserved(false);
        table.setTableNumber(tableNumber);

        if (table.getNumberOfSeats() == 2) table.setType("Couple");
        else if (table.getNumberOfSeats() > 2) table.setType("Family");
        else table.setType("Single");

        TablesList.getTables().add(table);

        saveData.saveData(table);
    }

    public void removeTable(Table table) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Restaurant.class);

        Marshaller marshaller = jaxbContext.createMarshaller();

        Restaurant restaurant;

        for (Table singleTable : TablesList.getTables()) {
            if (table.getTableNumber() == singleTable.getTableNumber()) {
                TablesList.getTables().remove(table);
                break;
            }
        }

        SaveData saveData = new SaveData();

        restaurant = SaveData.getSavedRestaurant();


        restaurant.getTables().setTables(TablesList.getTables());

        marshaller.marshal(restaurant, new File("data.xml"));


    }

    public void addUser(User user) {
        UsersHash.getUserData().put(user.getUserName(), user);
    }

    public void modifySalary(User user, Double salary) {
        user.setSalary(salary);
    }
}
