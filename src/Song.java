import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;

public class Song {

    private int id;
    private String name;
    private int author_id;
    private String author_name;
    private int length;
    private int views;
    private String imagePath;
    private String recordingPath;
    private String lyricsPath;
    private JLabel viewsLabel = new JLabel(" Views: " + 0);

    public Song(int id) {
        this.id = id;
        Connection connection = null;
        try {
            DatabaseConnection DC = new DatabaseConnection();
            connection = DC.MakeConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM (SONG s join SONG_DATA sd on(sd.song_data_id = s.DATA_ID)) join AUTHOR_SONGS aus using(SONG_ID) WHERE song_id = "
                            + this.id);
            while (rs.next()) {
                this.name = rs.getString("name");
                this.length = rs.getInt("length");
                this.views = rs.getInt("views");
                this.author_id = rs.getInt("author_id");
                this.imagePath = rs.getString("picture");
                this.lyricsPath = rs.getString("lyrics");
                this.recordingPath = rs.getString("music");
            }
            ResultSet rs2 = stmt.executeQuery("SELECT name FROM author WHERE author_id = " + this.author_id);
            while (rs2.next()) {
                this.author_name = rs2.getString("name");
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void wasClicked() {
        this.views++;
        Connection connection = null;
        try {
            DatabaseConnection DC = new DatabaseConnection();
            connection = DC.MakeConnection();
            String insert_query = "update song set views = " + this.views + " where song_id = " + this.id;
            PreparedStatement prepstat = connection.prepareStatement(insert_query);
            prepstat.executeQuery();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public JLabel getViewsLabel() {
        return this.viewsLabel;
    }

    public void setViewsLabel(JLabel newLabel) {
        this.viewsLabel = newLabel;
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
