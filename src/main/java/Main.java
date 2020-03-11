import classes.categories.Category;
import classes.controllers.SqlController;
import classes.enums.Role;
import classes.models.Product;
import classes.SqlConnector;
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

        System.out.println();
        System.out.println("sloneczko");
        System.out.println();

        Customer customer = new Customer("andrzej", "chrzan", "andrewatgmail.com", Role.CUSTOMER);

        sqlController.addUser(customer);

        sqlController.viewUsersTable();

        System.out.println();
        System.out.println("sloneczkoo");
        System.out.println();

        Category nonCategorized = new Category("Non Categorized");
        sqlController.addCategory(nonCategorized);

        Product product = new Product("Ruskach", 123.36, 52, beverages);
        sqlController.addProduct(product);

        sqlController.viewProductsTable();

        System.out.println();
        System.out.println("sloneczkooo");
        System.out.println();

        sqlController.viewCategoriesTable();

        System.out.println();
        System.out.println("sloneczkoooo");
        System.out.println();

        Product soap = new Product("Soap", 1.99, 30, hygiene);
        System.out.println(sqlController.getIsProductInDatabase(soap));
        sqlController.addProduct(soap);

        sqlController.viewProductsTable();

        System.out.println();
        System.out.println("sloneczkooooo");
        System.out.println();

        sqlController.updateAvailability(soap, 0);

        sqlController.viewProductsTable();

        System.out.println();
        System.out.println("sloneczkoooooo");
        System.out.println();

        sqlConnector.disconnectFromDatabase();


    }
}
