# Visitor Design Pattern (Behavioral DP)

## 1. What Problem Does Visitor Pattern Solve?

It solves the problem of:

* Performing **multiple operations** on a group of related objects
* **Without modifying** their classes
* When new operations are added frequently

> Visitor Pattern lets you define new operations on objects without changing their classes by moving the operation logic into a separate visitor.

📌 Key idea: **Operations change more often than object structure**

---

## Problem Without Visitor

```java
class TextFile {
    int fileSize() { ... }
    void virusScan() { ... }
}
```

Now add:

* Compression
* Encryption
* Metadata extraction

❌ You must modify **every file class**
❌ Violates Open–Closed Principle

---

## Visitor Pattern – Clean Design

### Step 1: Element Interface (File)

```java
interface FileElement {
    void accept(FileVisitor visitor);
}
```

---

### Step 2: Concrete Elements

```java
class TextFile implements FileElement {
    public void accept(FileVisitor visitor) {
        visitor.visit(this);
    }

    public int fileSize() {
        return 10;
    }
}

class ImageFile implements FileElement {
    public void accept(FileVisitor visitor) {
        visitor.visit(this);
    }

    public int fileSize() {
        return 200;
    }
}

class VideoFile implements FileElement {
    public void accept(FileVisitor visitor) {
        visitor.visit(this);
    }

    public int fileSize() {
        return 1000;
    }
}
```

---

### Step 3: Visitor Interface

```java
interface FileVisitor {
    void visit(TextFile file);
    void visit(ImageFile file);
    void visit(VideoFile file);
}
```

---

### Step 4: Concrete Visitors

#### File Size Visitor

```java
class FileSizeVisitor implements FileVisitor {

    public void visit(TextFile file) {
        System.out.println("Text file size: " + file.fileSize());
    }

    public void visit(ImageFile file) {
        System.out.println("Image file size: " + file.fileSize());
    }

    public void visit(VideoFile file) {
        System.out.println("Video file size: " + file.fileSize());
    }
}
```

---

#### Virus Scan Visitor

```java
class VirusScanVisitor implements FileVisitor {

    public void visit(TextFile file) {
        System.out.println("Scanning text file for viruses");
    }

    public void visit(ImageFile file) {
        System.out.println("Scanning image file for viruses");
    }

    public void visit(VideoFile file) {
        System.out.println("Scanning video file for viruses");
    }
}
```

---

### Step 5: Client Code

```java
public class Main {
    public static void main(String[] args) {

        List<FileElement> files = List.of(
                new TextFile(),
                new ImageFile(),
                new VideoFile()
        );

        FileVisitor sizeVisitor = new FileSizeVisitor();
        FileVisitor virusVisitor = new VirusScanVisitor();

        for (FileElement file : files) {
            file.accept(sizeVisitor);
            file.accept(virusVisitor);
        }
    }
}
```

---

## How Visitor Pattern Works (Key Insight)

✔ `accept(visitor)` calls `visitor.visit(this)`
✔ This enables **double dispatch**
✔ Correct method is chosen at runtime based on:

* Visitor type
* File type

---

## When to Use Visitor Pattern?

Use it when:

* You have **stable object structure**
* You frequently add **new operations**
* You want to avoid modifying existing classes

---

## When NOT to Use

❌ Object hierarchy changes frequently
❌ Too many element types

---

## Visitor Pattern Trade-Off (Important)

| Adding New | Easy | Hard |
| ---------- | ---- | ---- |
| Operation  | ✔    | ❌    |
| Element    | ❌    | ✔    |

---

## Visitor vs Strategy (Interview Trap)

| Visitor                | Strategy         |
| ---------------------- | ---------------- |
| Multiple operations    | Single algorithm |
| Object structure aware | Context-driven   |
| Uses double dispatch   | Single dispatch  |

---

## Real-World Examples ⭐

* Compilers (AST traversal)
* File system operations
* Static code analyzers

---