import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SongImport {
    String recordingPath, IconPath;

    public SongImport(String recordingPath, String IconPath) {
        this.recordingPath = recordingPath;
        this.IconPath = IconPath;
    }

    public void ImportSong(String songName) {
        String[] record_extensions = { "mp3" };
        FileExporter(this.recordingPath, songName, record_extensions);
        String[] icon_extensions = { "png", "jpg" };
        FileExporter(this.IconPath, songName, icon_extensions);
    }

    public static void FileExporter(String copyPath, String name, String[] extensions) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Compatible files", extensions);
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            String[] pathsplited = filePath.split("/");
            String extension = pathsplited[pathsplited.length - 1];
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

}
