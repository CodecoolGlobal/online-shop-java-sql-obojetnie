package classes.menus;

import classes.connectors.SqlConnector;
import classes.categories.Category;
import classes.controllers.CategoryController;
import classes.controllers.ProductController;
import classes.controllers.SqlController;
import classes.enums.Option;
import classes.inputs.InputTaker;
import classes.menus.exceptions.AvailabilityException;
import classes.menus.exceptions.OptionEnumException;
import classes.models.Product;

import java.sql.SQLException;


public class AdminMenu {

    SqlConnector sqlConnector;
    SqlController sqlController;
    InputTaker input;

    public AdminMenu() throws Exception, AvailabilityException, OptionEnumException {
        sqlConnector = new SqlConnector();
        sqlConnector.connectToDatabase();
        sqlController = new SqlController(sqlConnector);
        input = new InputTaker();
        displayAdminMenu();
    }

    public void displayAdminMenu() throws Exception, AvailabilityException, OptionEnumException {
        boolean isRunning = true;
        System.out.println("You are logged as admin");
        while (isRunning) {
            System.out.println("""
                    (1) Create new product
                    (2) Create new product category
                    (3) Edit product
                    (4) Deactivate product
                    (5) Delete category
                    (6) Edit category
                    (7) Delete product
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
                    deactivateProduct();
                    break;
                case FIVE:
                    deleteCategory();
                    break;
                case SIX:
                    editCategory();
                    break;
                case SEVEN:
                    deleteProduct();
                    break;
                default:
                    throw new OptionEnumException("No such field in OptionEnum");
            }
        }
    }

    public void createCategory() {
        String createdCategory = input.getStringInputWithMessage("Enter name of new product category: ");
        Category category = new Category(createdCategory);
        sqlController.getCategoryController().addCategory(category);
    }

    public void deleteCategory() {
        CategoryController categoryController = sqlController.getCategoryController();
        categoryController.viewCategoriesTable();
        int idOfCategoryToDelete = input.getIntInputWithMessage("Enter id of category you want to delete: ");
        categoryController.deleteCategory(idOfCategoryToDelete);
    }

    public void editCategory() {

    }

    public void addProduct() {

        String productName = input.getStringInputWithMessage("Enter a product name: ");
        double productPrice = input.getDoubleInputWithMessage("Enter price of product: ");
        int productQuantity = input.getIntInputWithMessage("Enter quantity of product: ");
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
        int productId = input.getIntInputWithMessage("Enter id of product you want to edit: ");
        Product productToEdit = productController.getProductFromDatabaseById(productId);
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
                int editOrUpdate = input.getIntInputWithMessage("""
                        Do you want to edit or add quantity?
                        (1) Edit
                        (2) Add""");
                switch (editOrUpdate) {
                    case 1 -> {
                        int newQuantity = input.getIntInputWithMessage("Enter new quantity of product: ");
                        productController.editProductQuantity(productToEdit, newQuantity);
                    }
                    case 2 -> {
                        int addon = input.getIntInputWithMessage("Enter amount to add: ");
                        productController.updateQuantity(productToEdit, addon);
                    }
                }

                break;
            case FOUR:
                CategoryController categoryController = new CategoryController(sqlConnector);
                categoryController.viewCategoriesTable();
                int newCategory = input.getIntInputWithMessage("Enter id of new category: ");
                productController.editProductCategory(productToEdit, newCategory);
                break;
            default:
                throw new Exception("Something went wrong.");
        }
    }

    private void deactivateProduct() throws SQLException, AvailabilityException {
        ProductController productController = sqlController.getProductController();
        productController.viewProductsTable();
        int productId = input.getIntInputWithMessage("Enter id of product you want to edit: ");
        Product product = productController.getProductFromDatabaseById(productId);

        int availability = switch (input.getIntInputWithMessage("""
        Choose availability:
        (1) Available
        (0) Not available""")) {
            case 1 -> 1;
            case 0 -> 0;
            default -> throw new AvailabilityException("Non existing availability case");
        };
        productController.updateAvailability(product, availability);
    }




    public void checkOngoingOrders() {

    }

    public void collectFeedback() {

    }
}
