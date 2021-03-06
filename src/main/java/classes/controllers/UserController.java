package classes.controllers;

import classes.connectors.SqlConnector;
import classes.controllers.exceptions.IdRoleException;
import classes.enums.Role;
import classes.users.Admin;
import classes.users.Customer;
import classes.users.User;

import java.sql.*;

public class UserController {

    private Connection c;
    private Statement st;

    public UserController(SqlConnector sqlConnector) {
        this.c = sqlConnector.getC();
        this.st = sqlConnector.getSt();
    }

    public User getUserFromDatabaseById(int id) throws Exception {
        String SELECT_SQL = "SELECT * FROM users WHERE id = '" + id + "';";
        try {
            ResultSet rs = st.executeQuery(SELECT_SQL);
            while (rs.next()) {
                String login = rs.getString("Login");
                String password = rs.getString("Password");
                String email = rs.getString("Email");
                int idRole = (rs.getInt("IdRole"));
                switch (idRole) {
                    case 1 -> {
                        return new Admin(id, login, password, email, Role.ADMIN);
                    }
                    case 2 -> {
                        return new Customer(id, login, password, email, Role.CUSTOMER);
                    }
                    default -> throw new IdRoleException("No matching role id");
                }
            }
        } catch (SQLException | IdRoleException e) {
            e.printStackTrace();
        }
        return null;
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

    public boolean getIsLoginTaken(String login) {
        final String SELECT_SQL = "SELECT login FROM users;";

        try {
            ResultSet rs = st.executeQuery(SELECT_SQL);
            while (rs.next()) {
                if (rs.getString("Login").toLowerCase().equals(login.toLowerCase())) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean getIsEmailTaken(String email) {
        final String SELECT_SQL = "SELECT email FROM users WHERE email = '" + email + "';";

        try {
            ResultSet rs = st.executeQuery(SELECT_SQL);
            while (rs.next()) {
                if (rs.getString("Email").equals(email)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean getIsPasswordMatching(String login, String password) {
        final String SELECT_SQL = "SELECT password FROM users WHERE login = '" + login + "';";

        try {
            ResultSet rs = st.executeQuery(SELECT_SQL);
            while (rs.next()) {
                if (rs.getString("Password").equals(password)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getIdRole(String login) {
        final String SELECT_SQL = "SELECT IdRole FROM users WHERE login = '" + login + "';";

        int idRole = 0;

        try {
            ResultSet rs = st.executeQuery(SELECT_SQL);
            while (rs.next()) {
                idRole = rs.getInt("IdRole");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idRole;
    }

    public User loginUser(String login, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE login = '" + login + "' AND password = '" + password + "';";

        try {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("Id");
                String email = rs.getString("Email");
                int roleId = rs.getInt("IdRole");
                switch (roleId) {
                    case 1 -> {
                        return new Admin(id, login, password, email, Role.ADMIN);
                    }
                    case 2 -> {
                        return new Customer(id, login, password, email, Role.CUSTOMER);
                    }
                    default -> throw new IdRoleException("No matching role id");
                }
            }
        } catch (Exception | IdRoleException e) {
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
