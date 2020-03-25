package classes.controllers;

import classes.connectors.SqlConnector;

import java.sql.SQLException;

public class SqlController {

    private SqlConnector sqlConnector;
    private CategoryController categoryController;
    private ProductController productController;
    private UserController userController;
    private OrderController orderController;

    public SqlController(SqlConnector sqlConnector) {
        this.categoryController = new CategoryController(sqlConnector);
        this.productController = new ProductController(sqlConnector);
        this.userController = new UserController(sqlConnector);
        this.orderController = new OrderController(sqlConnector);
        this.sqlConnector = sqlConnector;
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

    public SqlConnector getSqlConnector() {
        return sqlConnector;
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
        sqlConnector.disconnectFromDatabase();
    }
}
