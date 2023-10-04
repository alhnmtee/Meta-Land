package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect {
   // private static final String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String databaseUrl = "jdbc:sqlserver://localhost:1433;databaseName=meta_land;encrypt=true;trustServerCertificate=true;";
    private static final String userN = "sa";
    private static final String password = "1";

    private Connection connection;

    public Connection getConnection() {
        if (connection == null) {
            try {
              //  Class.forName(jdbcDriver);
                connection = DriverManager.getConnection(databaseUrl, userN, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
