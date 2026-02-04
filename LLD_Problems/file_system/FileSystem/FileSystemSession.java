package FileSystem;

public class FileSystemSession {
    private final DirectoryNode root;
    private DirectoryNode cwd;

    public FileSystemSession() {
        root = new DirectoryNode("/", null);
        cwd = root;
    }

    public DirectoryNode getRoot() {
        return root;
    }

    public DirectoryNode getCwd() {
        return cwd;
    }

    public void setCwd(DirectoryNode dir) {
        this.cwd = dir;
    }
}
