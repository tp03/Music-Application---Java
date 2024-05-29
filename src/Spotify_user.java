import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

public class Spotify_user {
    private int id;
    private String name;
    private String last_name;
    private String login;
    private String password;
    private String email;
    private ArrayList<Playlist> user_playlists = new ArrayList<>();

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

    public ArrayList<Playlist> getPlaylists()
    {
        downloadUserPlaylistsFromServer();
        return this.user_playlists;
    }

    Playlist createPlaylist(String playlist_name)

    {
        Connection connection = null;
        int new_id = 0;
        try {
            DatabaseConnection dc = new DatabaseConnection();
            connection = dc.MakeConnection();
            String in_query = "SELECT MAX(playlist_id) FROM playlist";          
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(in_query);
            while (resultSet.next()) {

                new_id = resultSet.getInt("MAX(playlist_id)") + 1;
            }
            String insert_query = "INSERT INTO PLAYLIST VALUES (" + new_id + ", '" + playlist_name + "')";
            PreparedStatement prepstat = connection.prepareStatement(insert_query);
            prepstat.executeQuery();
            String query = "SELECT MAX(user_playlist_id) FROM user_playlist";          
            ResultSet rs = stmt.executeQuery(query);
            int new_up_id = 0;
            while (rs.next()) {

                new_up_id = rs.getInt("MAX(user_playlist_id)") + 1;
            }
            String insert_query2 = "INSERT INTO USER_PLAYLIST VALUES (" + new_up_id + ", " + this.id + ", " + new_id + ")";
            PreparedStatement ps = connection.prepareStatement(insert_query2);
            ps.executeQuery();
            stmt.close();            
        } catch (SQLException e) {
            e.printStackTrace();
        }    
        Playlist playlist = new Playlist(new_id);
        return playlist;
    
    }

    ArrayList<Playlist> downloadUserPlaylistsFromServer()
    {
        Connection connection = null;
        try {
            DatabaseConnection DC = new DatabaseConnection();
            connection = DC.MakeConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user_playlist WHERE user_id = " + this.id);
            while (rs.next()) {
                int p_id = rs.getInt("playlist_id");
                Playlist new_playlist = new Playlist(p_id);
                this.user_playlists.add(new_playlist);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.user_playlists;       
    }

    void addSongtoPlaylist(Song song, Playlist playlist) {
        playlist.addSong(song);
    }

    void removeSongFromPlaylist(Song song, Playlist playlist) {
        playlist.removeSong(song);
    }

    void shufflePlaylist(Playlist playlist) {
        playlist.Shuffle();
    }

    void deletePlaylist(Playlist playlist) {
        this.user_playlists.remove(playlist);
        int playlist_id = playlist.getId();
        Connection connection = null;
        try {
            DatabaseConnection DC = new DatabaseConnection();
            connection = DC.MakeConnection();
            String delete_query1 = "DELETE FROM song_and_playlist WHERE playlist_id = " + playlist_id;
            PreparedStatement ptmt = connection.prepareStatement(delete_query1);
            ptmt.executeQuery();
            String delete_query2 = "DELETE FROM user_playlist WHERE playlist_id = " + playlist_id;
            PreparedStatement ptmt2 = connection.prepareStatement(delete_query2);
            ptmt2.executeQuery();
            String delete_query3 = "DELETE FROM playlist WHERE playlist_id = " + playlist_id;
            PreparedStatement ptmt3 = connection.prepareStatement(delete_query3);
            ptmt3.executeQuery();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
