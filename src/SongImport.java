import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class SongImport {
    String recordingPath, IconPath, songName;
    Connection connection = null;
    int id;

    public SongImport(String recordingPath, String IconPath) {
        this.recordingPath = recordingPath;
        this.IconPath = IconPath;
    }

    public void ImportSong(String songName) {
        this.songName = songName;
        String[] record_extensions = { "mp3" };
        FileExporter(this.recordingPath, songName, record_extensions);
        String[] icon_extensions = { "png", "jpg" };
        FileExporter(this.IconPath, songName, icon_extensions);
        AddSongDatabase();
    }

    public static void FileExporter(String copyPath, String name, String[] extensions) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Compatible files", extensions);
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            String extension = filePath.substring(filePath.lastIndexOf('.') + 1);
            String currentWorkingDir = System.getProperty("user.dir");
            System.out.println("Selected file: " + filePath);
            try {
                Path source = Paths.get(filePath);
                Path destination = Paths.get(currentWorkingDir + copyPath + "/" + name + "." + extension);
                Files.copy(source, destination);
                System.out.println("File copied successfully.");
            } catch (IOException e) {
                System.err.println("Error copying file: " + e.getMessage());
            }
        }
    }

    public void AddSongDatabase() {
        try {
            DatabaseConnection newCon = new DatabaseConnection();
            Connection connection = newCon.MakeConnection();

            String in_query2 = "SELECT COUNT(*) FROM song_data";

            Statement stmt = connection.createStatement();

            ResultSet resultSet = stmt.executeQuery(in_query2);

            while (resultSet.next()) {

                this.id = resultSet.getInt("COUNT(*)") + 1;
            }

            String insert_query = "INSERT INTO SONG_DATA VALUES ("
                    + this.id + ", '" + this.songName + "', '" + "recordings" + "/" + this.songName + ".mp3"
                    + "', '" + "assets" + "/" + this.songName + ".jpg')";
            System.out.println(insert_query);

            PreparedStatement prepstat = connection.prepareStatement(insert_query);

            int rowsInserted = prepstat.executeUpdate();

            insert_query = "INSERT INTO SONG VALUES ("
                    + this.id + ", '" + this.songName + "', '" + 100 +
                    "', '" + this.id + "', '" + 200 + "')";

            prepstat = connection.prepareStatement(insert_query);

            rowsInserted = prepstat.executeUpdate();

            if (rowsInserted > 1) {
                System.out.println("G");
            } else {
                System.out.println("Nie G");
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
