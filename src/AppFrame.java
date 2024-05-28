import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
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
    private ImageIcon addIcon;
    private ImageIcon backgroundIcon;
    private JLabel titleLabel;
    private Dimension screenSize;
    private int buttonWidth;
    private JButton skipButton;
    private JButton pauseButton;
    private JButton returnButton;
    private JButton searchButton;
    private JButton addButton;
    private JTextField searchField;
    private Color panelColor;
    private Color panelColor2;
    private int songWidth;
    private Clip clip;
    private JProgressBar progressBar;
    private ActionListener timerAction;
    private Spotify_user activeUser;
    private JPanel songListPanel;
    private JScrollPane scrollPane = new JScrollPane(songListPanel);
    private JPanel searchPanel;
    private SongImport songImporter = new SongImport("/recordings", "/assets");

    private JPanel playlistPanel;
    private DefaultListModel<String> playlistModel;
    private JList<String> playlistList;
    private JButton addPlaylistButton;
    private JButton removePlaylistButton;

    private JPanel userPanel;
    private JLabel usernameLabel;
    private JLabel backgroundLabel;

    private String newPlaylistName;

    AppFrame() {

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

        this.addIcon = new ImageIcon("assets/add.png");
        image = this.addIcon.getImage();
        newImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        this.addIcon = new ImageIcon(newImage);

        this.buttonWidth = 100;
        this.songWidth = 600;
        this.titleLabel = new JLabel(imageIcon);
        this.backgroundColor = new Color(230, 138, 0);
        this.textColor = new Color(0, 0, 0);
        this.panelColor = backgroundColor; // set to blue for visibility
        this.panelColor2 = panelColor;
        this.skipButton = new JButton(skipIcon);
        this.pauseButton = new JButton(pauseIcon);
        this.returnButton = new JButton(returnIcon);
        this.searchButton = new JButton(searchIcon);
        this.addButton = new JButton(addIcon);

        // Border border = BorderFactory.createLineBorder(Color.green,3);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        this.screenSize = toolkit.getScreenSize();

        this.backgroundIcon = new ImageIcon("assets/images.jpeg");
        image = this.backgroundIcon.getImage();
        newImage = image.getScaledInstance(this.screenSize.width, this.screenSize.height, Image.SCALE_SMOOTH);
        this.backgroundIcon = new ImageIcon(newImage);
        this.backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, this.screenSize.width, this.screenSize.height);

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

        addButton.setPreferredSize(new Dimension(80, 50));
        addButton.addActionListener(this);
        addButton.setBackground(Color.BLACK);

        // TITLE ACTIONS
        // titleLabel.setBorder(border);
        // UserReader reader = new UserReader("test123"); // testowy logowanie
        // Spotify_user user = reader.searchDB();

        // titleLabel.setText(user.name);
        // titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        // titleLabel.setVerticalTextPosition(JLabel.TOP);
        titleLabel.setForeground(textColor);
        titleLabel.setFont(new Font("Monospaced", Font.PLAIN, 20));
        // titleLabel.setIconTextGap(10);
        // titleLabel.setVerticalAlignment(JLabel.TOP);
        // titleLabel.setHorizontalAlignment(JLabel.CENTER);
        // titleLabel.setBounds(this.frameWidth/2-this.labelWidth/2, 0, labelWidth,
        // labelWidth);

        // SEARCH BAR ACTIONS
        this.searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(400, 50));
        searchField.setFont(new Font("Arial", Font.PLAIN, 18));
        searchField.setForeground(Color.WHITE);
        searchField.setBackground(Color.BLACK);

        this.songListPanel = new JPanel(new GridLayout(0, 1));
        songListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        songListPanel.setBackground(panelColor);

        // PANEL ACTIONS
        JPanel redPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

            }
        };
        redPanel.setOpaque(false);
        // redPanel.setBackground(panelColor);
        redPanel.add(titleLabel);
        Dimension prefSize = redPanel.getPreferredSize();
        redPanel.setBounds(0, 0, prefSize.width, prefSize.height);

        JPanel bluePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

            }
        };
        bluePanel.setOpaque(false);
        // bluePanel.setBackground(panelColor);
        bluePanel.add(returnButton);
        bluePanel.add(pauseButton);
        bluePanel.add(skipButton);
        prefSize = bluePanel.getPreferredSize();
        bluePanel.setBounds(this.screenSize.width / 2 - prefSize.width / 2,
                this.screenSize.height - 2 * prefSize.height, prefSize.width, prefSize.height);

        this.searchPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

            }
        };
        searchPanel.setOpaque(false);
        // this.searchPanel.setBackground(panelColor);
        this.searchPanel.add(this.searchField, BorderLayout.CENTER);
        this.searchPanel.add(this.searchButton, BorderLayout.EAST);
        Dimension searchprefSize = this.searchPanel.getPreferredSize();
        this.searchPanel.setBounds(this.screenSize.width / 2 - searchprefSize.width / 2, 0, searchprefSize.width,
                searchprefSize.height);

        // scrollPane.setBounds(this.screenSize.width/2 -
        // prefSize.width/2,searchprefSize.height,prefSize.width, prefSize.height);

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
        JPanel progressBarPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

            }
        };
        progressBarPanel.setOpaque(false);
        // progressBarPanel.setBackground(panelColor);
        progressBarPanel.add(progressBar);
        prefSize = progressBarPanel.getPreferredSize();
        progressBarPanel.setBounds(this.screenSize.width / 2 - prefSize.width / 2,
                this.screenSize.height - searchprefSize.height * 5, prefSize.width, prefSize.height);

        // PLAYLIST PANEL ACTIONS
        this.playlistPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

            }
        };
        playlistPanel.setOpaque(false);
        // this.playlistPanel.setBackground(panelColor);
        this.playlistPanel.setBorder(BorderFactory.createTitledBorder("Playlists"));

        this.playlistModel = new DefaultListModel<>();
        this.playlistList = new JList<>(playlistModel);
        JScrollPane playlistScrollPane = new JScrollPane(playlistList);

        this.addPlaylistButton = new JButton("Add Playlist");
        this.addPlaylistButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playlistName = JOptionPane.showInputDialog("Enter playlist name:");
                if (playlistName != null && !playlistName.trim().isEmpty()) {
                    playlistModel.addElement(playlistName);
                }
            }
        });

        this.removePlaylistButton = new JButton("Remove Playlist");
        this.removePlaylistButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = playlistList.getSelectedIndex();
                if (selectedIndex != -1) {
                    playlistModel.remove(selectedIndex);
                }
            }
        });

        JPanel playlistButtonPanel = new JPanel();
        playlistButtonPanel.add(addPlaylistButton);
        playlistButtonPanel.add(removePlaylistButton);

        this.playlistPanel.add(playlistScrollPane, BorderLayout.CENTER);
        this.playlistPanel.add(playlistButtonPanel, BorderLayout.SOUTH);
        prefSize = this.playlistPanel.getPreferredSize();
        this.playlistPanel.setBounds(this.screenSize.width - prefSize.width - 40,
                this.searchPanel.getPreferredSize().height, prefSize.width,
                this.screenSize.height - this.searchPanel.getPreferredSize().height * 4);

        // USER PANEL ACTIONS
        this.userPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

            }
        };
        userPanel.setOpaque(false);
        // this.userPanel.setBackground(panelColor);
        this.userPanel.setBorder(BorderFactory.createTitledBorder("User"));
        this.usernameLabel = new JLabel("Username: " + (activeUser != null ? activeUser.getName() : "Guest"));
        this.usernameLabel.setForeground(Color.WHITE);
        this.usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.usernameLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String newUsername = JOptionPane.showInputDialog("Enter new username:");
                if (newUsername != null && !newUsername.trim().isEmpty()) {
                    if (activeUser != null) {
                        usernameLabel.setText("Username: " + activeUser.getName());
                    }
                    usernameLabel.setText("Username: " + newUsername);
                }
            }
        });

        this.userPanel.add(usernameLabel);
        this.userPanel.add(this.addButton, BorderLayout.EAST);
        prefSize = this.userPanel.getPreferredSize();
        this.userPanel.setBounds(this.titleLabel.getPreferredSize().width + 10, 20, prefSize.width, prefSize.height);

        // FRAME ACTIONS
        if (this.activeUser != null) {
            this.setTitle(this.activeUser.getName());
        }
        this.getContentPane().add(backgroundLabel, BorderLayout.CENTER);
        this.setContentPane(backgroundLabel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLayout(null);
        // this.add(titleLabel);
        this.add(redPanel);
        this.add(bluePanel);
        this.add(searchPanel);
        // this.add(songListPanel, BorderLayout.CENTER);
        this.add(scrollPane);
        this.add(progressBarPanel);
        this.add(playlistPanel);
        this.add(userPanel);
        // this.getContentPane().setBackground(backgroundColor);
        drawCustom();
    }

    public void drawCustom() {
        if (this.activeUser != null) {
            this.setTitle(this.activeUser.getName());
            this.usernameLabel.setText("Username: " + this.activeUser.getName());
        }
        drawSongs();
    }

    public void drawSongs() {
        this.remove(this.scrollPane);
        this.songListPanel = new JPanel(new GridLayout(0, 1));
        songListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        songListPanel.setBackground(panelColor);

        ArrayList<Song> songs = createSongsArray();

        for (Song song : songs) {
            JPanel songPanel = createSongPanel(song);
            System.out.println(song.getName());
            songListPanel.add(songPanel);
        }
        Dimension searchprefSize = this.searchPanel.getPreferredSize();
        JScrollPane scrollPane = new JScrollPane(songListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        Dimension prefSize = scrollPane.getPreferredSize();
        scrollPane.setBounds(this.screenSize.width / 2 - prefSize.width / 2, searchprefSize.height, prefSize.width,
                this.screenSize.height - searchprefSize.height * 6);
        this.add(scrollPane);
        this.scrollPane = scrollPane;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == skipButton) {
            System.out.println("skip");
            long clipLength = this.clip.getMicrosecondLength();
            this.clip.setMicrosecondPosition(clipLength);
        } else if (e.getSource() == addButton) {
            this.songImporter.ImportSong("newSongName", "newSongAuthor");
        } else if (e.getSource() == pauseButton) {
            if (pauseButton.getIcon().equals(pauseIcon)) {
                pauseButton.setIcon(playIcon);
                System.out.println("pause");
                this.clip.stop();
            } else {
                pauseButton.setIcon(pauseIcon);
                System.out.println("play");
                this.clip.start();
            }

        } else if (e.getSource() == returnButton) {
            System.out.println("return");
            this.clip.setMicrosecondPosition(0);
        } else if (e.getSource() == searchButton) {
            String searchTerm = this.searchField.getText();
            System.out.println("Searching for: " + searchTerm);
        }
    }

    private ArrayList<Song> createSongsArray() {
        ArrayList<Song> songs = new ArrayList<>();
        try {
            DatabaseConnection newCon = new DatabaseConnection();
            Connection connection = newCon.MakeConnection();
            String in_query2 = "SELECT COUNT(*) FROM song_data";
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(in_query2);
            int idMax = 1;
            while (resultSet.next()) {

                idMax = resultSet.getInt("COUNT(*)");
            }
            System.out.println(idMax);

            for (int id = 1; id <= idMax; id++) {
                songs.add(new Song(id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }

    private JPanel createSongPanel(Song song) {
        // Panel
        JPanel songPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        songPanel.setLayout(new BoxLayout(songPanel, BoxLayout.X_AXIS));
        songPanel.setPreferredSize(new Dimension(songWidth, 50));
        // songPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Color borderColor = panelColor; // Specify the border color
        int borderThickness = 5; // Specify the border thickness
        LineBorder border = new LineBorder(borderColor, borderThickness);
        songPanel.setBorder(border);
        songPanel.setBackground(Color.BLACK);

        // Song Label
        JLabel nameLabel = new JLabel(song.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 22)); // Set font size
        nameLabel.setForeground(Color.WHITE);
        songPanel.add(nameLabel, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentPanel.setBackground(Color.BLACK);

        // Author Label
        JLabel authorLabel = new JLabel(" - " + song.getAuthor());
        authorLabel.setFont(new Font("Arial", Font.PLAIN, 22)); // Set font size
        authorLabel.setForeground(Color.WHITE);
        songPanel.add(authorLabel);

        // Image
        ImageIcon imageIcon = new ImageIcon(song.getImagePath());
        Image image = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Resize image
        ImageIcon resizedIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(resizedIcon);
        songPanel.add(Box.createHorizontalGlue());
        songPanel.add(imageLabel);

        ((JComponent) songPanel).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String recordingPath = song.getRecordingPath();
                if (recordingPath != null && !recordingPath.isEmpty()) {
                    try {
                        File recordingFile = new File(recordingPath);
                        AudioInputStream audioStream = AudioSystem.getAudioInputStream(recordingFile);
                        AppFrame.this.clip = AudioSystem.getClip();
                        AppFrame.this.clip.open(audioStream);
                        AppFrame.this.clip.start();

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

    void setActiveUser(Spotify_user user) {
        this.activeUser = user;
    }

}
