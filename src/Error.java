import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Error extends JDialog {
    public Error(Frame parent, int which_one) {
        super(parent, "Błąd", true);

        String text = null;


        // Ustawienie komunikatu
        if (which_one == 0)
        {
            text = "Błędnie wprowadzone hasło lub nazwa użytkownika. Spróbuj ponownie.";
        }
        else if (which_one == 1)
        {
            text = "Nazwa użytkownika jest już w użyciu. Spróbuj użyć innej.";
        }
        else if (which_one == 2)
        {
            text = "Playlista o tej nazwie już istnieje";
        }

        JLabel messageLabel = new JLabel(text);
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
    public static void showErrorDialog(Frame parent, int which) {
        Error dialog = new Error(parent, which);
        dialog.setVisible(true);
    }
}
