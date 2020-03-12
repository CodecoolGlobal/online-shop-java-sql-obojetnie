package classes.menus;

import classes.categories.Category;
import classes.enums.Option;
import classes.models.Product;


public class AdminMenu {
    InputTaker input = new InputTaker();

    public void displayAdminMenu() throws Exception {
        boolean isRunning = true;
        System.out.println("You are logged as admin");
        while(isRunning) {
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
            switch(option) {
                case ONE:
                    String productName = input.getStringInputWithMessage("Enter a product name: ");
                    double productPrice = input.getIntinputWithMessage("Enter price of product: ");
                    int productQuantity = input.getIntinputWithMessage("Enter quantity of product: ");
                    String productCategory = input.getStringInputWithMessage("Enter product Category: ");
                    Category category = new Category(productCategory);

                    Product createdProduct = new Product(productName, productPrice, productQuantity, category);
                    break;
                case TWO:
                    String createdCategory = input.getStringInputWithMessage("Enter name of new product category: ");
                    Category newCategory = new Category(createdCategory);
                    break;
                case THREE:
                    System.out.println("""
                            (1) Product name
                            (2) Product price
                            (3) Product quantity
                            (4) Product category""");
                    System.out.println("What do you want to edit?");
                    option = input.getOptionInt();
                    switch(option) {
                        case ONE:
                            System.out.println("Editing product name");
                        case TWO:
                            System.out.println("Editing product price");
                        case THREE:
                            System.out.println("Editing product quantity");
                        case FOUR:
                            System.out.println("Editing product category");
                            break;
                        default: throw new Exception("Something went wrong.");
                    }
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
                default: throw new Exception("Something went wrong.");
            }
        }
    }

    public void CreateCategory() {

    }

    public void deleteCategory() {

    }

    public void editCategory() {

    }

    public void addProduct() {

    }

    public void deleteProduct() {

    }

    public void editProduct() {

    }

    public void checkOngoingOrders() {

    }

    public void collectFeedback() {

    }
}
