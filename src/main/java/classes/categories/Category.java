package classes.categories;

import classes.models.Product;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private int id;
    private String name;
    private boolean isAvailable;
    private List<Product> productList;

    public Category(String name) {
        this.name = name;
        productList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void addProduct(Product product) {
        this.productList.add(product);
    }

}
