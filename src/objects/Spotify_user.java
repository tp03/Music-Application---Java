package objects;

import java.util.Optional;

public class Spotify_user {
    private int id;
    private String name;
    private String last_name;
    private String username;
    private Optional<String> email;

    Spotify_user(int id, String name, String last_name, String username, Optional<String> email) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.username = username;
        this.email = email;
    }
}
