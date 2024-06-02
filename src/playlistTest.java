import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;

public class playlistTest {

    @Test
    public void createTest() {

        Spotify_user user = new Spotify_user(1);

        try{
            Playlist created_playlist = user.createPlaylist("tested_playlist");

            assertTrue(created_playlist.getName().equals("tested_playlist"));

            user.deletePlaylist(created_playlist);
        }
        catch (Exception e)
        {}
    }

    @Test
    public void createTest2() {

        Spotify_user user = new Spotify_user(4);

        try{

            Playlist created_playlist = user.createPlaylist("tested_playlist");

            ArrayList<Playlist> playlists = user.getPlaylists();

            int size = playlists.size();

            user.deletePlaylist(created_playlist);

            assertTrue(size == 1);
        }
        catch (Exception e)
        {}
    }

    @Test
    public void addSongTest() {

        Spotify_user user = new Spotify_user(4);

        try{

            Playlist created_playlist = user.createPlaylist("tested_playlist");

            Song song = new Song(3);

            created_playlist.addSong(song);

            int size = created_playlist.getSongs().size();

            user.deletePlaylist(created_playlist);

            assertTrue(size == 1);
        }
        catch (Exception e)
        {}
    }

    @Test
    public void addSongTest2() {

        Spotify_user user = new Spotify_user(4);

        try{

            Playlist created_playlist = user.createPlaylist("tested_playlist");

            Song song = new Song(3);

            user.addSongtoPlaylist(song, created_playlist);

            int size = created_playlist.getSongs().size();

            user.deletePlaylist(created_playlist);

            assertTrue(size == 1);
        }
        catch (Exception e)
        {}

    }

    @Test
    public void removeSong() {

        Spotify_user user = new Spotify_user(4);

        try{

            Playlist created_playlist = user.createPlaylist("tested_playlist");

            Song song = new Song(3);

            user.addSongtoPlaylist(song, created_playlist);

            user.removeSongFromPlaylist(song, created_playlist);

            assertTrue(created_playlist.getSongs().size() == 0);
        }
        catch (Exception e)
        {}

    }

    @Test
    public void removeSong2() {

        Spotify_user user = new Spotify_user(4);

        try{
            Playlist created_playlist = user.createPlaylist("tested_playlist");

            Song song = new Song(3);

            user.addSongtoPlaylist(song, created_playlist);

            created_playlist.removeSong(song);

            assertTrue(created_playlist.getSongs().size() == 0);
        }
        catch (Exception e)
        {}

    }
    
    @Test
    public void usedNameErrorTest() {

        Spotify_user user = new Spotify_user(4);

        try{
            Playlist created_playlist = user.createPlaylist("tested_playlist");

            Exception exception = assertThrows(Exception.class, () -> user.createPlaylist("tested_playlist"));

            assertEquals(exception.getMessage(), "Playlist name already taken");

            
        }
        catch (Exception e)
        {}

    }

    @Test
    public void usedNameErrorTest2() {

        Spotify_user user = new Spotify_user(2);
        Spotify_user user2 = new Spotify_user(3);

        try{
            Playlist cr_playlist = user.createPlaylist("abc");
            Playlist cr_playlist2 = user2.createPlaylist("abc");

            String str1 = cr_playlist.getName();
            String str2 = cr_playlist2.getName();

            user.deletePlaylist(cr_playlist);
            user2.deletePlaylist(cr_playlist2);

            assertEquals(str1, str2);
        }
        catch (Exception e)
        {}


    }
}
