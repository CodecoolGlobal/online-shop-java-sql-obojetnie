import classes.menus.LoginMenu;
<<<<<<< Updated upstream
import classes.models.Product;
import classes.connectors.SqlConnector;
import classes.users.Customer;
=======
import classes.menus.exceptions.AvailabilityException;
import classes.menus.exceptions.OptionEnumException;
>>>>>>> Stashed changes


public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello!");

        LoginMenu loginMenu = new LoginMenu();
        loginMenu.displayLoginMenu();
    }
}
