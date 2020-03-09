import classes.models.Basket;
import classes.models.Order;
import classes.models.Product;
import classes.users.Customer;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello!");

        Order order = new Order(new Basket(), new Customer());
        System.out.println(order.getCreationDate());

    }
}
