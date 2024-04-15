import java.util.Optional;

public class Spotify_user {
    private int id;
    public String name;
    private String login;
    private String password;
    private String email;

    Spotify_user(int id, String name, String login, String password, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
    }
}
