import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class Login_or_Register {

    private LoginFrame loginFrame;
    private AppFrame appFrame;
    private Spotify_user logged_user;

    Login_or_Register(LoginFrame loginFrame, AppFrame AppFrame, JButton loginbutton, JButton registerbutton) {
        this.appFrame = AppFrame;
        this.loginFrame = loginFrame;
        add_login_on_click(loginbutton);
        add_register_on_click(registerbutton);

    }

    void add_register_on_click(JButton registerbutton) {
        registerbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });
    }

    void add_login_on_click(JButton loginbutton) {
        loginbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }

    void register() {
        Map<String, String> data = loginFrame.collect_register_data();
        UserCreator user_creator = new UserCreator();
        user_creator.created_user(data.get("name"), data.get("surname"), data.get("nickname"), data.get("email"), data.get("password"));
        UserReader user_reader = new UserReader(data.get("nickname"), data.get("password"));
        this.logged_user = user_reader.searchDB();
        loginFrame.dispose();
        appFrame.setVisible(true);
    }

    void login() {
        Map<String, String> data = loginFrame.collect_login_data();
        UserReader user_reader = new UserReader(data.get("nickname"), data.get("password"));
        this.logged_user = user_reader.searchDB();
        loginFrame.dispose();
        appFrame.setVisible(true);
    }
}
