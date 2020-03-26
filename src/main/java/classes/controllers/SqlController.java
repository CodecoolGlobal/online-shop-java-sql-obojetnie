package classes.controllers;

import classes.connectors.SqlConnector;

import java.sql.SQLException;

public class SqlController {

    private CategoryController categoryController;
    private ProductController productController;
    private UserController userController;
    private OrderController orderController;
    private ProductsOrdersController productsOrdersController;

    public SqlController(SqlConnector sqlConnector) {
        this.categoryController = new CategoryController(sqlConnector);
        this.productController = new ProductController(sqlConnector);
        this.userController = new UserController(sqlConnector);
        this.orderController = new OrderController(sqlConnector);
        this.productsOrdersController = new ProductsOrdersController(sqlConnector);
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

    public OrderController getOrderController() {
        return orderController;
    }

    public ProductsOrdersController getProductsOrdersController() {
        return productsOrdersController;
    }

    public void disconnectController() throws SQLException {
        getCategoryController().getC().close();
        getCategoryController().getSt().close();
        getUserController().getC().close();
        getUserController().getSt().close();
        getProductController().getC().close();
        getProductController().getSt().close();
        getOrderController().getC().close();
        getOrderController().getSt().close();
        getProductsOrdersController().getC().close();
        getProductsOrdersController().getSt().close();
    }
}
