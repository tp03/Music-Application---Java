import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;

public class App {
    static AppFrame appFrame = new AppFrame();
    static LoginFrame login = new LoginFrame();

    public static void main(String[] args) {

        // UserCreator próba = new UserCreator();
        // próba.created_user(2, "Tomek", "Zalewski", "tp03", "tzal@gmail.com");

        login.setVisible(true);
        Login_or_Register LoginLogic = new Login_or_Register(login, appFrame, login.loginbutton, login.registerbutton);

    }
}
