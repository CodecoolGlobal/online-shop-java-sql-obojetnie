package classes.menus;

import classes.categories.Category;
import classes.connectors.SqlConnector;
import classes.controllers.CategoryController;
import classes.controllers.ProductController;
import classes.controllers.SqlController;
import classes.enums.Option;
import classes.inputs.InputTaker;
import classes.menus.exceptions.AvailabilityException;
import classes.menus.exceptions.OptionEnumException;
import classes.models.Product;
import classes.users.Admin;
import classes.users.User;

import java.sql.SQLException;

public class AdminMenu {

    private SqlConnector sqlConnector;
    private SqlController sqlController;
    private InputTaker input;
    private Admin admin;

    public AdminMenu(User user) throws Exception, AvailabilityException, OptionEnumException {
        this.sqlConnector = new SqlConnector();
        this.sqlConnector.connectToDatabase();
        this.sqlController = new SqlController(sqlConnector);
        this.input = new InputTaker();
        this.admin = (Admin) user;
        displayAdminMenu();
    }

    public void displayAdminMenu() throws Exception, AvailabilityException, OptionEnumException {
        boolean isRunning = true;
        System.out.println("You are logged as an admin");
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
                case ONE -> addProduct();
                case TWO -> createCategory();
                case THREE -> editProduct();
                case FOUR -> deactivateProduct();
                case FIVE -> deleteCategory();
                case SIX -> editCategory();
                case SEVEN -> deleteProduct();
                case ZERO -> isRunning = false;
                default -> System.out.println("Wrong input.");
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
        int id = input.getIntInputWithMessage("Enter id of category you want to delete: ");
        categoryController.deleteCategory(id);
    }

    public void editCategory() {
        CategoryController categoryController = sqlController.getCategoryController();
        categoryController.viewCategoriesTable();
        int idOfCategoryToDelete = input.getIntInputWithMessage("Enter id of category you want to edit: ");
        String newName = input.getStringInputWithMessage("Enter new name: ");
        String formattedNewName = newName.substring(0, 1).toUpperCase() + newName.substring(1).toLowerCase();
        categoryController.editCategoryNameById(idOfCategoryToDelete, formattedNewName);
    }

    public void addProduct() throws SQLException {
        String productName = input.getStringInputWithMessage("Enter a product name: ");
        String formattedProductName = productName.substring(0, 1).toUpperCase() + productName.substring(1).toLowerCase();
        double productPrice = input.getDoubleInputWithMessage("Enter price of product: ");
        int productQuantity = input.getIntInputWithMessage("Enter quantity of product: ");
        String productCategoryName = input.getStringInputWithMessage("Enter product Category: ");
        String formattedProductCategoryName = productCategoryName.substring(0, 1).toUpperCase() + productCategoryName.substring(1).toLowerCase();
        Category category = new Category(formattedProductCategoryName);
        sqlController.getProductController().addProduct(new Product(formattedProductName, productPrice, productQuantity, category));
    }

    public void deleteProduct() throws SQLException {
        ProductController productController = sqlController.getProductController();
        productController.viewProductsTable();
        int id = input.getIntInputWithMessage("Enter id of category you want to delete: ");
        productController.deleteProduct(id);
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
                        int value = input.getIntInputWithMessage("Enter amount to add: ");
                        productController.addQuantity(productToEdit, value);
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
                System.out.println("Wrong input.");
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

}
