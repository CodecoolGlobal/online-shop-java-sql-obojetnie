package classes.users;

import classes.enums.Role;
import classes.models.Basket;

public class Customer extends User {

    private Basket basket;

    public Customer(int id, String login, String password, String email, Role role) {
        super(id, login, password, email, role);
        basket = new Basket();
    }

    public Basket getBasket() {
        return this.basket;
    }


}
