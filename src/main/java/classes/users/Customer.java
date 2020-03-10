package classes.users;

import classes.enums.Role;

public class Customer extends User {

    public Customer(String login, String password, String email, Role role) {
        super(login, password, email, role);
    }

}
