package classes.menus;

import classes.connectors.SqlConnector;
import classes.controllers.*;
import classes.enums.Option;
import classes.inputs.InputTaker;
import classes.menus.exceptions.OptionEnumException;
import classes.models.Basket;
import classes.models.Order;
import classes.models.Product;
import classes.users.Customer;
import classes.users.User;

import java.sql.SQLException;
import java.util.Map;

public class CustomerMenu {

    private SqlConnector sqlConnector;
    private SqlController sqlController;
    private InputTaker input;
    private Customer customer;

    public CustomerMenu(User user) throws Exception, OptionEnumException {
        this.sqlConnector = new SqlConnector();
        this.sqlConnector.connectToDatabase();
        this.sqlController = new SqlController(sqlConnector);
        this.input = new InputTaker();
        this.customer = (Customer) user;
        displayCustomerMenu();
    }

    public void displayCustomerMenu() throws Exception, OptionEnumException {
        boolean isRunning = true;
        System.out.println("You are logged as customer");
        while (isRunning) {
            System.out.println("""

                    (1) Add product to basket
                    (2) Delete product from basket
                    (3) See all products from basket
                    (4) Place an order
                    (5) See previous orders
                    (6) See all available products
                    (7) See all products from specific category
                    (8) Check availability of specific product
                    (0) Quit\n""");
            Option option = input.getOptionInt();
            switch (option) {
                case ONE:
                    addItemToBasket();
                    break;
                case TWO:
                    deleteItemFromBasket();
                    break;
                case THREE:
                    checkBasket();
                    break;
                case FOUR:
                    placeOrder();
                    break;
                case FIVE:
                    orderHistory();
                    break;
                case SIX:
                    viewListOfAvailableProducts();
                    break;
                case SEVEN:
                    viewProductsInCategory();
                    break;
                case EIGHT:
                    checkIfProductIsAvailable();
                    break;
                case ZERO:
                    isRunning = false;
            }
        }
    }

    private void addItemToBasket() throws SQLException {
        ProductController productController = sqlController.getProductController();
        int id = input.getIntInputWithMessage("Insert id of product you want to put into basket: ");
        Product product = productController.getProductFromDatabaseById(id);
        int quantity = input.getIntInputWithMessage("Insert quantity: ");
        customer.getBasket().addProduct(product, quantity);
    }

    private void deleteItemFromBasket() {
        checkBasket();
        int indexOfItem = input.getIntInputWithMessage("Select index of item to delete: ");
        Basket basket = customer.getBasket();
        Map<Product, Integer> basketMap = basket.getBasketMap();
        int i = 1;
        for (Product product : basketMap.keySet()) {
            if (i == indexOfItem) {
                basket.deleteProduct(product);
                break;
            }
            i++;
        }
    }

    private void checkBasket() {
        customer.getBasket().viewBasket();
    }

    private void placeOrder() throws Exception {
        OrderController orderController = sqlController.getOrderController();
        ProductsOrdersController productsOrdersController = sqlController.getProductsOrdersController();
        ProductController productController = sqlController.getProductController();

        Basket basket = customer.getBasket();
        int idUser = customer.getId();

        Order newOrder = new Order(basket, idUser);
        orderController.addOrder(newOrder);
        Order orderWithId = orderController.getOrderFromDatabase(customer, newOrder);

        Map<Product, Integer> basketMap = basket.getBasketMap();

        for (Product product : basketMap.keySet()) {
            int quantity = basketMap.get(product);
            productsOrdersController.addProduct(product, quantity, orderWithId);
            productController.decreaseQuantity(product, quantity);
        }

        customer.addOrderToHistory(newOrder);
    }

    private void orderHistory() {
        OrderController orderController = sqlController.getOrderController();
        orderController.viewOrdersTable();
    }

    private void viewListOfAvailableProducts() throws SQLException {
        ProductController productController = sqlController.getProductController();
        productController.viewProductsTable();
        System.out.println("\n");
    }

    private void viewProductsInCategory() {
        CategoryController categoryController = sqlController.getCategoryController();
        categoryController.viewCategoriesTable();
        ProductController productController = sqlController.getProductController();
        int idCategory = input.getIntInputWithMessage("Select id of category whom products you want to see: ");
        System.out.println("Category: " + categoryController.getCategoryFromDatabaseById(idCategory).getName());
        productController.viewAllProductsFromCategory(idCategory);
    }

    private void checkIfProductIsAvailable() {
        ProductController productController = sqlController.getProductController();
        int id = input.getIntInputWithMessage("Insert id of product: ");
        boolean isAvailable = productController.checkAvailability(id);
        if (isAvailable) {
            System.out.println("Available");
        } else {
            System.out.println("Not available");
        }
    }

    private void rateItem() {

    }

    private void getOrdersStatistics() {

    }
}
