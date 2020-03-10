package classes.controllers;

import classes.enums.Role;
import classes.readers.SqlConnector;
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

                String format = "|%1$-3s|%2$-12s|%3$-16s|%4$-30s|%5$-9s|\n";
                System.out.printf(format, id, login, password, email, role);

            }
        } catch (Exception e) {
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

}
