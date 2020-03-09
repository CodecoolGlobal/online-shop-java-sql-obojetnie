package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Basket {

    private int id;
    ListIterator<Product> listIterator;
    private List<Product> productList;

    public Basket() {
        productList = new ArrayList<>();
        listIterator = productList.listIterator();
    }

    public ListIterator<Product> getListIterator() {
        return listIterator;
    }

    public void addProduct(Product product) {
        this.productList.add(product);
    }

    public void deleteProduct() {

    }

}
