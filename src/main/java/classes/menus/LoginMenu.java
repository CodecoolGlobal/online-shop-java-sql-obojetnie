package classes.menus;

import classes.enums.Option;
import classes.enums.Role;
import classes.users.Admin;
import classes.users.Customer;

public class LoginMenu {

    InputTaker input = new InputTaker();
    Admin createdAdmin;
    Customer createdCustomer;

    public void displayLoginMenu() throws Exception {
        boolean isRunning = true;
        System.out.println("Welcome to the Online Shop!");
        while(isRunning) {
            System.out.println("""
                    (1) Log in
                    (2) Create new user
                    (0) Exit""");
            Option option = input.getOptionInt();
            switch (option) {
                case ONE -> {
                    String loginInput = input.getStringInputWithMessage("Please enter your login: ");
                    String passwordInput = input.getStringInputWithMessage("Please enter your password: ");
                }
                case TWO -> {
                    String role = input.getStringInputWithMessage("Are you an admin or a customer?");
                    String login = input.getStringInputWithMessage("Enter login: ");
                    String password = input.getStringInputWithMessage("Enter password: ");
                    String email = input.getStringInputWithMessage("Enter email: ");
                    switch (role) {
                        case "a":
                            createdAdmin = new Admin(login, password, email, Role.ADMIN);
                            break;
                        case "c":
                            createdCustomer = new Customer(login, password, email, Role.CUSTOMER);
                            break;
                    }
                }
                case ZERO -> isRunning = false;
            }
        }

    }
}
