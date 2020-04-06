package file.handling;

import file.handling.dishes.Dish;
import file.handling.tables.Table;
import file.handling.tables.Tables;
import file.handling.tables.TablesList;
import file.handling.users.User;
import logic.entities.restaurant.Reservation;
import logic.entities.restaurant.Reservations;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class SaveData<T> {


    static Restaurant savedRestaurant;
    Reservations reservations;

    public SaveData() throws JAXBException {
        FileHandling fileHandling = new FileHandling();
        savedRestaurant = fileHandling.fetchData();
    }

    public static Restaurant getSavedRestaurant() {
        return savedRestaurant;
    }

    public void saveData(T data) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Restaurant.class);
        Marshaller marshaller = jaxbContext.createMarshaller();

        if (data instanceof Reservation) {

            Reservation reservation = (Reservation) data;
            reservations = new Reservations();
            Reservations.addReservation(reservation.getCustomerName(), reservation);
            Tables tables = new Tables();
            tables.setTables(TablesList.getTables());
            reservation.getTable().setReserved(true);
            savedRestaurant.setTables(tables);
            savedRestaurant.setReservations(reservations);
        } else if (data instanceof User) {
            User user = (User) data;
            savedRestaurant.getUsers().getUsers().add(user);
        } else if (data instanceof Table) {
            Tables tables = new Tables();
            tables.setTables(TablesList.getTables());
            savedRestaurant.setTables(tables);
        } else if (data instanceof Double) {
            savedRestaurant.calculateRevenue((Double) data);
        } else if(data instanceof Dish) {
            savedRestaurant.getDishes().getDishes().add((Dish) data);
        }

        marshaller.marshal(savedRestaurant, new File("data.xml"));


    }

    public void removeData(Reservation reservation) {
        reservations = new Reservations();
        Reservations.getReservations().remove(reservation.getCustomerName());
        Reservations.getReservations().get(reservation.getCustomerName());
        savedRestaurant.setReservations(reservations);
    }

    public void modifyRevenue(Double price) {
        savedRestaurant.setRevenue(savedRestaurant.getRevenue() - price);
    }

    public void calculateRevenue(Double price) {
        savedRestaurant.setRevenue(savedRestaurant.getRevenue() + price);
    }

    public void modifySalary(User user) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Restaurant.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        for (User temp : savedRestaurant.getUsers().getUsers()) {
            if (temp.getUserName().equals(user.getUserName())) {
                savedRestaurant.getUsers().getUsers().set(savedRestaurant.getUsers().getUsers().indexOf(temp), user);
            }
        }
        marshaller.marshal(savedRestaurant, new File("data.xml"));
    }
}
