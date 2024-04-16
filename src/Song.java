import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Song {

    private int id;
    private String name;
    private int author_id;
    private String author_name;
    private int length;
    private int views;
    private int data;
    private String imagePath;
    private String recordingPath;
    private String lyricsPath;

    public Song(int id) {
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
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM song WHERE song_id = " + this.id);
            while (resultSet.next()) {

                this.name = resultSet.getString("name");
                this.length = resultSet.getInt("length");
                this.views = resultSet.getInt("views");
                this.data = resultSet.getInt("data_id");

            }
            ResultSet rs2 = stmt.executeQuery("SELECT author_id FROM author_songs WHERE song_id = " + this.id);
            while (rs2.next()) {
                this.author_id = rs2.getInt("author_id");
            }
            ResultSet rs3 = stmt.executeQuery("SELECT * FROM song_data WHERE song_data_id = " + this.data);
            while (rs3.next()) {
                this.imagePath = rs3.getString("picture");
                this.lyricsPath = rs3.getString("lyrics");
                this.recordingPath = rs3.getString("music");
            }
            ResultSet rs4 = stmt.executeQuery("SELECT name FROM author WHERE author_id = " + this.author_id);
            while (rs4.next()) {
                this.author_name = rs4.getString("name");
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public int getAuthorInt() {
        return author_id;
    }

    public String getAuthor() {
        return author_name;
    }

    public int getViews() {
        return views;
    }

    public int getLength() {
        return length;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getRecordingPath() {
        return recordingPath;
    }

    public String getLyricsPath() {
        return lyricsPath;
    }
}
