import classes.categories.Category;
import classes.controllers.CategoryController;
import classes.controllers.ProductController;
import classes.controllers.SqlController;
import classes.controllers.UserController;
import classes.enums.Role;
import classes.menus.LoginMenu;
import classes.models.Product;
import classes.SqlConnector;
import classes.users.Customer;


public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello!");

        LoginMenu loginMenu = new LoginMenu();
        loginMenu.displayLoginMenu();

        Category hygiene = new Category("Hygiene");
        Category beverages = new Category("Beverages");
        Category food = new Category("Food");

        SqlConnector sqlConnector = new SqlConnector();
        sqlConnector.connectToDatabase();

        SqlController sqlController = new SqlController(sqlConnector);

        CategoryController categoryController = sqlController.getCategoryController();
        ProductController productController = sqlController.getProductController();
        UserController userController = sqlController.getUserController();

        userController.viewUsersTable();

        System.out.println();
        System.out.println("sloneczko");
        System.out.println();

        Customer customer = new Customer("andrzej", "chrzan", "andrewatgmail.com", Role.CUSTOMER);

        userController.addUser(customer);

        userController.viewUsersTable();

        System.out.println();
        System.out.println("sloneczkoo");
        System.out.println();

        Category nonCategorized = new Category("Non Categorized");
        categoryController.addCategory(nonCategorized);

        Product product = new Product("Ruskach", 123.36, 52, beverages);
        productController.addProduct(product);

        productController.viewProductsTable();

        System.out.println();
        System.out.println("sloneczkooo");
        System.out.println();

        categoryController.viewCategoriesTable();

        System.out.println();
        System.out.println("sloneczkoooo");
        System.out.println();

        Product soap = new Product("Soap", 1.99, 30, hygiene);
        System.out.println(productController.getIsProductInDatabase(soap));
        productController.addProduct(soap);

        productController.viewProductsTable();

        System.out.println();
        System.out.println("sloneczkooooo");
        System.out.println();

        productController.updateAvailability(soap, 0);

        productController.viewProductsTable();

        System.out.println();
        System.out.println("sloneczkoooooo");
        System.out.println();

        sqlConnector.disconnectFromDatabase();

    }
}
