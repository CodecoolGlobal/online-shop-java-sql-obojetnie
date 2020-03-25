package classes.models;

import classes.enums.OrderStatus;
import classes.users.User;

import java.util.Date;

public class Order {

    private int id;
    private Basket basket;
    private int idUser;
    private Date creationDate;
    private OrderStatus orderStatus;
    private Date orderPayAt;

    public Order(Basket basket, int idUser) {
        this.creationDate = new Date();
        this.basket = basket;
        this.idUser = idUser;
    }
}
