package classes.controllers;

import classes.connectors.SqlConnector;
import classes.models.Order;
import classes.users.Customer;
import classes.users.User;

import java.sql.*;
import java.time.LocalDate;

public class OrderController {

    private Connection c;
    private Statement st;

    public OrderController(SqlConnector sqlConnector) {
        this.c = sqlConnector.getC();
        this.st = sqlConnector.getSt();
    }

    public void viewOrdersTable() {
        final String SELECT_SQL = "SELECT * FROM orders;";

        try {
            ResultSet rs = st.executeQuery(SELECT_SQL);

            while (rs.next()) {
                int idOrder = rs.getInt("id");
                String date = rs.getString("date");
                int idUser = rs.getInt("idUser");

                String format = "|%1$-5s|%2$-15s|%3$-5s|\n";
                System.out.printf(format, idOrder, date, idUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOrder(User user) {
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

    public Order getOrderFromDatabaseById(Customer customer, int idOrder) throws Exception {
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


    public Connection getC() {
        return c;
    }

    public Statement getSt() {
        return st;
    }

}
