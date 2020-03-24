package classes.controllers;

import classes.connectors.SqlConnector;
import classes.categories.Category;

import java.sql.*;

public class CategoryController {

    private Connection c;
    private Statement st;

    public CategoryController(SqlConnector sqlConnector) {
        this.c = sqlConnector.getC();
        this.st = sqlConnector.getSt();
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

    public void editCategoryName(Category category, String newName) {
        String nameOfCategoryInDatabase = category.getName();

        final String UPDATE_SQL = "UPDATE categories " +
                "SET name = ?" +
                "WHERE name = ?;";

        PreparedStatement ps = null;

        try {
            ps = this.c.prepareStatement(UPDATE_SQL);
            ps.setString(1, newName);
            ps.setString(2, nameOfCategoryInDatabase);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getIsCategoryInDatabase(Category category) {
        String name = category.getName();

        final String SELECT_SQL = "SELECT name FROM categories WHERE name = '" + name + "';";

        try {
            ResultSet rs = st.executeQuery(SELECT_SQL);
            while (rs.next()){
                if (rs.getString("Name").equals(name)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteCategory(int id) {
        final String DELETE_SQL = "DELETE FROM categories WHERE id = ?;";

        PreparedStatement ps = null;

        try {
            ps = this.c.prepareStatement(DELETE_SQL);
            ps.setInt(1, id);
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
