import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SearchEngine {

    private String ask;
    private ArrayList<Song> returned_songs;

    void make_song_search(String parsed_search)
    {
        this.ask = parsed_search;
        this.returned_songs = new ArrayList<Song>();
        Connection connection = null;
        try{
            DatabaseConnection dc = new DatabaseConnection();
            connection = dc.MakeConnection();
            if (connection != null)
            {
                System.out.println("Successful");
            }
            else
                System.out.println("Error");
            
            String search_query = "SELECT * FROM song WHERE upper(name) like '%" + this.ask.toUpperCase() + "%'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(search_query);
            while(rs.next())
            {
                int id = rs.getInt("song_id");
                Song searched_song = new Song(id);
                this.returned_songs.add(searched_song);
            }
            stmt.close();
            connection.close();

            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
    }
    
    void make_author_search(String parsed_search)
    {
        this.ask = parsed_search;
        this.returned_songs = new ArrayList<Song>();
        Connection connection = null;
        try{
            DatabaseConnection dc = new DatabaseConnection();
            connection = dc.MakeConnection();
            if (connection != null)
            {
                System.out.println("Successful");
            }
            else
                System.out.println("Error");
            
            String search_query = "SELECT * FROM (SONG s join AUTHOR_SONGS aus using(song_id)) join AUTHOR au using(author_id) WHERE au.name like '%" + this.ask.toUpperCase() + "%'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(search_query);
            while(rs.next())
            {
                int id = rs.getInt("song_id");
                Song searched_song = new Song(id);
                this.returned_songs.add(searched_song);
            }
            stmt.close();
            connection.close();

            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }


    }

    ArrayList<Song> get_searched_songs()
    {
        return this.returned_songs;
    }  
}
