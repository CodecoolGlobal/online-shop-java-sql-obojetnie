package classes.connectors;

import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(false);
            config.setJournalMode(SQLiteConfig.JournalMode.WAL);
            config.setBusyTimeout(5000);
            config.setTransactionMode(SQLiteConfig.TransactionMode.EXCLUSIVE);
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/shop", config.toProperties());

//            c.setAutoCommit(false);

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


    public Statement getSt() {
        return st;
    }

}
