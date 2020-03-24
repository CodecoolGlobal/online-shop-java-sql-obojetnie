package classes.menus;

import classes.connectors.SqlConnector;
import classes.controllers.SqlController;
import classes.controllers.UserController;
import classes.enums.Option;
import classes.enums.Role;
import classes.inputs.InputTaker;
import classes.users.Customer;
import classes.users.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenu {

    private SqlConnector sqlConnector;
    private SqlController sqlController;
    private InputTaker input;

    public LoginMenu() {
        this.sqlConnector = new SqlConnector();
        this.sqlConnector.connectToDatabase();
        this.sqlController = new SqlController(sqlConnector);
        this.input = new InputTaker();
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
                    login();
                }
                case TWO -> {
                    createAccount();
                }
                case ZERO -> {
                    sqlController.disconnectController();
                    isRunning = false;
                }
            }
        }
    }

    private String createCustomerEmail(UserController userController) {
        String email = "";
        boolean flag = true;
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

    private String createCustomerPassword() {
        String regex = "^[a-zA-Z0-9]{8,16}$";
        Pattern pattern = Pattern.compile(regex);
        String password = "";
        boolean isPasswordChoosen = false;
        while (!isPasswordChoosen) {
            System.out.println("Password must contain at least 8 max 16 characters.");
            password = input.getStringInputWithMessage("Enter password: ");
            Matcher matcher = pattern.matcher(password);
            if (matcher.matches()) {
                isPasswordChoosen = true;
            } else {
                System.out.println("Incorrect password");
            }
        }
        return password;
    }

    private String createCustomerLogin(UserController userController) {
        String login = "";
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

    private void createAccount() throws Exception {
        UserController userController = sqlController.getUserController();
        String login = createCustomerLogin(userController);
        String password = createCustomerPassword();
        String email = createCustomerEmail(userController);

        try {
            userController.addUser(new Customer(login, password, email, Role.CUSTOMER));
        } catch (Exception e) {
            throw new Exception("Something went wrong");
        }
    }

    private String getLoginFromUser(UserController userController) {
        String loginInput = "";
        boolean isLoginValid = false;
        while (!isLoginValid) {
            loginInput = input.getStringInputWithMessage("Please enter your login: ");
            if (userController.getIsLoginTaken(loginInput)) {
                isLoginValid = true;
            } else {
                System.out.println("Invalid login");
            }
        }
        return loginInput;
    }

    private String checkIfPasswordMatches(UserController userController, String loginInput) {
        String passwordInput = "";
        boolean isPasswordValid = false;
        while (!isPasswordValid) {
            passwordInput = input.getStringInputWithMessage("Please enter your password: ");
            if (userController.getIsPasswordMatching(loginInput, passwordInput)) {
                isPasswordValid = true;
            } else {
                System.out.println("Invalid password");
            }
        }
        return passwordInput;
    }

    private void login() throws Exception {
        UserController userController = sqlController.getUserController();
        String loginInput = getLoginFromUser(userController);
        String passwordInput = checkIfPasswordMatches(userController, loginInput);
        User user = userController.loginUser(loginInput, passwordInput);
        Role role = user.getRole();
        switch (role) {
            case ADMIN -> new AdminMenu(user);
            case CUSTOMER -> new CustomerMenu(user);
            default -> throw new Exception("Something went wrong.");
        }
    }
}
