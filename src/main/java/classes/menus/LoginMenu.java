package classes.menus;

import classes.enums.Role;
import classes.users.Admin;
import classes.users.Customer;

public class LoginMenu {

    InputTaker input = new InputTaker();
    Admin createdAdmin;
    Customer createdCustomer;

    public void displayLoginMenu() {
        boolean isRunning = true;
        System.out.println("Welcome to the Online Shop!");
        while(isRunning) {
            System.out.println("""
                    (1) Log in
                    (2) Create new user
                    (0) Exit""");
            int userInput = input.getIntInput();
            switch (userInput) {
                case 1 -> {
                    String loginInput = input.getStringInputWithMessage("Please enter your login: ");
                    String passwordInput = input.getStringInputWithMessage("Please enter your password: ");
                }
                case 2 -> {
                    String role = input.getStringInputWithMessage("Are you an admin or a customer?");
                    String login = input.getStringInputWithMessage("Enter login: ");
                    String password = input.getStringInputWithMessage("Enter password: ");
                    String email = input.getStringInputWithMessage("Enter email: ");
                    switch (role) {
                        case "a":
                            createdAdmin = new Admin(login, password, email, Role.ADMIN);
                        case "c":
                            createdCustomer = new Customer(login, password, email, Role.CUSTOMER);
                    }
                }
                case 3 -> isRunning = false;
            }
        }

    }
}
