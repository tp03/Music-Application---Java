import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
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

public class AppFrame extends JFrame implements ActionListener {
    private Color backgroundColor;
    private Color textColor;
    private Color panelColor;
    private int buttonWidth;
    private int songWidth;
    private Clip clip;
    private JProgressBar progressBar;
    private ActionListener timerAction;
    private Spotify_user activeUser = null;
    private JPanel songListPanel;
    private JScrollPane scrollPane;
    private JTextField searchField;
    private JPanel searchPanel;
    private JPanel playlistPanel;
    private DefaultListModel<Playlist> playlistModel = new DefaultListModel<>();
    private JList<Playlist> playlistList;
    private JPanel userPanel;
    private JLabel usernameLabel;
    private JLabel backgroundLabel;
    private JButton skipButton;
    private JButton pauseButton;
    private JButton returnButton;
    private JButton searchButton;
    private JButton addButton;
    private ImageIcon imageIcon;
    private ImageIcon returnIcon;
    private ImageIcon skipIcon;
    private ImageIcon pauseIcon;
    private ImageIcon playIcon;
    private ImageIcon addIcon;
    private ImageIcon searchIcon;
    private ImageIcon backgroundIcon;
    private JLabel titleLabel;

    AppFrame() {
        initializeIcons();
        initializeColors();
        initializeButtons();
        initializeFrame();
        initializePanels();
    }

    public void initialize(){
        initializeIcons();
        initializeColors();
        initializeButtons();
        initializeFrame();
        initializePanels();
    }

    private void initializeIcons() {
        this.imageIcon = createScaledIcon("assets/logo1.png", 100, 100);
        this.returnIcon = createScaledIcon("assets/return.png", 50, 50);
        this.skipIcon = createScaledIcon("assets/skip.png", 50, 50);
        this.pauseIcon = createScaledIcon("assets/pause.png", 50, 50);
        this.playIcon = createScaledIcon("assets/play.png", 50, 50);
        this.searchIcon = createScaledIcon("assets/search.png", 30, 30);
        this.addIcon = createScaledIcon("assets/add.png", 30, 30);
    }

    private ImageIcon createScaledIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    private void initializeColors() {
        this.backgroundColor = new Color(230, 138, 0);
        this.textColor = new Color(0, 0, 0);
        this.panelColor = backgroundColor;
    }

    private void initializeButtons() {
        this.buttonWidth = 100;
        this.songWidth = 600;

        this.skipButton = createButton(skipIcon, buttonWidth, buttonWidth, Color.BLACK);
        this.pauseButton = createButton(pauseIcon, buttonWidth, buttonWidth, Color.BLACK);
        this.returnButton = createButton(returnIcon, buttonWidth, buttonWidth, Color.BLACK);
        this.searchButton = createButton(searchIcon, 80, 50, Color.BLACK);
        this.addButton = createButton(addIcon, 80, 50, Color.BLACK);

        this.searchButton.setFont(new Font("Arial", Font.BOLD, 18));
        this.searchButton.setForeground(Color.WHITE);
    }

    private JButton createButton(ImageIcon icon, int width, int height, Color bgColor) {
        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(bgColor);
        button.addActionListener(this);
        return button;
    }

    private void initializeFrame() {
        this.titleLabel = new JLabel(imageIcon);
        this.titleLabel.setForeground(textColor);
        this.titleLabel.setFont(new Font("Monospaced", Font.PLAIN, 20));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.backgroundIcon = createScaledIcon("assets/images.jpeg", screenSize.width, screenSize.height);
        this.backgroundLabel = new JLabel(this.backgroundIcon);
        this.backgroundLabel.setBounds(0, 0, screenSize.width, screenSize.height);
        this.getContentPane().add(backgroundLabel, BorderLayout.CENTER);
        this.setContentPane(backgroundLabel);


        this.progressBar = new JProgressBar(0, 100);
        this.progressBar.setStringPainted(true);
        this.progressBar.setPreferredSize(new Dimension(songWidth, 50));

        this.timerAction = e -> {
            if (clip != null && clip.isOpen() && clip.isRunning()) {
                long currentPosition = clip.getMicrosecondPosition();
                long totalLength = clip.getMicrosecondLength();
                double progress = (double) currentPosition / totalLength;
                progressBar.setValue((int) (progress * 100));
            }
        };

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLayout(null);
    }

    private void initializePanels() {
        if (this.activeUser != null) {
            this.setTitle(this.activeUser.getName());
            this.usernameLabel.setText("Username: " + this.activeUser.getName());
        }
        this.searchField = createSearchField();
        this.searchPanel = createSearchPanel();
        this.playlistPanel = createPlaylistPanel();
        this.scrollPane = createScrollPane();
        
        this.userPanel = createUserPanel();

        this.add(createControlPanel());
        this.add(searchPanel);
        this.add(scrollPane);
        this.add(createProgressBarPanel());
        this.add(playlistPanel);
        this.add(userPanel);
        this.add(createTopPanel(titleLabel));
    }

