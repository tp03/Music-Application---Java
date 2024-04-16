import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Playlist {
    private ArrayList<Song> songs;


    // stworzenie nowej listy z piosenkami
    public Playlist() {     
        this.songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    //gettery do autorow, tytulow, miniaturek, nagran

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

        Playlist myPlaylist = new Playlist();
        Song song1 = new Song("Pila tango", "Bajm", "image1.jpg", "recording1.mp3");
        Song song2 = new Song("Song", "Author", "image2.jpg", "recording2.mp3");
        myPlaylist.addSong(song1);
        myPlaylist.addSong(song2);
        myPlaylist.savePlaylistToFile("playlist.txt");
    }
}
