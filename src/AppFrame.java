import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AppFrame extends JFrame implements ActionListener{
    private Color backgroundColor;
    private Color textColor;
    private ImageIcon imageIcon;
    private ImageIcon returnIcon;
    private ImageIcon skipIcon;
    private ImageIcon pauseIcon;
    private JLabel titleLabel;
    private Dimension screenSize;
    private int buttonWidth;
    private JButton skipButton;
    private JButton pauseButton;
    private JButton returnButton;
    private JButton searchButton;
    private JTextField searchField;
    private Color panelColor;
    AppFrame(){
        this.imageIcon = new ImageIcon("assets/logo1.png");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        this.imageIcon = new ImageIcon(newImage);

        this.returnIcon = new ImageIcon("assets/return.png");
        image = returnIcon.getImage();
        newImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        this.returnIcon = new ImageIcon(newImage);
        
        this.skipIcon = new ImageIcon("assets/skip.png");
        image = skipIcon.getImage();
        newImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        this.skipIcon = new ImageIcon(newImage);

        this.pauseIcon = new ImageIcon("assets/pause.png");
        image = pauseIcon.getImage();
        newImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        this.pauseIcon = new ImageIcon(newImage);

        this.buttonWidth = 100;
        this.titleLabel = new JLabel(imageIcon);
        this.backgroundColor = new Color(230, 138, 0);
        this.textColor = new Color(0,0,0);
        this.panelColor = Color.blue; // set to blue for visibility
        this.skipButton = new JButton(skipIcon);
        this.pauseButton = new JButton(pauseIcon);
        this.returnButton = new JButton(returnIcon);

        this.searchButton = new JButton("Search");

        //Border border = BorderFactory.createLineBorder(Color.green,3);
        
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        this.screenSize = toolkit.getScreenSize();

        // BUTTON ACTIONS
        skipButton.setPreferredSize(new Dimension(buttonWidth,buttonWidth));
        skipButton.addActionListener(this);
        skipButton.setBackground(Color.BLACK);
        pauseButton.setPreferredSize(new Dimension(buttonWidth,buttonWidth));
        pauseButton.addActionListener(this);
        pauseButton.setBackground(Color.BLACK);
        returnButton.setPreferredSize(new Dimension(buttonWidth,buttonWidth));
        returnButton.addActionListener(this);
        returnButton.setBackground(Color.BLACK);

        searchButton.setPreferredSize(new Dimension(120, 50));
        searchButton.setFont(new Font("Arial", Font.BOLD, 18));
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(Color.BLACK);
        searchButton.addActionListener(this);

        // TITLE ACTIONS
        // titleLabel.setBorder(border);
        titleLabel.setText("SpotiSing");
        // titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        // titleLabel.setVerticalTextPosition(JLabel.TOP);
        titleLabel.setForeground(textColor);
        titleLabel.setFont(new Font("Monospaced",Font.PLAIN, 20));
        // titleLabel.setIconTextGap(10);
        // titleLabel.setVerticalAlignment(JLabel.TOP);
        // titleLabel.setHorizontalAlignment(JLabel.CENTER);
        // titleLabel.setBounds(this.frameWidth/2-this.labelWidth/2, 0, labelWidth, labelWidth);

        // SEARCH BAR ACTIONS
        this.searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(400, 50));
        searchField.setFont(new Font("Arial", Font.PLAIN, 18));
        searchField.setForeground(Color.WHITE);
        searchField.setBackground(Color.BLACK);

        // PANEL ACTIONS
        JPanel redPanel = new JPanel();
        redPanel.setBackground(panelColor);
        redPanel.add(titleLabel);
        Dimension prefSize = redPanel.getPreferredSize();
        redPanel.setBounds(0, 0, prefSize.width, prefSize.height);
        

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(panelColor);
        bluePanel.add(returnButton);
        bluePanel.add(pauseButton);
        bluePanel.add(skipButton);
        prefSize = bluePanel.getPreferredSize();
        bluePanel.setBounds(this.screenSize.width/2 - prefSize.width/2, this.screenSize.height-2*prefSize.height, prefSize.width, prefSize.height);

        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(panelColor);
        searchPanel.add(this.searchField, BorderLayout.CENTER);
        searchPanel.add(this.searchButton, BorderLayout.EAST);
        prefSize = searchPanel.getPreferredSize();
        searchPanel.setBounds(this.screenSize.width/2 - prefSize.width/2,0,prefSize.width, prefSize.height);


        // FRAME ACTIONS
        this.setTitle("SpotiSing");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLayout(null);
        //this.add(titleLabel);
        this.add(redPanel);
        this.add(bluePanel);
        this.add(searchPanel);
        this.getContentPane().setBackground(backgroundColor);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==skipButton)
        {
            System.out.println("skip");
        }
        else if(e.getSource()==pauseButton)
        {
            System.out.println("pause");
        }
        else if(e.getSource()==returnButton)
        {
            System.out.println("return");
        }
        else if(e.getSource()==searchButton)
        {
            String searchTerm = this.searchField.getText();
            System.out.println("Searching for: " + searchTerm);
        }
    }
}
