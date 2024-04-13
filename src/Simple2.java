import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

public class Simple2 {// inheriting JFrame
    CardLayout cardLayout = new CardLayout();
    JPanel mainPanel = new JPanel();
    JFrame frame = new JFrame();

    void run_app() {
        frame = new JFrame("Login App Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel loginPanel = create_login_panel();
        JPanel registerPanel = create_register_panel();

        mainPanel.setLayout(cardLayout);
        frame.add(mainPanel);
        mainPanel.add(loginPanel, "login");
        mainPanel.add(registerPanel, "register");
        cardLayout.show(mainPanel, "login");
        frame.setTitle("LoginPanel");
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    JPanel create_login_panel() {
        JPanel login_panel = new JPanel();
        JLabel l1, l2;
        l1 = new JLabel("First Label.");
        l1.setBounds(50, 50, 100, 30);
        JButton b = new JButton("click");// create button

        JTextField login_input = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        login_input.setBounds(50, 100, 150, 20);
        passwordField.setBounds(50, 150, 150, 20);
        b.setBounds(50, 170, 100, 40);
        login_panel.add(login_input);
        login_panel.add(b);// adding button on frame
        login_panel.add(l1);
        login_panel.add(passwordField);
        login_panel.setLayout(null);
        login_panel.setSize(400, 500);
        switch_scane_button(b, "register", "register_panel");

        return login_panel;

    }

    void switch_scane_button(JButton button, String scane_name, String title) {
        button.addActionListener(e -> {
            // Perform logout actions here
            // If logout is successful, switch back to the login panel
            cardLayout.show(mainPanel, scane_name);
            frame.setTitle(title);
        });
    }

    JPanel create_register_panel() {
        JPanel register_panel = new JPanel();
        JLabel l1, l2;
        l1 = new JLabel("First Label.");
        l1.setBounds(50, 50, 100, 30);
        JButton b = new JButton("click2");// create button

        JTextField login_input = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        login_input.setBounds(50, 100, 150, 20);
        passwordField.setBounds(50, 150, 150, 20);
        b.setBounds(50, 170, 100, 40);
        register_panel.add(login_input);
        register_panel.add(b);// adding button on frame
        register_panel.add(l1);
        register_panel.add(passwordField);
        register_panel.setLayout(null);
        register_panel.setSize(400, 500);
        return register_panel;

    }

    public static void main(String[] args) {
        Simple2 app = new Simple2();
        app.run_app();

    }
}
