import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

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
            DatabaseConnection dc = new DatabaseConnection();
            connection = dc.MakeConnection();
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

    void createPlaylist(String playlist_name)
    {
        Connection connection = null;
        try {
            DatabaseConnection dc = new DatabaseConnection();
            connection = dc.MakeConnection();
            String in_query = "SELECT COUNT(*) FROM playlist";          
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(in_query);
            int new_id = 0;
            while (resultSet.next()) {

                new_id = resultSet.getInt("COUNT(*)") + 1;
            }
            System.out.println(new_id);
            String insert_query = "INSERT INTO PLAYLIST VALUES (" + new_id + ", '" + playlist_name + "')";
            PreparedStatement prepstat = connection.prepareStatement(insert_query);
            String query = "SELECT COUNT(*) FROM user_playlist";          
            ResultSet rs = stmt.executeQuery(query);
            int new_up_id = 0;
            while (rs.next()) {

                new_up_id = rs.getInt("COUNT(*)") + 1;
            }
            String insert_query2 = "INSERT INTO USER_PLAYLIST VALUES (" + new_up_id + ", " + this.id + ", " + new_id + ")";
            PreparedStatement ps = connection.prepareStatement(insert_query2);
            stmt.close();            
        } catch (SQLException e) {
            e.printStackTrace();
        }        
    }
}
