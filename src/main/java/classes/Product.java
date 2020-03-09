package classes;

import classes.categories.Category;

public class Product {

    private int id;
    private String name;
    private double price;
    private int amount;
    private boolean isAvailable;
    private Category category;

    public Product(String name, double price, int amount, Category category) {
        this.name = name;
        this.price = price;
        this.amount = amount;
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
        return amount;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Category getCategory() {
        return category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
