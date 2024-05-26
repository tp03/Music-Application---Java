import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Playlist {

    private int id;
    private ArrayList<Song> songs;
    private String name;

    // stworzenie nowej listy z piosenkami
    public Playlist(int playlist_id) throws SQLException{
        this.songs = new ArrayList<Song>();
        this.id = playlist_id;
        Connection connection = null;
        try {
            DatabaseConnection DC = new DatabaseConnection();
            connection = DC.MakeConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM playlist WHERE playlist_id = " + this.id);
            while (rs.next()) {
                this.name = rs.getString("name");
            }
            ResultSet rs2 = stmt.executeQuery("SELECT name FROM song_and_playlist join song using(song_id) WHERE playlist_id = " + this.id);
            while (rs2.next()){
                Song song_from_playlist = new Song(rs2.getInt("song_id"));
                this.songs.add(song_from_playlist);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }        
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    // gettery do autorow, tytulow, miniaturek, nagran

    public void savePlaylistToFile(String playlist_name) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(playlist_name))) {
            for (Song song : songs) {
                String line = song.getName() + " - " + song.getAuthor();
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Song saved to " + playlist_name);
        } catch (IOException e) {
            System.err.println("Error occurred while saving playlist: " + e.getMessage());
        }
    }

    // należy stworzyć gettery

    private String formatSong(Song song) {
        return song.getName() + " - " + song.getAuthor();
    }

    public static void main(String[] args) {

        // Przykładowe użycie zapisywania do pliku

        // Playlist myPlaylist = new Playlist();
        // Song song1 = new Song("Pila tango", "Bajm", "image1.jpg", "recording1.mp3");
        // Song song2 = new Song("Song", "Author", "image2.jpg", "recording2.mp3");
        // myPlaylist.addSong(song1);
        // myPlaylist.addSong(song2);
        // myPlaylist.savePlaylistToFile("playlist.txt");
    }
}
