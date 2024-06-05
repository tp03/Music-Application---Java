import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncorrectPassword extends JDialog {
    public IncorrectPassword(Frame parent) {
        super(parent, "Błąd", true);

        // Ustawienie komunikatu
        JLabel messageLabel = new JLabel("Błędnie wprowadzone hasło lub nazwa użytkownika. Spróbuj ponownie.");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Ustawienie przycisku OK
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Zamknięcie okienka dialogowego
                dispose();
            }
        });

        // Dodanie elementów do okna dialogowego
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(messageLabel, BorderLayout.CENTER);
        getContentPane().add(okButton, BorderLayout.SOUTH);

        // Ustawienie rozmiaru i położenia okna dialogowego
        setSize(500, 150);
        setLocationRelativeTo(parent);
    }

    // Metoda do wyświetlania okna dialogowego
    public static void showIncorrectPasswordDialog(Frame parent) {
        IncorrectPassword dialog = new IncorrectPassword(parent);
        dialog.setVisible(true);
    }
}
