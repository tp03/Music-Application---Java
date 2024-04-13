import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class App {
    static AppFrame appFrame = new AppFrame();
    static LoginFrame login = new LoginFrame();

    public static void main(String[] args) {

        login.setVisible(true);
        switch_frames();

    }

    static void switch_frames() {
        login.registerbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login.dispose();
                appFrame.setVisible(true);
            }
        });
    }

}
