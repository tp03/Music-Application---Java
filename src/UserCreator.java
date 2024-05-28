import java.sql.Connection;
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
    
    void created_user(String name, String last_name, String user_name, String email, String passw)
    {
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
            DatabaseConnection dc = new DatabaseConnection();
            connection = dc.MakeConnection();
            if (connection != null)
            {
                System.out.println("Successful");
            }
            else
                System.out.println("Error");

            String in_query2 = "SELECT MAX(user_id) FROM app_user";
            
            Statement stmt = connection.createStatement();

            ResultSet resultSet = stmt.executeQuery(in_query2);

            while (resultSet.next()) {

                this.id = resultSet.getInt("MAX(user_id)") + 1;
            }
            
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

            connection.close();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    
}
