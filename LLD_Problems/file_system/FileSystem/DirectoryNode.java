package FileSystem;

import java.util.*;

public class DirectoryNode implements FileSystemComponent {
    private final String name;
    private final Map<String, FileSystemComponent> children = new HashMap<>();
    private DirectoryNode parent;

    public DirectoryNode(String name, DirectoryNode parent) {
        this.name = name;
        this.parent = parent;
    }

    public FileSystemComponent getChild(String name) {
        return children.get(name);
    }

    public void addChild(FileSystemComponent node) {
        if (children.containsKey(node.getName()))
            throw new IllegalArgumentException("Already exists: " + node.getName());
        children.put(node.getName(), node);
    }

    public void removeChild(String name) {
        if (!children.containsKey(name))
            throw new IllegalArgumentException("Not found: " + name);
        children.remove(name);
    }

    public DirectoryNode getParent() {
        return parent;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public double getSize() {
        double size = 0.0;
        for(FileSystemComponent f: children.values())
            size += f.getSize();
        return size;
    }

    @Override
    public void ls(int depth) {
        System.out.println("---".repeat(depth) + name + "/");
        for (FileSystemComponent c : children.values()) {
            c.ls(depth + 1);
        }
    }

    public Map<String, FileSystemComponent> getChildren() {
        return children;
    }
}
