## Composite Pattern

> **Composite Pattern lets you treat individual objects and groups of objects the same way.**

In a file system:
• A **File** is a leaf
• A **Directory** is a composite
• Both implement the same interface

So the client doesn’t care if it’s a File or Folder — it just calls:

```java
component.getSize();
component.ls();
```


> Composite Pattern composes objects into tree structures and lets clients treat individual objects and compositions uniformly.

---

## 🔧 Java Example – File System Composite

### Step 1: Common Interface

```java
interface FileSystemComponent {
    void ls();
    String getName();
    int getSize();
}
```

---

### Step 2: Leaf – File

```java
class FileLeaf implements FileSystemComponent {
    private String name;
    private int size;

    public FileLeaf(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void ls() {
        System.out.println("File: " + name);
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
}
```

---

### Step 3: Composite – Directory

```java
import java.util.*;

class DirectoryComposite implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> children = new ArrayList<>();

    public DirectoryComposite(String name) {
        this.name = name;
    }

    public void add(FileSystemComponent component) {
        children.add(component);
    }

    public void remove(FileSystemComponent component) {
        children.remove(component);
    }

    public void ls() {
        System.out.println("Directory: " + name);
        for (FileSystemComponent c : children) {
            c.ls();
        }
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        int totalSize = 0;
        for (FileSystemComponent c : children) {
            totalSize += c.getSize();
        }
        return totalSize;
    }
}
```

---

### Step 4: Client Code

```java
public class CompositeDemo {
    public static void main(String[] args) {
        FileSystemComponent file1 = new FileLeaf("a.txt", 10);
        FileSystemComponent file2 = new FileLeaf("b.txt", 20);

        DirectoryComposite folder1 = new DirectoryComposite("docs");
        folder1.add(file1);
        folder1.add(file2);

        DirectoryComposite root = new DirectoryComposite("root");
        root.add(folder1);

        root.ls();
        System.out.println("Total size: " + root.getSize());
    }
}
```

---

## 🔑 Key Interview Points

✔ Treat files and folders uniformly
✔ Tree structure (part-whole hierarchy)
✔ Clients don’t need to know leaf vs composite
✔ Makes recursive structures easy

---