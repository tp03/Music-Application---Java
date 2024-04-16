import javax.swing.*;
import java.util.List;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class LoginFrame extends JFrame {// inheriting JFrame
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
        this.setSize(600, 800);
        this.setLocationRelativeTo(null);

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
        center_elements_login(login_panel);

        login_panel.setSize(600, 800);
        // login_panel.setBackground(new Color(0, 0, 0));
        switch_scane_button(this.move_to_register, "register", "register_panel");

        return login_panel;

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

    void center_elements_login(JPanel login_panel) {
        GridBagConstraints gbc = create_gbc();

        // Labels
        JLabel l1, l2;
        l1 = new JLabel("Nickname:");
        l1.setBounds(50, 50, 100, 30);
        l2 = new JLabel("Password:");
        l2.setBounds(50, 50, 100, 30);

        // InputFields
        JTextField login_input = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        this.loginbutton = new JButton("Login");
        this.move_to_register = new JButton("Register");

        // Configs
        passwordField.setPreferredSize(new Dimension(250, 20));

        JPanel main_objts = new JPanel(new GridBagLayout());

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
        // main_objts.setBackground(new Color(0, 0, 0));

        login_panel.add(main_objts, gbc);

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
        JPanel register_panel = new JPanel();
        register_panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        register_panel.setLayout(new GridBagLayout());
        center_elements_register(register_panel);

        register_panel.setSize(400, 500);
        switch_scane_button(this.move_to_login, "login", "login_panel");
        return register_panel;

    }

    void center_elements_register(JPanel register_panel) {
        GridBagConstraints gbc = create_gbc();

        // Labels
        JLabel l1, l2, l3, l4, l5;
        l1 = new JLabel("Nickname:");
        l1.setBounds(50, 50, 100, 30);
        l2 = new JLabel("Name:");
        l2.setBounds(50, 50, 100, 30);
        l3 = new JLabel("Password:");
        l3.setBounds(50, 50, 100, 30);
        l4 = new JLabel("Email:");
        l4.setBounds(50, 50, 100, 30);
        l5 = new JLabel("Surname:");
        l5.setBounds(50, 50, 100, 30);

        // InputFields
        JTextField nickname_input = new JTextField();
        JTextField surname_input = new JTextField();
        JTextField name_input = new JTextField();
        JTextField email_input = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        this.registerbutton = new JButton("Create user");
        this.move_to_login = new JButton("Login");

        // Configs
        passwordField.setPreferredSize(new Dimension(250, 20));

        JPanel main_objts = new JPanel(new GridBagLayout());

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

    void create_user_action(JButton button, JTextField name_field, JTextField email_field, JTextField login_field,
            JPasswordField password_field) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = name_field.getText();
                String login = login_field.getText();
                String password = new String(password_field.getPassword());
                String email = email_field.getText();

                // UserCreator new_user = new UserCreator();
                // new_user.created_user(1, name, login, password, email);
            }
        });
    }

    public Map<String, String> collect_login_data() {
        Map<String, String> data = new HashMap<>();
        String nickname = login_input_map.get("nickname").getText();
        String password = new String(password_field_login.getPassword());
        data.put("nickname", nickname);
        data.put("password", password);
        return data;
    }

    public Map<String, String> collect_register_data() {
        Map<String, String> data = new HashMap<>();
        String nickname = register_input_map.get("nickname").getText();
        String email = register_input_map.get("email").getText();
        String name = register_input_map.get("name").getText();
        String surname = register_input_map.get("surname").getText();
        String password = new String(password_field_register.getPassword());
        data.put("nickname", nickname);
        data.put("name", name);
        data.put("surname", surname);
        data.put("email", email);
        data.put("password", password);
        return data;

    }

    public void highlight_unfilled_login(List<String> check_result) {
        reset_login_color();
        for (String element : check_result) {
            if (login_input_map.containsKey(element)) {
                login_input_map.get(element).setBackground(new Color(200, 0, 0));
            } else {
                password_field_login.setBackground(new Color(200, 0, 0));
            }
        }
    }

    public void highlight_unfilled_register(List<String> check_result) {
        reset_register_color();
        for (String element : check_result) {
            if (register_input_map.containsKey(element)) {
                register_input_map.get(element).setBackground(new Color(200, 0, 0));
            } else {
                password_field_register.setBackground(new Color(200, 0, 0));
            }
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
