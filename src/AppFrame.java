import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

import javax.swing.JComponent;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JComponent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class AppFrame extends JFrame implements ActionListener {
    private Color backgroundColor;
    private Color textColor;
    private ImageIcon imageIcon;
    private ImageIcon returnIcon;
    private ImageIcon skipIcon;
    private ImageIcon pauseIcon;
    private ImageIcon playIcon;
    private ImageIcon searchIcon;
    private JLabel titleLabel;
    private Dimension screenSize;
    private int buttonWidth;
    private JButton skipButton;
    private JButton pauseButton;
    private JButton returnButton;
    private JButton searchButton;
    private JTextField searchField;
    private Color panelColor;
    private Color panelColor2;
    private int songWidth;
    private Clip clip;
    private JProgressBar progressBar;
    private ActionListener timerAction;
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

        this.playIcon = new ImageIcon("assets/play.png");
        image = this.playIcon.getImage();
        newImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        this.playIcon = new ImageIcon(newImage);

        this.searchIcon = new ImageIcon("assets/search.png");
        image = this.searchIcon.getImage();
        newImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        this.searchIcon = new ImageIcon(newImage);

        this.buttonWidth = 100;
        this.songWidth = 600;
        this.titleLabel = new JLabel(imageIcon);
        this.backgroundColor = new Color(230, 138, 0);
        this.textColor = new Color(0,0,0);
        this.panelColor = Color.blue; // set to blue for visibility
        this.panelColor2 = panelColor;
        this.skipButton = new JButton(skipIcon);
        this.pauseButton = new JButton(pauseIcon);
        this.returnButton = new JButton(returnIcon);

        this.searchButton = new JButton(searchIcon);

        //Border border = BorderFactory.createLineBorder(Color.green,3);
        
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        this.screenSize = toolkit.getScreenSize();

        // BUTTON ACTIONS
        skipButton.setPreferredSize(new Dimension(buttonWidth, buttonWidth));
        skipButton.addActionListener(this);
        skipButton.setBackground(Color.BLACK);
        pauseButton.setPreferredSize(new Dimension(buttonWidth, buttonWidth));
        pauseButton.addActionListener(this);
        pauseButton.setBackground(Color.BLACK);
        returnButton.setPreferredSize(new Dimension(buttonWidth, buttonWidth));
        returnButton.addActionListener(this);
        returnButton.setBackground(Color.BLACK);

        searchButton.setPreferredSize(new Dimension(80, 50));
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
        titleLabel.setFont(new Font("Monospaced", Font.PLAIN, 20));
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
        Dimension searchprefSize = searchPanel.getPreferredSize();
        searchPanel.setBounds(this.screenSize.width/2 - searchprefSize.width/2,0,searchprefSize.width, searchprefSize.height);

        JPanel songListPanel = new JPanel(new GridLayout(0, 1));
        songListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        songListPanel.setBackground(panelColor);

        ArrayList<Song> songs = createSampleSongs();

        for (Song song : songs) {
            JPanel songPanel = createSongPanel(song);
            songListPanel.add(songPanel);
            // JPanel spacerPanel = new JPanel();
            // spacerPanel.setPreferredSize(new Dimension(songWidth, 1));
            // spacerPanel.setBackground(panelColor);
            // songListPanel.add(spacerPanel);
        }
        
        JScrollPane scrollPane = new JScrollPane(songListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        prefSize = scrollPane.getPreferredSize();
        scrollPane.setBounds(this.screenSize.width/2 - prefSize.width/2,searchprefSize.height,prefSize.width, 
            this.screenSize.height-searchprefSize.height*6);
        //scrollPane.setBounds(this.screenSize.width/2 - prefSize.width/2,searchprefSize.height,prefSize.width, prefSize.height);

        // PROGRESS BAR ACTIONS
        this.progressBar = new JProgressBar(0, 100);
        this.progressBar.setStringPainted(true);
        this.progressBar.setPreferredSize(new Dimension(songWidth, 50));
        this.timerAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Update the progress bar based on the current position of the clip
                if (clip != null && AppFrame.this.clip.isOpen() && AppFrame.this.clip.isRunning()) {
                    long currentPosition = AppFrame.this.clip.getMicrosecondPosition();
                    long totalLength = AppFrame.this.clip.getMicrosecondLength();
                    double progress = (double) currentPosition / totalLength;
                    AppFrame.this.progressBar.setValue((int) (progress * 100)); // Set the progress bar value
                }
            }
        };
        JPanel progressBarPanel = new JPanel();
        progressBarPanel.setBackground(panelColor);
        progressBarPanel.add(progressBar);
        prefSize = progressBarPanel.getPreferredSize();
        progressBarPanel.setBounds(this.screenSize.width/2 - prefSize.width/2, this.screenSize.height - searchprefSize.height*5, prefSize.width, prefSize.height);

        // FRAME ACTIONS
        this.setTitle("SpotiSing");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLayout(null);
        // this.add(titleLabel);
        this.add(redPanel);
        this.add(bluePanel);
        this.add(searchPanel);
        //this.add(songListPanel, BorderLayout.CENTER);
        this.add(scrollPane);
        this.add(progressBarPanel);
        this.getContentPane().setBackground(backgroundColor);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == skipButton) {
            System.out.println("skip");
            long clipLength = this.clip.getMicrosecondLength();
            this.clip.setMicrosecondPosition(clipLength);
        }
        else if(e.getSource()==pauseButton)
        {
            if (pauseButton.getIcon().equals(pauseIcon)) {
                pauseButton.setIcon(playIcon);
                System.out.println("pause");
                this.clip.stop();
            } else {
                pauseButton.setIcon(pauseIcon);
                System.out.println("play");
                this.clip.start();
            }
            
        }
        else if(e.getSource()==returnButton)
        {
            System.out.println("return");
            this.clip.setMicrosecondPosition(0);
        }
        else if(e.getSource()==searchButton)
        {
            String searchTerm = this.searchField.getText();
            System.out.println("Searching for: " + searchTerm);
        }
    }
    

    private ArrayList<Song> createSampleSongs() {
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("Moja Gwiazda", "Zenon Martyniuk", "assets/zenek1.png", "recordings/gwiazda.mp3"));
        songs.add(new Song("Życie To Są Chwile", "Zenon Martyniuk", "assets/zenek2.jpg", "recordings/chwile.mp3"));
        songs.add(new Song("Przez Twe Oczy Zielone", "Zenon Martuniuk", "assets/zenek3.jpeg", "recordings/gwiazda.mp3"));
        songs.add(new Song("Moja Gwiazda", "Zenon Martyniuk", "assets/zenek1.png", "recordings/gwiazda.mp3"));
        songs.add(new Song("Życie To Są Chwile", "Zenon Martyniuk", "assets/zenek2.jpg", "recordings/chwile.mp3"));
        songs.add(new Song("Przez Twe Oczy Zielone", "Zenon Martuniuk", "assets/zenek3.jpeg", "recordings/gwiazda.mp3"));
        songs.add(new Song("Moja Gwiazda", "Zenon Martyniuk", "assets/zenek1.png", "recordings/gwiazda.mp3"));
        songs.add(new Song("Życie To Są Chwile", "Zenon Martyniuk", "assets/zenek2.jpg", "recordings/chwile.mp3"));
        songs.add(new Song("Przez Twe Oczy Zielone", "Zenon Martuniuk", "assets/zenek3.jpeg", "recordings/gwiazda.mp3"));
        songs.add(new Song("Moja Gwiazda", "Zenon Martyniuk", "assets/zenek1.png", "recordings/gwiazda.mp3"));
        songs.add(new Song("Życie To Są Chwile", "Zenon Martyniuk", "assets/zenek2.jpg", "recordings/chwile.mp3"));
        songs.add(new Song("Przez Twe Oczy Zielone", "Zenon Martuniuk", "assets/zenek3.jpeg", "recordings/gwiazda.mp3"));
        songs.add(new Song("Moja Gwiazda", "Zenon Martyniuk", "assets/zenek1.png", "recordings/gwiazda.mp3"));
        songs.add(new Song("Życie To Są Chwile", "Zenon Martyniuk", "assets/zenek2.jpg", "recordings/chwile.mp3"));
        songs.add(new Song("Przez Twe Oczy Zielone", "Zenon Martuniuk", "assets/zenek3.jpeg", "recordings/gwiazda.mp3"));
        songs.add(new Song("Moja Gwiazda", "Zenon Martyniuk", "assets/zenek1.png", "recordings/gwiazda.mp3"));
        songs.add(new Song("Życie To Są Chwile", "Zenon Martyniuk", "assets/zenek2.jpg", "recordings/chwile.mp3"));
        songs.add(new Song("Przez Twe Oczy Zielone", "Zenon Martuniuk", "assets/zenek3.jpeg", "recordings/gwiazda.mp3"));
        return songs;
    }

    private JPanel createSongPanel(Song song) {
        // Create a panel for a single song
        JPanel songPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        songPanel.setLayout(new BoxLayout(songPanel, BoxLayout.X_AXIS)); 
        songPanel.setPreferredSize(new Dimension(songWidth, 50));
        // songPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Color borderColor = panelColor; // Specify the border color
        int borderThickness = 5; // Specify the border thickness
        LineBorder border = new LineBorder(borderColor, borderThickness);
        songPanel.setBorder(border);
        songPanel.setBackground(Color.BLACK);

        // Create a label for the song name
        JLabel nameLabel = new JLabel(song.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 22)); // Set font size
        nameLabel.setForeground(Color.WHITE);
        songPanel.add(nameLabel, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentPanel.setBackground(Color.BLACK);

        // Create a label for the author
        JLabel authorLabel = new JLabel(" - " + song.getAuthor());
        authorLabel.setFont(new Font("Arial", Font.PLAIN, 22)); // Set font size
        authorLabel.setForeground(Color.WHITE);
        songPanel.add(authorLabel);

        // Create an image icon for the song image
        ImageIcon imageIcon = new ImageIcon(song.getImagePath());
        Image image = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Resize image
        ImageIcon resizedIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(resizedIcon);
        songPanel.add(Box.createHorizontalGlue());
        songPanel.add(imageLabel);

        ((JComponent) songPanel).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Play the recording
                String recordingPath = song.getRecordingPath();
                if (recordingPath != null && !recordingPath.isEmpty()) {
                    try {
                        // Use any audio player library or API to play the recording
                        // For example, you can use the Java Sound API or a third-party library like JLayer
                        // Here's a simple example using the Java Sound API:
                        File recordingFile = new File(recordingPath);
                        AudioInputStream audioStream = AudioSystem.getAudioInputStream(recordingFile);
                        AppFrame.this.clip  = AudioSystem.getClip();
                        AppFrame.this.clip .open(audioStream);
                        AppFrame.this.clip .start();

                        Timer timer = new Timer(100, timerAction);
                        timer.start(); 
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        return songPanel;
    }
    
}

