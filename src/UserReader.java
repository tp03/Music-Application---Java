import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//jakaś klasa użytkownik kurwa ten?

public class UserReader {

    String password;

    public UserReader(String password) {
        this.password = password;
    }

    Spotify_user searchDB() {
        Connection connection = null;
        int id = 0;
        String name = "";
        String login = "";
        String mail = "";
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
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM app_user WHERE nick = " + this.password);
            while (resultSet.next()) {

                id = resultSet.getInt("user_id");
                name = resultSet.getString("name");
                login = resultSet.getString("login");
                if (resultSet.getString("email") != null) {
                    mail = resultSet.getString("email");
                } else {
                    mail = "empty";
                }
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
        Spotify_user created_user = new Spotify_user(id, name, login, this.password, mail);
        return created_user;

    }
}
