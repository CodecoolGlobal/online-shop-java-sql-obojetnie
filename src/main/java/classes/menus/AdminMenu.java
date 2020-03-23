package classes.menus;

import classes.connectors.SqlConnector;
import classes.categories.Category;
import classes.controllers.ProductController;
import classes.controllers.SqlController;
import classes.enums.Option;
import classes.inputs.InputTaker;
import classes.models.Product;

import java.sql.SQLException;


public class AdminMenu {

    SqlConnector sqlConnector;
    SqlController sqlController;
    InputTaker input;

    public AdminMenu() throws Exception {
        sqlConnector = new SqlConnector();
        sqlConnector.connectToDatabase();
        sqlController = new SqlController(sqlConnector);
        input = new InputTaker();
        displayAdminMenu();
    }

    public void displayAdminMenu() throws Exception {
        boolean isRunning = true;
        System.out.println("You are logged as admin");
        while (isRunning) {
            System.out.println("""
                    (1) Create new product
                    (2) Create new product category
                    (3) Edit product
                    (4) Edit product category name
                    (5) Deactivate product
                    (6) Collect feedback from users
                    (7) See statistics of user feedbacks
                    (8) See list of ongoing orders
                    (0) Quit""");
            Option option = input.getOptionInt();
            switch (option) {
                case ONE:
                    addProduct();
                    break;
                case TWO:
                    createCategory();
                    break;
                case THREE:
                    editProduct();
                    break;
                case FOUR:
                    String editCategoryName = input.getStringInputWithMessage("Which category name do you want to edit?");
                    System.out.println("Changing category name");
                    break;
                case FIVE:
                    //there will be list of products
                    String deactivatedProduct = input.getStringInputWithMessage("Which product do you want to deactivate?");
                    break;
                case SIX:
                    //collecting User rates of products
                    System.out.println("Collect feedback");
                    break;
                case SEVEN:
                    // table with statistics
                    System.out.println("Statistics from users");
                    break;
                case EIGHT:
                    //table with ongoing orders
                    System.out.println("List of ongoing orders");
                case NINE:
                    isRunning = false;
                default:
                    throw new Exception("Something went wrong.");
            }
        }
    }

    public void createCategory() {
        String createdCategory = input.getStringInputWithMessage("Enter name of new product category: ");
        Category category = new Category(createdCategory);
        sqlController.getCategoryController().addCategory(category);
    }

    public void deleteCategory() {

    }

    public void editCategory() {

    }

    public void addProduct() {

        String productName = input.getStringInputWithMessage("Enter a product name: ");
        double productPrice = input.getDoubleInputWithMessage("Enter price of product: ");
        int productQuantity = input.getIntinputWithMessage("Enter quantity of product: ");
        String productCategory = input.getStringInputWithMessage("Enter product Category: ");
        Category category = new Category(productCategory);

        sqlController.getProductController().addProduct(new Product(productName, productPrice, productQuantity, category));

    }

    public void deleteProduct() {

    }

    public void editProduct() throws Exception {
        Option option;
        ProductController productController = sqlController.getProductController();
        productController.viewProductsTable();
        String productName = input.getStringInputWithMessage("Enter name of product you want to edit: ");

        Product productToEdit = productController.getProductFromDatabase(productName);
        System.out.println("""
                            (1) Product name
                            (2) Product price
                            (3) Product quantity
                            (4) Product category""");
        option = input.getOptionIntWithMessage("What do you want to edit?");

        switch (option) {
            case ONE:
                String productNewName = input.getStringInputWithMessage("Enter new name of product: ");
                productController.editProductName(productToEdit, productNewName);
                productController.viewProductsTable();
                break;
            case TWO:
                double newPrice = input.getDoubleInputWithMessage("Enter new price of product: ");
                productController.editProductPrice(productToEdit, newPrice);
                break;
            case THREE:
                int newQuantity = input.getIntinputWithMessage("Enter new quantity of product: ");
                productController.editProductQuantity(productToEdit, newQuantity);
                break;
            case FOUR:
                String newCategory = input.getStringInputWithMessage("Enter new category of product: ");
                break;
            default:
                throw new Exception("Something went wrong.");
        }
    }

    public void checkOngoingOrders() {

    }

    public void collectFeedback() {

    }
}
