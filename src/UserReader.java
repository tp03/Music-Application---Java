import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//jakaś klasa użytkownik kurwa ten?

public class UserReader {

    String username;
    String pass;

    public UserReader(String nick, String passw) {
        this.username = nick;
        this.pass = passw;
    }

    Spotify_user searchDB() {
        Connection connection = null;
        int id = 0;
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
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM app_user WHERE (nick = " + this.username
                    + ") AND (password = " + this.pass + ")");
            while (resultSet.next()) {

                id = resultSet.getInt("user_id");
            }
            if (!resultSet.next()) {
                // co sie dzieje gdy wpiszaemy nieistniejący nick
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Spotify_user created_user = new Spotify_user(id);
        return created_user;

    }
}
