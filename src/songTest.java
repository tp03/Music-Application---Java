import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;


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

        assertTrue(song_test.getLength() == 100);  
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
}
