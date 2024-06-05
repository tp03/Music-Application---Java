import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class LoginFrame extends JFrame {
    private Color backgroundColor;
    private Color buttonColor;
    private Font defaultFont;
    private Dimension dimensions;
    CardLayout cardLayout = new CardLayout();
    JPanel mainPanel = new JPanel();
    private JPanel loginPanel;
    private JPanel registerPanel;
    private JButton move_to_register;
    private JButton move_to_login;
    public JButton registerbutton;
    public JButton loginbutton;
    public Map<String, JTextField> login_input_map = new HashMap<>();
    public Map<String, JTextField> register_input_map = new HashMap<>();
    public JPasswordField password_field_login;
    public JPasswordField password_field_register;

    LoginFrame() {
        dimensions = new Dimension(600, 800);
        this.setSize(dimensions.width, dimensions.height);
        this.setLocationRelativeTo(null);

        backgroundColor = new Color(245, 245, 245);  // Light grey background
        buttonColor = new Color(70, 130, 180);  // Steel blue button color
        defaultFont = new Font("Arial", Font.PLAIN, 14);  // Default font

        this.loginPanel = create_login_panel();
        this.registerPanel = create_register_panel();

        mainPanel.setLayout(cardLayout);
        mainPanel.setBackground(backgroundColor);
        this.add(mainPanel);
        mainPanel.add(loginPanel, "login");
        mainPanel.add(registerPanel, "register");
        cardLayout.show(mainPanel, "login");
        this.setTitle("LoginPanel");
        this.add(mainPanel);
        this.setVisible(true);
    }

    JPanel create_login_panel() {
        JPanel login_panel = new JPanel();
        login_panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        login_panel.setLayout(new GridBagLayout());
        center_elements_login(login_panel);
        login_panel.setBackground(backgroundColor);

        login_panel.setSize(dimensions.width, dimensions.height);
        switch_scane_button(this.move_to_register, "register", "Register");

        return login_panel;
    }

    void center_elements_login(JPanel login_panel) {
        GridBagConstraints gbc = create_gbc();

        JLabel l1 = new JLabel("Nickname:");
        l1.setFont(defaultFont);
        l1.setBounds(50, 50, 100, 30);

        JLabel l2 = new JLabel("Password:");
        l2.setFont(defaultFont);
        l2.setBounds(50, 50, 100, 30);

        JTextField login_input = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        this.loginbutton = createStyledButton("Login");
        this.move_to_register = createStyledButton("Register");

        passwordField.setPreferredSize(new Dimension(250, 20));

        JPanel main_objts = new JPanel(new GridBagLayout());
        main_objts.setBackground(backgroundColor);

        main_objts.add(l1, gbc);
        main_objts.add(login_input, gbc);
        main_objts.add(l2, gbc);
        main_objts.add(passwordField, gbc);

        login_input_map.put("nickname", login_input);
        password_field_login = passwordField;

        gbc.insets = new Insets(30, 10, 5, 10);
        main_objts.add(this.loginbutton, gbc);
        gbc.insets = new Insets(0, 10, 30, 10);
        main_objts.add(move_to_register, gbc);

        main_objts.setSize(300, 600);
        login_panel.add(main_objts, gbc);
    }

    JPanel create_register_panel() {
        JPanel register_panel = new JPanel();
        register_panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        register_panel.setLayout(new GridBagLayout());
        center_elements_register(register_panel);
        register_panel.setBackground(backgroundColor);

        register_panel.setSize(400, 500);
        switch_scane_button(this.move_to_login, "login", "Login");
        return register_panel;
    }

    void center_elements_register(JPanel register_panel) {
        GridBagConstraints gbc = create_gbc();

        JLabel l1 = new JLabel("Nickname:");
        l1.setFont(defaultFont);
        l1.setBounds(50, 50, 100, 30);

        JLabel l2 = new JLabel("Name:");
        l2.setFont(defaultFont);
        l2.setBounds(50, 50, 100, 30);

        JLabel l3 = new JLabel("Password:");
        l3.setFont(defaultFont);
        l3.setBounds(50, 50, 100, 30);

        JLabel l4 = new JLabel("Email:");
        l4.setFont(defaultFont);
        l4.setBounds(50, 50, 100, 30);

        JLabel l5 = new JLabel("Surname:");
        l5.setFont(defaultFont);
        l5.setBounds(50, 50, 100, 30);

        JTextField nickname_input = new JTextField();
        JTextField surname_input = new JTextField();
        JTextField name_input = new JTextField();
        JTextField email_input = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        this.registerbutton = createStyledButton("Create user");
        this.move_to_login = createStyledButton("Login");

        passwordField.setPreferredSize(new Dimension(250, 20));

        JPanel main_objts = new JPanel(new GridBagLayout());
        main_objts.setBackground(backgroundColor);

        main_objts.add(l1, gbc);
        main_objts.add(nickname_input, gbc);
        main_objts.add(l4, gbc);
        main_objts.add(email_input, gbc);
        main_objts.add(l2, gbc);
        main_objts.add(name_input, gbc);
        main_objts.add(l5, gbc);
        main_objts.add(surname_input, gbc);
        main_objts.add(l3, gbc);
        main_objts.add(passwordField, gbc);

        gbc.insets = new Insets(30, 10, 5, 10);
        main_objts.add(this.registerbutton, gbc);
        gbc.insets = new Insets(0, 10, 30, 10);
        main_objts.add(this.move_to_login, gbc);

        register_input_map.put("nickname", nickname_input);
        register_input_map.put("email", email_input);
        register_input_map.put("name", name_input);
        register_input_map.put("surname", surname_input);
        password_field_register = passwordField;

        register_panel.add(main_objts, gbc);
    }

    GridBagConstraints create_gbc() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weighty = 1;
        return gbc;
    }

    void switch_scane_button(JButton button, String scane_name, String title) {
        button.addActionListener(e -> {
            cardLayout.show(mainPanel, scane_name);
            this.setTitle(title);
        });
    }

    JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(buttonColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(defaultFont);
        return button;
    }

    // New methods for Login_or_Register class integration
    public Map<String, String> collect_register_data() {
        Map<String, String> data = new HashMap<>();
        data.put("nickname", register_input_map.get("nickname").getText());
        data.put("email", register_input_map.get("email").getText());
        data.put("name", register_input_map.get("name").getText());
        data.put("surname", register_input_map.get("surname").getText());
        data.put("password", new String(password_field_register.getPassword()));
        return data;
    }

    public Map<String, String> collect_login_data() {
        Map<String, String> data = new HashMap<>();
        data.put("nickname", login_input_map.get("nickname").getText());
        data.put("password", new String(password_field_login.getPassword()));
        return data;
    }

    public void highlight_unfilled_register(List<String> fields) {
        reset_register_color();
        for (String field : fields) {
            if (login_input_map.containsKey(field)) {
                register_input_map.get(field).setBackground(Color.PINK);
            } else {
                password_field_register.setBackground(Color.PINK);
            }

            // JTextField textField = login_input_map.get(field);
            // textField.setBackground(Color.PINK);
        }
    }

    public void highlight_unfilled_login(List<String> fields) {
        reset_login_color();
        for (String field : fields) {
            if (login_input_map.containsKey(field)) {
                login_input_map.get(field).setBackground(Color.PINK);
            } else {
                password_field_login.setBackground(Color.PINK);
            }

            // JTextField textField = login_input_map.get(field);
            // textField.setBackground(Color.PINK);
        }
    }

    public void reset_register_color() {
        for (JTextField value : register_input_map.values()) {
            value.setBackground(new Color(255, 255, 255));
        }
        password_field_register.setBackground(new Color(255, 255, 255));
    }

    public void reset_login_color() {
        for (JTextField value : login_input_map.values()) {
            value.setBackground(new Color(255, 255, 255));
        }
        password_field_login.setBackground(new Color(255, 255, 255));
    }
}
