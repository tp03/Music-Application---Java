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
        System.out.println(data.get("email"));
        loginFrame.dispose();
        appFrame.setVisible(true);
    }

    void login() {
        Map<String, String> data = loginFrame.collect_login_data();
        System.out.println(data.get("password"));
        loginFrame.dispose();
        appFrame.setVisible(true);
    }
}
