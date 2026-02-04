package FileSystem;

public interface FileSystemComponent {
    String getName();
    boolean isFile();
    double getSize();
    void ls(int depth);
}
