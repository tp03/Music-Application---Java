import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DatabaseConnection {
    Connection MakeConnection() {
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";
            String usern = "tzalews1";
            String password = "tzalews1";
            connection = DriverManager.getConnection(url, usern, password);
            if (connection != null) {
                System.out.println("Successful");
            } else
                System.out.println("Error");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
