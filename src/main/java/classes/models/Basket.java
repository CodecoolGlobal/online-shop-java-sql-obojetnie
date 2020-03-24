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

    public Product getProduct(String name) {
        for (Product product : basket.keySet()) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

}
