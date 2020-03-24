package classes.menus;

import classes.connectors.SqlConnector;
import classes.controllers.ProductController;
import classes.controllers.SqlController;
import classes.enums.Option;
import classes.inputs.InputTaker;
import classes.models.Product;
import classes.users.Customer;
import classes.users.User;

import java.sql.SQLException;

public class CustomerMenu {

    private SqlConnector sqlConnector;
    private SqlController sqlController;
    private InputTaker input;
    private Customer customer;

    public CustomerMenu(User user) throws Exception {
        this.sqlConnector = new SqlConnector();
        this.sqlConnector.connectToDatabase();
        this.sqlController = new SqlController(sqlConnector);
        this.input = new InputTaker();
        this.customer = (Customer) user;
        displayCustomerMenu();
    }

    public void displayCustomerMenu() throws Exception {
        boolean isRunning = true;
        System.out.println("You are logged as customer");
        while(isRunning) {
            System.out.println("""
                    (1) Add product to basket
                    (2) Delete product from basket
                    (3) See all products from basket
                    (4) Place an order
                    (5) See previous orders
                    (6) See all available products
                    (7) See all products from specific category
                    (8) Check availability of specific product
                    (9) Rate product
                    (10) See statistic of your orders
                    (0) Quit""");
            Option option = input.getOptionInt();
            switch (option) {
                case ONE:
                    addItemToBasket();
                    break;
                case TWO:
                    break;
                case THREE:
                    checkBasket();
                    break;
                case FOUR:;
                    placeOrder();
                    break;
                case FIVE:
                    ordersHistory();
                    break;
                case SIX:
                    viewListOfAvailableProducts();
                    break;
                case SEVEN:
                    viewProductsInCategory();
                    break;
                case EIGHT:
                    checkIfProductAvailable();
                    break;
                case NINE:
                    rateItem();
                case ZERO:
                    isRunning = false;
                default: throw new Exception("Something went wrong.");
            }
        }
    }

    private void addItemToBasket() throws SQLException {
        ProductController productController = sqlController.getProductController();
        int id = input.getIntInputWithMessage("Insert id of product you want to put into basket: ");
        Product product = productController.getProductFromDatabaseById(id);
        int quantity = input.getIntInputWithMessage("Insert quantity: ");
        customer.getBasket().addProduct(product, quantity);
        checkBasket();
    }

    private void deleteItemFromBasket() {
    }

    private void checkBasket() {
        customer.getBasket().viewBasket();
    }

    private void placeOrder() {

    }

    private void ordersHistory() {

    }

    private void viewListOfAvailableProducts() throws SQLException {
        ProductController productController = sqlController.getProductController();
        productController.viewProductsTable();
    }

    private void viewProductsInCategory() {

    }

    private void checkIfProductAvailable() {

    }

    private void rateItem() {

    }

    private void getOrdersStatistics() {

    }
}
