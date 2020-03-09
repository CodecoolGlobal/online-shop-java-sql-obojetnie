package classes.models;

import classes.categories.Category;

public class Product {

    private int id;
    private String name;
    private double price;
    private int quantity;
    private boolean isAvailable;
    private Category category;
    private int rate;

    public Product(String name, double price, int quantity, Category category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return quantity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Category getCategory() {
        return category;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int amount) {
        this.quantity = amount;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
