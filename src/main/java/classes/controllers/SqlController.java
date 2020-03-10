package classes.controllers;

import classes.categories.Category;
import classes.enums.Role;
import classes.models.Product;
import classes.SqlConnector;
import classes.users.User;

import java.sql.*;

public class SqlController {

    private SqlConnector sqlConnector;
    private Connection c;
    private Statement st;

    public SqlController(SqlConnector sqlConnector) {
        this.sqlConnector = sqlConnector;
        this.c = sqlConnector.getC();
        this.st = sqlConnector.getSt();
    }

    public void viewUsersTable() throws SQLException {
        String query = "SELECT * FROM users;";

        try {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("Id");
                String login = rs.getString("Login");
                String password = rs.getString("Password");
                String email = rs.getString("Email");
                int roleId = rs.getInt("IdRole");
                String role = switch (roleId) {
                    case 1 -> "Admin";
                    case 2 -> "Customer";
                    default -> throw new Exception("No matching role Id");
                };

                String format = "|%1$-3s|%2$-18s|%3$-16s|%4$-30s|%5$-9s|\n";
                System.out.printf(format, id, login, password, email, role);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewProductsTable() throws SQLException {
        String query = "SELECT * FROM products;";

        try {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                double price = rs.getDouble("Price");
                String quantity = rs.getString("Quantity");
                int availability = rs.getInt("Availability");
                int idCategory = rs.getInt("idCategory");
                double rate = rs.getDouble("Rate");

                String format = "|%1$-3s|%2$-18s|%3$-16s|%4$-30s|%5$-9s|%6$-20s|%7$-10s|\n";
                System.out.printf(format, id, name, price, quantity, availability, idCategory, rate);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewCategoriesTable() {
        String query = "SELECT * FROM categories";

        try {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");

                String format = "|%1$-3s|%2$-18s|\n";
                System.out.printf(format, id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {
        final String INSERT_SQL = "INSERT INTO users (Login, Password, Email, IdRole)" +
                "VALUES (?, ?, ?, ?);";

        String login = user.getLogin();
        String password = user.getPassword();
        String email = user.getEmail();
        Role userRole = user.getRole();
        int roleId = switch (userRole) {
            case ADMIN -> 1;
            case CUSTOMER -> 2;
        };

        PreparedStatement ps = null;

        try {
            ps = this.c.prepareStatement(INSERT_SQL);
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setInt(4, roleId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product product) {
        final String INSERT_SQL = "INSERT INTO products (name, price, quantity, availability, idCategory, rate)" +
                "VALUES (?, ?, ?, ?, ?, ?);";

        String name = product.getName();
        double price = product.getPrice();
        int quantity = product.getQuantity();
        int availability = 1;
        if (quantity == 0) {
            availability = 0;
        }
        Category category = product.getCategory();
        int idCategory = switch (category.getName()) {
            case "Hygiene" -> 1;
            case "Beverages" -> 2;
            case "Food" -> 3;
            default -> 4;
        };
        double rate = product.getRate();

        PreparedStatement ps = null;

        try {
            ps = this.c.prepareStatement(INSERT_SQL);
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, quantity);
            ps.setInt(4, availability);
            ps.setInt(5, idCategory);
            ps.setDouble(6, rate);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCategory(Category category) {
        final String INSERT_SQL = "INSERT INTO categories (name)" +
                "VALUES (?);";

        String name = category.getName();

        PreparedStatement ps = null;

        try {
            ps = this.c.prepareStatement(INSERT_SQL);
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

