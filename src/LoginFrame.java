import javax.swing.*;

import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

public class LoginFrame extends JFrame {// inheriting JFrame
    CardLayout cardLayout = new CardLayout();
    JPanel mainPanel = new JPanel();
    private JPanel loginPanel;
    private JPanel registerPanel;
    private JButton loginbutton;
    public JButton registerbutton;

    LoginFrame() {
        this.setSize(400, 300);

        this.loginPanel = create_login_panel();
        this.registerPanel = create_register_panel();

        mainPanel.setLayout(cardLayout);
        this.add(mainPanel);
        mainPanel.add(loginPanel, "login");
        mainPanel.add(registerPanel, "register");
        cardLayout.show(mainPanel, "login");
        this.setTitle("LoginPanel");
        this.add(mainPanel);
        // this.setVisible(true);
    }

    JPanel create_login_panel() {
        JPanel login_panel = new JPanel();
        login_panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        login_panel.setLayout(new GridBagLayout());
        center_elements(login_panel);

        login_panel.setSize(400, 500);
        switch_scane_button(this.loginbutton, "register", "register_panel");

        return login_panel;

    }

    void center_elements(JPanel login_panel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        JLabel l1, l2;
        l1 = new JLabel("First Label.");
        l1.setBounds(50, 50, 100, 30);
        this.loginbutton = new JButton("click");// create button

        JTextField login_input = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        // login_input.setBounds(50, 100, 150, 20);
        // passwordField.setSize(150, 20);
        passwordField.setPreferredSize(new Dimension(150, 30));
        // this.loginbutton.setPreferredSize(new Dimension(150, 30));
        JPanel buttons = new JPanel(new GridBagLayout());
        // buttons.setPreferredSize(new Dimension(20, 300));
        JButton start = new JButton("Start");
        start.setPreferredSize(new Dimension(400, 20));
        buttons.add(start, gbc);

        // buttons.add(Box.createRigidArea(new Dimension(0, 10)), gbc);
        JButton input_login = new JButton("Start");
        input_login.setSize(new Dimension(200, 20));

        buttons.add(input_login, gbc);
        buttons.add(new JButton("Help"), gbc);
        buttons.add(loginbutton, gbc);

        gbc.weighty = 1;
        login_panel.add(buttons, gbc);

    }

    void switch_scane_button(JButton button, String scane_name, String title) {
        button.addActionListener(e -> {
            // Perform logout actions here
            // If logout is successful, switch back to the login panel
            cardLayout.show(mainPanel, scane_name);
            this.setTitle(title);
        });
    }

    JPanel create_register_panel() {
        JPanel register_panel = new JPanel(new GridLayout(1, 1));
        JLabel l1, l2;
        l1 = new JLabel("First Label.");
        l1.setBounds(50, 50, 100, 30);
        this.registerbutton = new JButton("click2");// create button

        JTextField login_input = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        login_input.setBounds(50, 100, 150, 20);
        passwordField.setBounds(50, 150, 150, 20);
        this.registerbutton.setBounds(50, 170, 100, 40);
        register_panel.add(login_input);
        register_panel.add(this.registerbutton);// adding button on frame
        register_panel.add(l1);
        register_panel.add(passwordField);
        register_panel.setLayout(null);
        register_panel.setSize(400, 500);
        return register_panel;

    }

    public static void main(String[] args) {
        // Simple2 app = new Simple2();
        // app.run_app();

    }
}
