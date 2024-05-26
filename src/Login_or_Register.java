import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

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

    Spotify_user loggedUser() {
        return this.logged_user;
    }

    void register() {
        Map<String, String> data = loginFrame.collect_register_data();
        List<String> check_result = check_data(data);
        if (check_result.size() != 0) {
            loginFrame.highlight_unfilled_register(check_result);
        } else {
            UserCreator user_creator = new UserCreator();
            user_creator.created_user(data.get("name"), data.get("surname"), data.get("nickname"), data.get("email"),
                    data.get("password"));
            UserReader user_reader = new UserReader(data.get("nickname"), data.get("password"));
            try{
                this.logged_user = user_reader.searchDB();
                loginFrame.dispose();
                this.appFrame.setActiveUser(loggedUser());
                appFrame.setVisible(true);
            }
            catch (Exception e)
            {
                System.out.println("Bad");
            }
        }
    }

    void login() {
        Map<String, String> data = loginFrame.collect_login_data();
        List<String> check_result = check_data(data);
        if (check_result.size() != 0) {
            loginFrame.highlight_unfilled_login(check_result);
        } else {
            UserReader user_reader = new UserReader(data.get("nickname"), data.get("password"));
            try{
                this.logged_user = user_reader.searchDB();
                loginFrame.dispose();
                this.appFrame.setActiveUser(loggedUser());
                appFrame.setVisible(true);
            }
            catch (Exception e)
            {
                System.out.println("Bad");
            }
        }
    }

    List<String> check_data(Map<String, String> map) {
        List<String> unfilled = new ArrayList<>();
        for (String key : map.keySet()) {
            if (map.get(key).isEmpty()) {
                unfilled.add(key);
            }
        }
        return unfilled;
    }
}
