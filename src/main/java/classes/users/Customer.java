package classes.users;

import classes.enums.Role;
import classes.models.Basket;
import classes.models.Order;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    private Basket basket;
    private List<Order> orderHistory;

    public Customer(int id, String login, String password, String email, Role role) {
        super(id, login, password, email, role);
        basket = new Basket();
        orderHistory = new ArrayList<>();
    }

    public Basket getBasket() {
        return this.basket;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void addOrderToHistory(Order order) {
        this.orderHistory.add(order);
    }

}
