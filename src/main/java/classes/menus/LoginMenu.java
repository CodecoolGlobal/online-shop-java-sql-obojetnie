package classes.menus;

import classes.SqlConnector;
import classes.controllers.SqlController;
import classes.controllers.UserController;
import classes.enums.Option;
import classes.enums.Role;
import classes.users.Customer;

import java.util.regex.Pattern;

public class LoginMenu {

    SqlConnector sqlConnector;
    SqlController sqlController;
    InputTaker input;

    public LoginMenu() {
        sqlConnector = new SqlConnector();
        sqlConnector.connectToDatabase();
        sqlController = new SqlController(sqlConnector);
        input = new InputTaker();
    }

    public void displayLoginMenu() throws Exception {
        boolean isRunning = true;
        System.out.println("Welcome to the Online Shop!");
        while (isRunning) {
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
                    UserController userController = sqlController.getUserController();
                    String login = "";
                    String password = "";
                    String email = "";

                    login = getCustomerLogin(userController, login);
                    password = getCustomerPassword(password);
                    email = getCustomerEmail(userController, email);

                    try {
                        userController.addUser(new Customer(login, password, email, Role.CUSTOMER));
                    } catch (Exception e) {
                        throw new Exception("Something went wrong");
                    }
                    userController.viewUsersTable();

                }
                case ZERO -> {
                    sqlController.disconnectController();
                    isRunning = false;
                }
            }
        }

    }

    private String getCustomerEmail(UserController userController, String email) {
        boolean flag;

        flag = true;
        while (flag) {
            email = input.getStringInputWithMessage("Enter email: ");
            if (userController.getIsEmailTaken(email)) {
                System.out.println("Email is already taken");
            } else {
                flag = false;
            }
        }
        return email;
    }

    private String getCustomerPassword(String password) {
        String regex = "^[a-zA-Z0-9]{8,16}";
        Pattern pattern = Pattern.compile(regex);

        boolean isPasswordChoosen = false;
        while (!isPasswordChoosen) {
            System.out.println("Password must contain at least 8 max 16 characters.");
            password = input.getStringInputWithMessage("Enter password: ");
            // Matcher matcher = pattern.matcher(password);
            if (password.matches(regex)) {
                isPasswordChoosen = true;

            } else {
                System.out.println("Incorrect password");
            }
        }
        return password;
    }

    private String getCustomerLogin(UserController userController, String login) {
        boolean flag = true;
        while (flag) {
            login = input.getStringInputWithMessage("Enter login: ");
            if (userController.getIsLoginTaken(login)) {
                System.out.println("Login is already taken");
            } else {
                flag = false;
            }
        }
        return login;
    }
}
