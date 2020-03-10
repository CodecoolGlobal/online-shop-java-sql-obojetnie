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

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderPayAt() {
        return orderPayAt;
    }

    public void setOrderPayAt(Date orderPayAt) {
        this.orderPayAt = orderPayAt;
    }

}
