import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsedUsername extends JDialog {
    public UsedUsername(Frame parent) {
        super(parent, "Błąd", true);

        JLabel messageLabel = new JLabel("Nazwa użytkownika jest już w użyciu. Spróbuj użyć innej.");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(messageLabel, BorderLayout.CENTER);
        getContentPane().add(okButton, BorderLayout.SOUTH);

        setSize(500, 150);
        setLocationRelativeTo(parent);
    }

    public static void showUsedUsername(Frame parent) {
        UsedUsername dialog = new UsedUsername(parent);
        dialog.setVisible(true);
    }
}
