import classes.controllers.SqlController;
import classes.enums.Role;
import classes.readers.SqlConnector;
import classes.users.Customer;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello!");

        SqlConnector sqlConnector = new SqlConnector();
        sqlConnector.connectToDatabase();

        SqlController sqlController = new SqlController(sqlConnector);

        sqlController.viewUsersTable();

        Customer customer = new Customer("andrzej", "chrzan", "andrewatgmail.com", Role.CUSTOMER);

        sqlController.addUser(customer);

        sqlController.viewUsersTable();

        sqlConnector.disconnectFromDatabase();


    }
}
