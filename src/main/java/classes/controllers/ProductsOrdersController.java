package classes.controllers;

import classes.connectors.SqlConnector;
import classes.models.Order;
import classes.models.Product;

import java.sql.*;

public class ProductsOrdersController {

    private Connection c;
    private Statement st;

    public ProductsOrdersController(SqlConnector sqlConnector) {
        this.c = sqlConnector.getC();
        this.st = sqlConnector.getSt();
    }

    public void viewProductsOrdersTable() {
        final String SELECT_SQL = "SELECT * FROM products_orders;";

        try {
            ResultSet rs = st.executeQuery(SELECT_SQL);

            while (rs.next()) {
                int id = rs.getInt("id");
                int idProduct = rs.getInt("idProduct");
                int quantity = rs.getInt("quantity");
                int idOrder = rs.getInt("idOrder");

                String format = "|%1$-5s|%2$-15s|%3$-5s|%4$-5s|\n";
                System.out.printf(format, id, idProduct, quantity, idOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product product, int quantity, Order order) {
        final String INSERT_SQL = "INSERT INTO products_orders (idProduct, Quantity, IdOrder)" +
                " VALUES (?, ?, ?);";

        PreparedStatement ps = null;

        int idProduct = product.getId();
        int idOrder = order.getId();

        try {
            ps = this.c.prepareStatement(INSERT_SQL);
            ps.setInt(1, idProduct);
            ps.setInt(2, quantity);
            ps.setInt(3, idOrder);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Connection getC() {
        return c;
    }

    public Statement getSt() {
        return st;
    }


}