    private JScrollPane createScrollPane(){
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
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.scrollPane = new JScrollPane(this.songListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        Dimension prefSize = scrollPane.getPreferredSize();
        scrollPane.setBounds(screenSize.width / 2 - prefSize.width / 2, searchprefSize.height, prefSize.width,
                screenSize.height - searchprefSize.height * 6);
        return scrollPane;
    }

    private JTextField createSearchField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(400, 50));
        textField.setFont(new Font("Arial", Font.PLAIN, 18));
        textField.setForeground(Color.WHITE);
        textField.setBackground(Color.BLACK);
        return textField;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
            }
        };
        panel.setOpaque(false);
        panel.add(searchField, BorderLayout.CENTER);
        panel.add(searchButton, BorderLayout.EAST);
        panel.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - panel.getPreferredSize().width / 2, 0, panel.getPreferredSize().width, panel.getPreferredSize().height);
        return panel;
    }

    private JPanel createTopPanel(JLabel label) {
        JPanel panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
            }
        };
        panel.setOpaque(false);
        panel.add(label);
        Dimension prefSize = panel.getPreferredSize();
        panel.setBounds(0, 0, prefSize.width, prefSize.height);
        return panel;
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
            }
        };
        panel.setOpaque(false);
        panel.add(returnButton);
        panel.add(pauseButton);
        panel.add(skipButton);
        Dimension prefSize = panel.getPreferredSize();
        panel.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - prefSize.width / 2,
        Toolkit.getDefaultToolkit().getScreenSize().height - 2 * prefSize.height, prefSize.width, prefSize.height);
        return panel;
    }

    private JPanel createProgressBarPanel() {
        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
            }
        };
        panel.setOpaque(false);
        panel.add(progressBar);
        Dimension prefSize = panel.getPreferredSize();
        panel.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - prefSize.width / 2,
            Toolkit.getDefaultToolkit().getScreenSize().height - searchPanel.getPreferredSize().height * 5, prefSize.width, prefSize.height);
        return panel;
    }

    private JPanel createPlaylistPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder("Playlists"));

        ArrayList<Playlist> playlists = null;
        if (activeUser != null) {
            playlists = activeUser.getPlaylists();
            for (Playlist playlist : playlists) {
                playlistModel.addElement(playlist);
            }
        }

        this.playlistList = new JList<>(playlistModel);
        this.playlistList.setCellRenderer(new PlaylistCellRenderer());

        JScrollPane playlistScrollPane = new JScrollPane(playlistList);

        JPanel playlistButtonPanel = new JPanel();
        playlistButtonPanel.add(createButton("Add Playlist", e -> {
            String playlistName = JOptionPane.showInputDialog("Enter playlist name:");
            if (playlistName != null && !playlistName.trim().isEmpty()) {
                Playlist playlist = activeUser.createPlaylist(playlistName);
                if (playlist != null) {
                    playlistModel.addElement(playlist);
                }
                
            }
        }));
        playlistButtonPanel.add(createButton("Remove Playlist", e -> {
            int selectedIndex = playlistList.getSelectedIndex();
            if (selectedIndex != -1) {
                Playlist playlist = playlistModel.getElementAt(selectedIndex);
                playlistModel.remove(selectedIndex);
                activeUser.deletePlaylist(playlist);
            }
        }));
        playlistButtonPanel.add(createButton("Open Playlist", e -> {
            int selectedIndex = playlistList.getSelectedIndex();
            if (selectedIndex != -1) {
                Playlist playlist = playlistModel.getElementAt(selectedIndex);
                showPlaylistSongs(playlist);
            }
        }));

        panel.add(playlistScrollPane, BorderLayout.CENTER);
        panel.add(playlistButtonPanel, BorderLayout.SOUTH);
        panel.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width - panel.getPreferredSize().width - 40, searchPanel.getPreferredSize().height, panel.getPreferredSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - searchPanel.getPreferredSize().height * 4);
        return panel;
    }

    private void showPlaylistSongs(Playlist playlist) {
        JDialog dialog = new JDialog((Frame) null, "Playlist: " + playlist.getName(), true);
        dialog.setLayout(new BorderLayout());
    
        DefaultListModel<Song> songModel = new DefaultListModel<>();
        JList<Song> songList = new JList<>(songModel);
        songList.setCellRenderer(new SongCellRenderer());
    
        for (Song song : playlist.getSongs()) {
            songModel.addElement(song);
        }
    
        JScrollPane songScrollPane = new JScrollPane(songList);
        dialog.add(songScrollPane, BorderLayout.CENTER);
    
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dialog.dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
    
        dialog.setSize(300, 400);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }

    private JPanel createUserPanel() {
        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
            }
        };
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder("User"));
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

        panel.add(usernameLabel);
        panel.add(addButton, BorderLayout.EAST);
        Dimension prefSize = panel.getPreferredSize();
        panel.setBounds(this.titleLabel.getPreferredSize().width+10, 20, prefSize.width, prefSize.height);
        return panel;
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

        songPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    // Right-clicked
                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem addToPlaylistItem = new JMenuItem("Add to Playlist");
                    addToPlaylistItem.addActionListener(event -> {
                        addToPlaylist(song);
                        playlistPanel.revalidate();
                        playlistPanel.repaint();
                        // drawCustom();
                    });
                    popupMenu.add(addToPlaylistItem);
                    popupMenu.show(songPanel, e.getX(), e.getY());
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    // Left-clicked
                    String recordingPath = song.getRecordingPath();
                    if (recordingPath != null && !recordingPath.isEmpty()) {
                        try {
                            if (AppFrame.this.clip != null && AppFrame.this.clip.isRunning()) {
                                AppFrame.this.clip.stop();
                                AppFrame.this.clip.close();
                            }
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
            }
        });

        return songPanel;
    }

    private void addToPlaylist(Song song) {
        ArrayList<Playlist> playlists = activeUser.getPlaylists();
        
        JComboBox<Playlist> playlistComboBox = new JComboBox<>(playlists.toArray(new Playlist[0]));
        playlistComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Playlist) {
                    Playlist playlist = (Playlist) value;
                    return super.getListCellRendererComponent(list, playlist.getName(), index, isSelected, cellHasFocus);
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        
        int result = JOptionPane.showConfirmDialog(this, playlistComboBox, "Choose Playlist", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            Playlist selectedPlaylist = (Playlist) playlistComboBox.getSelectedItem();
            if (selectedPlaylist != null) {
                selectedPlaylist.addSong(song);
                JOptionPane.showMessageDialog(this, "Song added to playlist: " + selectedPlaylist.getName(), "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No playlist selected!", "Error", JOptionPane.ERROR_MESSAGE);
            }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == skipButton) {
            handleSkip();
        } else if (source == pauseButton) {
            handlePause();
        } else if (source == returnButton) {
            handleReturn();
        } else if (source == searchButton) {
            handleSearch();
        } else if (source == addButton) {
            handleAddSong();
        }
    }

    private void handleSkip() {
        // Implement the logic to skip to the next song
        if (clip != null) {
            clip.stop();
            long clipLength = this.clip.getMicrosecondLength();
            this.clip.setMicrosecondPosition(clipLength);
            System.out.println("Skipped to the next song");
        }
    }

    private void handlePause() {
        // Implement the logic to pause or resume the song
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
                pauseButton.setIcon(playIcon); // Change to play icon when paused
                System.out.println("Song paused");
            } else {
                clip.start();
                pauseButton.setIcon(pauseIcon); // Change to pause icon when playing
                System.out.println("Song playing");
            }
        }
    }

    private void handleReturn() {
        // Implement the logic to return to the beginning of the song
        if (clip != null) {
            clip.setMicrosecondPosition(0);
            if (!clip.isRunning()) {
                clip.start();
                pauseButton.setIcon(pauseIcon); // Ensure icon shows pause if starting playback
            }
            System.out.println("Returned to the beginning of the song");
        }
    }

    private void handleSearch() {
        // Implement the logic to perform a search
        String searchText = searchField.getText();
        if (!searchText.isEmpty()) {
            // Logic to search for songs based on searchText
            System.out.println("Search performed for: " + searchText);
            // Example: Clear the song list panel and add search results
        }
    }

    private void handleAddSong() {
        String songName = JOptionPane.showInputDialog("Enter playlist name:");
        String songAutor = JOptionPane.showInputDialog("Enter playlist author:");

    }

    void setActiveUser(Spotify_user user) {
        this.activeUser = user;
    }

    public void drawCustom() {
        if (this.activeUser != null) {
            this.setTitle(this.activeUser.getName());
        }
        drawSongs();
        // remove(this.playlistPanel);
        // JPanel playlistPanel = createPlaylistPanel();
        // this.add(playlistPanel);
        // this.playlistPanel = playlistPanel;
    }

    public void drawSongs() {
        this.remove(this.scrollPane);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
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
        scrollPane.setBounds(screenSize.width / 2 - prefSize.width / 2, searchprefSize.height, prefSize.width,
                screenSize.height - searchprefSize.height * 6);
        this.add(scrollPane);
        this.scrollPane = scrollPane;
    }

    
    private class PlaylistCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Playlist) {
                Playlist playlist = (Playlist) value;
                setText(playlist.getName());
            }
            return this;
        }
    }

    private class SongCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Song) {
                Song song = (Song) value;
                setText(song.getName());
            }
            return this;
        }
    }
}

