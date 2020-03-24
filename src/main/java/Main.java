import classes.categories.Category;
import classes.controllers.CategoryController;
import classes.controllers.ProductController;
import classes.controllers.SqlController;
import classes.controllers.UserController;
import classes.enums.Role;
import classes.menus.LoginMenu;
import classes.menus.exceptions.AvailabilityException;
import classes.menus.exceptions.OptionEnumException;
import classes.models.Product;
import classes.connectors.SqlConnector;
import classes.users.Customer;


public class Main {

    public static void main(String[] args) throws Exception, AvailabilityException, OptionEnumException {
        System.out.println("Hello!");

        LoginMenu loginMenu = new LoginMenu();
        loginMenu.displayLoginMenu();

    }
}
