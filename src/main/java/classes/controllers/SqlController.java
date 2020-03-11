package classes.controllers;

import classes.SqlConnector;

public class SqlController {

    private CategoryController categoryController;
    private ProductController productController;
    private UserController userController;

    public SqlController(SqlConnector sqlConnector) {
        categoryController = new CategoryController(sqlConnector);
        productController = new ProductController(sqlConnector);
        userController = new UserController(sqlConnector);
    }

    public CategoryController getCategoryController() {
        return categoryController;
    }

    public ProductController getProductController() {
        return productController;
    }

    public UserController getUserController() {
        return userController;
    }

}
