package classes.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class Basket {

    private int id;
    Map<Product, Integer> basket;

    public Basket() {
        basket = new HashMap<>();
    }

    public Map<Product, Integer> getBasketMap() {
        return basket;
    }

    public void addProduct(Product product, int quantity) {
        double price = round(product.getPrice() * quantity, 2);
        product.setPrice(price);
        basket.put(product, quantity);
    }

    public void deleteProduct(Product product) {
        basket.remove(product);
    }

    public void viewBasket() {
        String format = "|%1$-3s|%2$-18s|%3$-5s|%4$-4s|%5$-5s|\n";
        int i = 1;

        for (Product product : basket.keySet()) {
            String name = product.getName();
            double price = product.getPrice();
            int quantity = basket.get(product);
            double rate = product.getRate();

            System.out.printf(format, i, name, price, quantity, rate);
            i++;
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

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


}
