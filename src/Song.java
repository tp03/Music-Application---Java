public class Song {
    private String name;
    private String author;
    private String imagePath;

    public Song(String name, String author, String imagePath) {
        this.name = name;
        this.author = author;
        this.imagePath = imagePath;
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
}
