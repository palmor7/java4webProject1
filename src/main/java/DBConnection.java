import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/car_insurance?useUnicode=true" + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String DB_USER;
    private String DB_PASSWORD;
    private static Connection connection;

    protected Connection getDBConnection() throws Exception {
        Class.forName(DB_DRIVER);
        return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
    }

    protected void closeDBConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    protected void setDB_USER(String DB_USER) {
        this.DB_USER = DB_USER;
    }

    protected void setDB_PASSWORD(String DB_PASSWORD) {
        this.DB_PASSWORD = DB_PASSWORD;
    }
}

