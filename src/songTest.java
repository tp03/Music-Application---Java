import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class songTest {

    @Test
    public void testSongName() {

        Song song_test = new Song(3);

        assertTrue(song_test.getName().equals("Moja Gwiazda"));  
    }    

    @Test
    public void testSongAuthorName() {

        Song song_test = new Song(3);

        assertTrue(song_test.getAuthor().equals("Zenon Martyniuk"));  
    }   

    @Test
    public void testAuthorID() {

        Song song_test = new Song(3);

        assertTrue(song_test.getAuthorInt() == 2);  
    }  
    
    @Test
    public void testLength() {

        Song song_test = new Song(3);

        assertTrue(song_test.getLength() == 0);  
    }   

    @Test
    public void testSongLyrics() {

        Song song_test = new Song(3);

        assertTrue(song_test.getLyricsPath().equals("lyrics/moja_gwiazda.txt"));  
    }  
    
    @Test
    public void testSongPath() {

        Song song_test = new Song(3);

        assertTrue(song_test.getRecordingPath().equals("recordings/gwiazda.mp3"));  
    }   

    @Test
    public void testSongPhoto() {

        Song song_test = new Song(3);

        assertTrue(song_test.getImagePath().equals("assets/zenek3.jpeg"));  
    } 

    @Test
    public void testViews() {

        Song song_test = new Song(3);

        int views_before = song_test.getViews();

        song_test.wasClicked();

        int views_after = song_test.getViews();

        assertTrue(views_after == views_before+1);
    }

    @Test
    public void testViews2() {

        Song song_test = new Song(3);

        int views_before = song_test.getViews();

        song_test.wasClicked();

        Connection connection = null;
        try {

            int views_after = 0;

            DatabaseConnection DC = new DatabaseConnection();
            connection = DC.MakeConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT views from song where song_id = 3");
            while (rs.next()) {
                views_after = rs.getInt("views");
            }

            assertTrue(views_before+1 == views_after);
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
    }
}
