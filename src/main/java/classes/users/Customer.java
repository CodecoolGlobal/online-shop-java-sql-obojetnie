package classes.users;

import classes.enums.Role;
import classes.models.Basket;
import classes.models.Product;

public class Customer extends User {

    private Basket basket;

    public Customer(int id, String login, String password, String email, Role role) {
        super(id, login, password, email, role);
    }

    public Basket getBasket() {
        return this.basket;
    }


}
