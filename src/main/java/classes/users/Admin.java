package classes.users;

import classes.enums.Role;

public class Admin extends User {

    public Admin(String login, String password, String email, Role role) {
        super(login, password, email, role);
    }

}
