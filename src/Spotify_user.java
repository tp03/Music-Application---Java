import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class Spotify_user {
    private int id;
    private String name;
    private String last_name;
    private String login;
    private String password;
    private String email;

    public Spotify_user(int id) {
        this.id = id;
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
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM app_user WHERE user_id = " + this.id);
            while (resultSet.next()) {

                this.name = resultSet.getString("name");
                this.last_name = resultSet.getString("last_name");
                this.login = resultSet.getString("nick");
                this.email = resultSet.getString("email");
                this.password = resultSet.getString("password");

            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    int getId()
    {
        return this.id;
    }

    String getName()
    {
        return this.name;
    }

    String getLastName()
    {
        return this.last_name;
    }

    String getLogin()
    {
        return this.login;
    }

    String getPassword()
    {
        return this.password;
    }

    String getEmail()
    {
        return this.email;
    }

}
