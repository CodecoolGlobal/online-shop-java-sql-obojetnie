package classes.models;

import classes.enums.OrderStatus;
import classes.users.User;

import java.util.Date;

public class Order {

    private int id;
    private Basket basket;
    private User user;
    private Date creationDate;
    private OrderStatus orderStatus;
    private Date orderPayAt;

    public Order(Basket basket, User user) {
        this.creationDate = new Date();
        this.basket = basket;
        this.user = user;
    }

    public Date getCreationDate() {
        return creationDate;
    }

}
