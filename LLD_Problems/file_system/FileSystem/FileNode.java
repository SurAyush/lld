package FileSystem;

public class FileNode implements FileSystemComponent {
    private final String name;
    private String content;
    private double size;

    public FileNode(String name) {
        this.name = name;
        this.content = "";
        this.size = 0;
    }

    @Override
    public String getName() {
        return name;
    }

    public void write(String text) {
        this.content = text;
        this.size = text.length();
    }

    public String read() {
        return content;
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public double getSize() {
        return size;
    }

    @Override
    public void ls(int depth) {
        System.out.println("---".repeat(depth) + name);
    }
}
