package classes.models;

import java.util.*;

public class Basket {

    private int id;
    Map<Product, Integer> basket;

    public Basket() {
        basket = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        basket.put(product, quantity);
    }

    public void deleteProduct(Product product) {
        basket.remove(product);
    }

    public void viewBasket() {
        String format = "|%1$-18s|%2$-5s|%3$-4s|%4$-5s|\n";

        for (Product product : basket.keySet()) {
            String name = product.getName();
            double price = product.getPrice();
            int quantity = product.getQuantity();
            double rate = product.getRate();

            System.out.printf(format, name, price, quantity, rate);
        }
    }

    public Product getProduct(String name) {
        for (Product product : basket.keySet()) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

}
