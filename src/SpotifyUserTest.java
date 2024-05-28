public class SpotifyUserTest {
    public static void main(String[] args) {
        SpotifyUserTest test = new SpotifyUserTest();
        test.runTests();
    }

    public void runTests() {
        testGetId();
        testGetName();
        testGetLastName();
        testGetLogin();
        testGetPassword();
        testGetEmail();
        testCreatePlaylist();
        testAddSongToPlaylist();
        testRemoveSongFromPlaylist();
        testShufflePlaylist();
        testDeletePlaylist();
    }

    public void assertEqual(Object expected, Object actual, String testName) {
        if (expected.equals(actual)) {
            System.out.println(testName + " passed.");
        } else {
            System.out.println(testName + " failed. Expected " + expected + " but got " + actual);
        }
    }

    public void assertTrue(boolean condition, String testName) {
        if (condition) {
            System.out.println(testName + " passed.");
        } else {
            System.out.println(testName + " failed.");
        }
    }

    public void assertNotNull(Object obj, String testName) {
        if (obj != null) {
            System.out.println(testName + " passed.");
        } else {
            System.out.println(testName + " failed. Object is null.");
        }
    }

    public void testGetId() {
        Spotify_user user = new Spotify_user(1);
        assertEqual(1, user.getId(), "testGetId");
    }

    public void testGetName() {
        Spotify_user user = new Spotify_user(1);
        assertNotNull(user.getName(), "testGetName");
    }

    public void testGetLastName() {
        Spotify_user user = new Spotify_user(1);
        assertNotNull(user.getLastName(), "testGetLastName");
    }

    public void testGetLogin() {
        Spotify_user user = new Spotify_user(1);
        assertNotNull(user.getLogin(), "testGetLogin");
    }

    public void testGetPassword() {
        Spotify_user user = new Spotify_user(1);
        assertNotNull(user.getPassword(), "testGetPassword");
    }

    public void testGetEmail() {
        Spotify_user user = new Spotify_user(1);
        assertNotNull(user.getEmail(), "testGetEmail");
    }

    public void testCreatePlaylist() {
        Spotify_user user = new Spotify_user(1);
        user.createPlaylist("Test Playlist");
        ArrayList<Playlist> playlists = user.downloadUserPlaylistsFromServer();
        boolean found = playlists.stream().anyMatch(p -> p.getName().equals("Test Playlist"));
        assertTrue(found, "testCreatePlaylist");
    }

    public void testAddSongToPlaylist() {
        Spotify_user user = new Spotify_user(1);
        Playlist playlist = new Playlist(1);
        Song song = new Song(1, "Test Song");
        user.addSongtoPlaylist(song, playlist);
        assertTrue(playlist.getSongs().contains(song), "testAddSongToPlaylist");
    }

    public void testRemoveSongFromPlaylist() {
        Spotify_user user = new Spotify_user(1);
        Playlist playlist = new Playlist(1);
        Song song = new Song(1, "Test Song");
        user.addSongtoPlaylist(song, playlist);
        user.removeSongFromPlaylist(song, playlist);
        assertTrue(!playlist.getSongs().contains(song), "testRemoveSongFromPlaylist");
    }

    public void testShufflePlaylist() {
        Spotify_user user = new Spotify_user(1);
        Playlist playlist = new Playlist(1);
        playlist.addSong(new Song(1, "Song 1"));
        playlist.addSong(new Song(2, "Song 2"));
        user.shufflePlaylist(playlist);
        assertNotNull(playlist.getSongs(), "testShufflePlaylist");
    }

    public void testDeletePlaylist() {
        Spotify_user user = new Spotify_user(1);
        Playlist playlist = new Playlist(1);
        user.deletePlaylist(playlist);
        assertTrue(!user.downloadUserPlaylistsFromServer().contains(playlist), "testDeletePlaylist");
    }
}
