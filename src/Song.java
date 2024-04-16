public class Song {
    private String name;
    private String author;
    private String imagePath;
    private String recordingPath;

    public Song(String name, String author, String imagePath,  String recordingPath) {
        this.name = name;
        this.author = author;
        this.imagePath = imagePath;
        this.recordingPath = recordingPath;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getRecordingPath() {
        return recordingPath;
    }
}
