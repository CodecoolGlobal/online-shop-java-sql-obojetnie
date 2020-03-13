package classes.controllers;

import classes.connectors.SqlConnector;

import java.sql.SQLException;

public class SqlController {

    private SqlConnector sqlConnector;
    private CategoryController categoryController;
    private ProductController productController;
    private UserController userController;

    public SqlController(SqlConnector sqlConnector) {
        categoryController = new CategoryController(sqlConnector);
        productController = new ProductController(sqlConnector);
        userController = new UserController(sqlConnector);
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
        sqlConnector.disconnectFromDatabase();
    }
}
