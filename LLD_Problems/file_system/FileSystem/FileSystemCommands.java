package FileSystem;
import java.util.*;

public class FileSystemCommands {
    private final FileSystemSession session;

    public FileSystemCommands(FileSystemSession session) {
        this.session = session;
    }

    public void pwd() {
        DirectoryNode cur = session.getCwd();
        StringBuilder sb = new StringBuilder();
        while (cur != null && cur.getParent() != null) {
            sb.insert(0, "/" + cur.getName());    // prepending
            cur = cur.getParent();
        }
        System.out.println(sb.length() == 0 ? "/" : sb.toString());
    }

    public void ls() {
        session.getCwd().ls(0);
    }

    public void cd(String path) {
        FileSystemComponent node = PathResolver.resolve(session, path);
        if (node.isFile())
            throw new IllegalArgumentException("Not a directory");
        session.setCwd((DirectoryNode) node);
    }

    public void mkdir(String name) {
        session.getCwd().addChild(new DirectoryNode(name, session.getCwd()));
    }

    public void touch(String name) {
        session.getCwd().addChild(new FileNode(name));
    }

    public void cat(String path) {
        FileSystemComponent node = PathResolver.resolve(session, path);
        if (!node.isFile())
            throw new IllegalArgumentException("Not a file");
        System.out.println(((FileNode) node).read());
    }

    public void cp(String src, String dest){
        FileSystemComponent target = PathResolver.resolve(session, dest);
        if(target.isFile()) 
            throw new IllegalArgumentException("Paste can be made to a directory only");
        FileSystemComponent source = PathResolver.resolve(session, src);
        if (source.isFile())
            cp_file((FileNode)source, (DirectoryNode)target); 
        else{
            if(isDescendant((DirectoryNode)source, (DirectoryNode)target))    
                throw new IllegalArgumentException("Will lead to infinte copy");
            cp_folder((DirectoryNode)source, (DirectoryNode)target);
        }
    }

    private boolean isDescendant(DirectoryNode source, DirectoryNode target) {
        DirectoryNode cur = target;
        while (cur != null) {
            if (cur == source) {
                return true; // target is inside source subtree
            }
            cur = cur.getParent();
        }
        return false;
    }

    
    // only supports file copying, to copy entire directories, first go the src, copy of heirarchy of directory and create at dest
    // again go and copy all files
    private void cp_folder(DirectoryNode source, DirectoryNode target){
        DirectoryNode folderCopy = new DirectoryNode(source.getName(), target);
        target.addChild(folderCopy);
        Map<String, FileSystemComponent> children = source.getChildren();
        for(FileSystemComponent f: children.values()){
            if(f.isFile())
                cp_file((FileNode)f, folderCopy);
            else 
                cp_folder((DirectoryNode)f, folderCopy);
        }
    }

    private void cp_file(FileNode source, DirectoryNode target) {
        FileNode copy = new FileNode(source.getName());
        copy.write(source.read());
        target.addChild(copy);
    }

    public void mv(String src, String dest) {
        FileSystemComponent source = PathResolver.resolve(session, src);
        DirectoryNode target = (DirectoryNode) PathResolver.resolve(session, dest);
        DirectoryNode parent = ((DirectoryNode) PathResolver.resolve(session, src.substring(0, src.lastIndexOf('/'))));
        parent.removeChild(source.getName());
        target.addChild(source);
    }
}
