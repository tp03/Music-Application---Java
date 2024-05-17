import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;

public class App {
    static AppFrame appFrame = new AppFrame();
    static LoginFrame login = new LoginFrame();

    public static void main(String[] args) {

        // UserCreator próba = new UserCreator();
        // próba.created_user(2, "Tomek", "Zalewski", "tp03", "tzal@gmail.com");
<<<<<<< HEAD
        SongImport newsong = new SongImport("/recordings", "/assets");
        newsong.ImportSong("wyszło5", "ADITECHNIK");
        login.setVisible(true);
=======
        Spotify_user su = new Spotify_user(1);
        su.createPlaylist("test123");
>>>>>>> 6d075c7c07bb387e782ad5cd1d6df14e9a435289
        Login_or_Register LoginLogic = new Login_or_Register(login, appFrame,
                login.loginbutton, login.registerbutton);
    }

}
