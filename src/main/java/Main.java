import classes.menus.LoginMenu;
import classes.menus.exceptions.AvailabilityException;
import classes.menus.exceptions.OptionEnumException;

public class Main {

    public static void main(String[] args) throws Exception, AvailabilityException, OptionEnumException {

        LoginMenu loginMenu = new LoginMenu();
        loginMenu.displayLoginMenu();

    }
}
