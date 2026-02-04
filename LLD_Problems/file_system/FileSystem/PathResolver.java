package FileSystem;

public class PathResolver {

    public static FileSystemComponent resolve(FileSystemSession session, String path) {
        if (path == null || path.isEmpty())
            throw new IllegalArgumentException("Invalid path");

        DirectoryNode current = path.startsWith("/") ? session.getRoot() : session.getCwd();    // / starts signifies absolute path
        String[] parts = path.split("/");

        for (String p : parts) {
            if (p.isEmpty() || p.equals(".")) continue;        // relative path can be like ./dir or dir
            if (p.equals("..")) {
                if (current.getParent() != null)
                    current = current.getParent();
                continue;
            }
            FileSystemComponent child = current.getChild(p);
            if (child == null)
                throw new IllegalArgumentException("Path not found: " + p);
            if (child.isFile())
                return child;
            current = (DirectoryNode) child;
        }
        return current;
    }
}
