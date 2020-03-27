package classes.controllers;

import classes.connectors.SqlConnector;
import classes.categories.Category;
import classes.menus.exceptions.AvailabilityException;
import classes.models.Basket;
import classes.models.Order;
import classes.models.Product;

import java.sql.*;
import java.util.Map;

public class ProductController {

    private Connection c;
    private Statement st;

    public ProductController(SqlConnector sqlConnector) {
        this.c = sqlConnector.getC();
        this.st = sqlConnector.getSt();
    }

    public void viewProductsTable() throws SQLException {
        String SELECT_SQL = "SELECT * FROM products;";
        try {
            ResultSet rs = st.executeQuery(SELECT_SQL);
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                double price = rs.getDouble("Price");
                int quantity = rs.getInt("Quantity");
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

    public void viewAllProductsFromCategory(int categoryId) {
        String SELECT_SQL = "SELECT * FROM products WHERE idcategory = '" +  categoryId + "';";

        try {
            ResultSet rs = st.executeQuery(SELECT_SQL);
            while(rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                double price = rs.getDouble("Price");
                int quantity = rs.getInt("Quantity");
                int availability = rs.getInt("Availability");
                int idCategory = rs.getInt("idCategory");
                double rate = rs.getDouble("Rate");

                String format = "|%1$-3s|%2$-18s|%3$-16s|%4$-30s|%5$-9s|%6$-20s|%7$-10s|\n";
                System.out.printf(format, id, name, price, quantity, availability, idCategory, rate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product product) throws SQLException {
        final String INSERT_SQL = "INSERT INTO products (name, price, quantity, availability, idCategory, rate)" +
                "VALUES (?, ?, ?, ?, ?, ?);";
        if (c.isClosed()) {
            System.out.println("Connection still open ... ");
        }
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

        boolean isProductInDatabase = getIsProductInDatabase(product);

        if (isProductInDatabase) {
            System.out.println("Product already in database. Updating quantity");
            addQuantity(product, product.getQuantity());
        } else {
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
    }
    public boolean getIsProductInDatabase(Product product) {
        String name = product.getName();

        final String SELECT_SQL = "SELECT name FROM products WHERE name = '" + name + "';";

        try {
            ResultSet rs = st.executeQuery(SELECT_SQL);
            while (rs.next()) {
                if (rs.getString("Name").equals(name)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public void editProductName(Product product, String newName) {
        String nameOfProductInDatabase = product.getName();

        final String UPDATE_SQL = "UPDATE products " +
                "SET name = ? " +
                "WHERE name = ?;";

        PreparedStatement ps = null;

        try {
            ps = this.c.prepareStatement(UPDATE_SQL);
            ps.setString(1, newName);
            ps.setString(2, nameOfProductInDatabase);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editProductPrice(Product product, double price) {
        String nameOfProductInDatabase = product.getName();

        final String UPDATE_SQL = "UPDATE products " +
                "SET price = ? " +
                " WHERE name = ?;";

        PreparedStatement ps = null;

        try {
            ps = this.c.prepareStatement(UPDATE_SQL);
            ps.setDouble(1, price);
            ps.setString(2, nameOfProductInDatabase);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editProductQuantity(Product product, int quantity) {
        String nameOfProductInDatabase = product.getName();

        final String UPDATE_SQL = "UPDATE products " +
                "SET quantity = ? " +
                "WHERE name = ?;";

        PreparedStatement ps = null;

        try {
            ps = this.c.prepareStatement(UPDATE_SQL);
            ps.setInt(1, quantity);
            ps.setString(2, nameOfProductInDatabase);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editProductCategory(Product product, int idCategory) {
        String nameOfProductInDatabase = product.getName();

        final String UPDATE_SQL = "UPDATE products " +
                "SET idCategory = ? " +
                "WHERE name = ?;";

        PreparedStatement ps = null;

        try {
            ps = this.c.prepareStatement(UPDATE_SQL);
            ps.setInt(1, idCategory);
            ps.setString(2, nameOfProductInDatabase);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product getProductFromDatabaseById(int id) {
        final String SELECT_SQL = "SELECT * FROM products WHERE id = '" + id + "';";
        try {
            ResultSet rs = st.executeQuery(SELECT_SQL);
            while (rs.next()) {
                String name = rs.getString("Name");
                double price = rs.getDouble("Price");
                int quantity = rs.getInt("Quantity");
                int idCategory = rs.getInt("idCategory");
                Category category = switch (idCategory) {
                    case 1 -> new Category(1, "Hygiene");
                    case 2 -> new Category(2, "Beverages");
                    case 3 -> new Category(3, "Food");
                    default -> throw new IllegalStateException("Unexpected value: " + idCategory);
                };

                return new Product(id, name, price, quantity, category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addQuantity(Product product, int value) {
        int id = product.getId();
        int quantityOfProductInDatabase = product.getQuantity();
        final String UPDATE_SQL = "UPDATE products " +
                "SET quantity = ? " +
                " WHERE id = ?;";

        PreparedStatement ps = null;

        try {
            ps = this.c.prepareStatement(UPDATE_SQL);
            ps.setInt(1, quantityOfProductInDatabase + value);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void decreaseQuantity(Product product, int value) {
        int id = product.getId();
        int quantityOfProductInDatabase = product.getQuantity();
        final String UPDATE_SQL = "UPDATE products " +
                "SET quantity = ? " +
                " WHERE id = ?;";

        PreparedStatement ps = null;

        try {
            ps = this.c.prepareStatement(UPDATE_SQL);
            ps.setInt(1, quantityOfProductInDatabase - value);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void autoDecreaseQuantityBasedOnBasket(Basket basket) {
        Map<Product, Integer> basketMap = basket.getBasketMap();

        for (Product product : basketMap.keySet()) {
            int quantity = basketMap.get(product);
            decreaseQuantity(product, quantity);
        }
    }

    public void updateAvailability(Product product, int value) {
        String name = product.getName();
        final String UPDATE_SQL = "UPDATE products " +
                "SET availability = ? " +
                "WHERE name = ?;";

        PreparedStatement ps = null;

        try {
            ps = this.c.prepareStatement(UPDATE_SQL);
            ps.setInt(1, value);
            ps.setString(2, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        final String DELETE_SQL = "DELETE FROM products WHERE id = ?;";

        PreparedStatement ps = null;

        try {
            ps = this.c.prepareStatement(DELETE_SQL);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkAvailability(int id) {
        final String SELECT_SQL = "SELECT availability FROM products WHERE id = '" + id + "';";
        boolean isAvailable = false;
        try {
            ResultSet rs = st.executeQuery(SELECT_SQL);
            isAvailable = switch (rs.getInt("availability")) {
                case 1 -> true;
                case 0 -> false;
                default -> throw new AvailabilityException("Wrong idAvailability");
            };
        } catch (Exception | AvailabilityException e) {
            e.printStackTrace();
        }
        return isAvailable;
    }


    public Connection getC() {
        return c;
    }

    public Statement getSt() {
        return st;
    }

}
