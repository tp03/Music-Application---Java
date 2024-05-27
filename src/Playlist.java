import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Playlist {

    private int id;
    private ArrayList<Song> songs = new ArrayList<>();
    private String name;

    // stworzenie nowej listy z piosenkami
    public Playlist(int playlist_id) {
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
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM song_and_playlist join song using(song_id) WHERE playlist_id = " + this.id);
            while (rs2.next()){
                Song song_from_playlist = new Song(rs2.getInt("song_id"));
                this.songs.add(song_from_playlist);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }        
    }

    public void downloadSongsFromServer()
    {
        Connection connection = null;
        try {
            DatabaseConnection DC = new DatabaseConnection();
            connection = DC.MakeConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM song_and_playlist join song using(song_id) WHERE playlist_id = " + this.id);
            while (rs.next()) {
                int song_id = rs.getInt("song_id");
                Song new_song = new Song(song_id);
                this.songs.add(new_song);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }  
    }

    public void addSong(Song song) {
        this.songs.add(song);
        int song_id = song.getID();
        Connection connection = null;
        try {
            DatabaseConnection DC = new DatabaseConnection();
            connection = DC.MakeConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM song_and_playlist");
            int new_sap_id = 0;
            while (rs.next()) {
                new_sap_id = rs.getInt("COUNT(*)") + 1;
            }
            String insert_query = "INSERT INTO song_and_playlist VALUES (" + new_sap_id + ", " + this.id + ", " + song_id + ")";
            PreparedStatement prepstat = connection.prepareStatement(insert_query);
            prepstat.executeQuery();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeSong(Song song) {
        this.songs.remove(song);
        int song_id = song.getID();
        Connection connection = null;
        try {
            DatabaseConnection DC = new DatabaseConnection();
            connection = DC.MakeConnection();
            String insert_query = "DELETE FROM song_and_playlist WHERE playlist_id = " + this.id + " and song_id = " + song_id;
            PreparedStatement prepstat = connection.prepareStatement(insert_query);
            prepstat.executeQuery();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }     
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

    public void Shuffle() {
        Collections.shuffle(this.songs);        
    }
    // należy stworzyć gettery

    private String formatSong(Song song) {
        return song.getName() + " - " + song.getAuthor();
    }

    public String getName()
    {
        return this.name;
    }

    public int getId()
    {
        return this.id;
    }
}
