package classes.readers;

import java.sql.*;

public class SqlConnector {

    private Connection c;
    private Statement st;

    public SqlConnector() {
        c = null;
        st = null;
    }

    public void connectToDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/home/szniemiec/IdeaProjects/OnlineShopJavaSQL/src/main/resources/shop");

            c.setAutoCommit(false);

            st = c.createStatement();

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void disconnectFromDatabase() throws SQLException {
        st.close();
        c.close();
    }

    public Connection getC() {
        return c;
    }

    public void setC(Connection c) {
        this.c = c;
    }

    public Statement getSt() {
        return st;
    }

    public void setSt(Statement st) {
        this.st = st;
    }
}
