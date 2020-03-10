import classes.categories.Category;
import classes.controllers.SqlController;
import classes.enums.Role;
import classes.models.Product;
import classes.readers.SqlConnector;
import classes.users.Customer;


public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello!");

        Category hygiene = new Category("Hygiene");
        Category beverages = new Category("Beverages");
        Category food = new Category("Food");

        SqlConnector sqlConnector = new SqlConnector();
        sqlConnector.connectToDatabase();

        SqlController sqlController = new SqlController(sqlConnector);

        sqlController.viewUsersTable();

        System.out.println("sloneczko");

        Customer customer = new Customer("andrzej", "chrzan", "andrewatgmail.com", Role.CUSTOMER);

        sqlController.addUser(customer);

        sqlController.viewUsersTable();

        System.out.println("sloneczkoo");

        Category nonCategorized = new Category("Non Categorized");
        sqlController.addCategory(nonCategorized);

        Product product = new Product("Ruskach", 123.36, 52, beverages);
        sqlController.addProduct(product);

        sqlController.viewProductsTable();

        System.out.println("sloneczkooo");

        sqlController.viewCategoriesTable();

        System.out.println("sloneczkoooo");

        sqlConnector.disconnectFromDatabase();


    }
}
