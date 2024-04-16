import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class UserCreator {

    private int id;
    private String name;
    private String last_name;
    private String username;
    private String email;
    private String pass;
    
    void created_user(int id, String name, String last_name, String user_name, String email, String passw)
    {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.username = user_name;
        if (email != null)
        {
            this.email = email;
        }
        this.pass = passw;
        Connection connection = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver"); 
            String url = "jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";
            String usern = "tzalews1";
            String password = "tzalews1";
            connection = DriverManager.getConnection(url, usern, password);
            if (connection != null)
            {
                System.out.println("Successful");
            }
            else
                System.out.println("Error");

            String insert_query = "INSERT INTO app_user VALUES ("
            + this.id + ", '" + this.name + "', '" + this.last_name
            + "', '" + this.username + "', '" + this.email + "', '" + this.pass + "')";

            PreparedStatement prepstat = connection.prepareStatement(insert_query);

            int rowsInserted = prepstat.executeUpdate();
            
            if (rowsInserted > 0) {
                System.out.println("G");
            } else {
                System.out.println("Nie G");
            }

            connection.commit();

            connection.close();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }


    
}
