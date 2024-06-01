import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserReader {

    String username;
    String pass;

    public UserReader(String nick, String passw) {
        this.username = nick;
        this.pass = passw;
    }

    Spotify_user searchDB() throws Exception{
        Connection connection = null;
        int id = 0;
        try {
            DatabaseConnection dc = new DatabaseConnection();
            connection = dc.MakeConnection();
            if (connection != null) {
                System.out.println("Successful");
            } else
                System.out.println("Error");
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM app_user WHERE (nick = '" + this.username
                    + "') AND (password = '" + this.pass + "')");
            while (resultSet.next()) {

                id = resultSet.getInt("user_id");
            }
            if (id == 0) {
                
                throw new Exception("user doesn't exists");
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Spotify_user created_user = new Spotify_user(id);
        return created_user;

    }
}
