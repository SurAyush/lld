## Proxy Pattern

> “Proxy class is-a and has-a relationship with actual interface and class”

Proxy:
- **IS-A** → Implements same interface as Real Object
- **HAS-A** → Holds reference to the Real Object

This lets it **control access** to the real object.

> **Proxy Pattern provides a surrogate or placeholder for another object to control access to it.**

---

## 🔧 Java Example – Protection Proxy

### Step 1: Subject Interface

```java
interface Document {
    void read();
}
```

---

### Step 2: Real Subject

```java
class RealDocument implements Document {
    public void read() {
        System.out.println("Reading the document...");
    }
}
```

---

### Step 3: Proxy

```java
class DocumentProxy implements Document {

    private RealDocument realDocument;
    private String userRole;

    public DocumentProxy(String userRole) {
        this.userRole = userRole;
    }

    public void read() {
        if ("ADMIN".equals(userRole)) {
            if (realDocument == null) {
                realDocument = new RealDocument();  // Lazy init
            }
            realDocument.read();
        } else {
            System.out.println("Access denied!");
        }
    }
}
```

---

### Step 4: Client

```java
public class ProxyDemo {
    public static void main(String[] args) {
        Document adminDoc = new DocumentProxy("ADMIN");
        adminDoc.read();

        Document userDoc = new DocumentProxy("USER");
        userDoc.read();
    }
}
```

---

## 🧩 Types of Proxy

### 1️⃣ Virtual Proxy

👉 Delays creation of expensive objects

```java
if (realImage == null)
    realImage = new RealImage();
```

Used for:
• Lazy loading images
• Big objects

---

### 2️⃣ Protection Proxy

👉 Controls access (security)

Used for:
• Role-based access
• Permissions

---

### 3️⃣ Remote Proxy

👉 Represents object in another JVM / machine

Used for:
• RMI
• gRPC / REST client wrappers

---

## Key Interview Points

- Same interface as real object
- Controls access
- Can add logging, security, caching
- Supports lazy loading

---
