package classes.controllers;

import classes.categories.Category;
import classes.connectors.SqlConnector;
import classes.enums.Role;
import classes.models.Order;
import classes.models.Product;
import classes.users.Admin;
import classes.users.Customer;
import classes.users.User;

import java.sql.*;
import java.time.LocalDate;

public class OrderController {
    private Connection c;
    private Statement st;
    private Customer customer;


    public OrderController(SqlConnector sqlConnector) {
        this.c = sqlConnector.getC();
        this.st = sqlConnector.getSt();

    }

    private void viewCurrentOrder() {



    }

    private void addOrder(User user) {
        final String INSERT_SQL = "INSERT INTO orders (date, idUser)" +
                " VALUES (?, ?);";

        PreparedStatement ps = null;

        try {
            ps = this.c.prepareStatement(INSERT_SQL);
            ps.setString(1, LocalDate.now().toString());
            ps.setInt(2, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Order getOrderFromDatabaseById(Customer customer, int idOrder) throws Exception {
        final String SELECT_SQL = "SELECT * FROM orders WHERE id = '" + idOrder + "'";
        try {
            ResultSet rs = st.executeQuery(SELECT_SQL);
            while (rs.next()) {
                String date = rs.getString("Date");
                int idUser = rs.getInt("idUser");
                return new Order(customer.getBasket(), idUser);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
