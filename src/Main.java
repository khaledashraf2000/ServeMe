import file.handling.FileHandling;
import file.handling.dishes.DishesHash;
import file.handling.dishes.ListOfDishes;
import file.handling.tables.TablesList;
import file.handling.users.UsersHash;
import gui.handles.Window;
import gui.windows.LoginScreen;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;

public class Main extends Application {
    static FileHandling fileHandling;


    public Main() throws JAXBException {
    }

    public static void main(String[] args) throws JAXBException {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        fileHandling = new FileHandling();

        UsersHash usersHash = new UsersHash();
        DishesHash dishesHash = new DishesHash();
        TablesList tablesList = new TablesList();
        ListOfDishes listOfDishes = new ListOfDishes();

        fileHandling.loadData(tablesList, usersHash, dishesHash, listOfDishes);

        LoginScreen login = new LoginScreen(UsersHash.getUserData(), primaryStage);
        login.prepareScene();

        primaryStage.setScene(login.getScene());
        primaryStage.setTitle("ServeMe");
        primaryStage.show();
        primaryStage.setResizable(false);
        Window.setOnCenter(primaryStage);
    }
}
