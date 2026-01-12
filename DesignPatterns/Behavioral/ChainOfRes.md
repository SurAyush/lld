# Chain of Responsibility Design Pattern

## What Problem Does It Solve?

It solves the problem of:

* A request that can be handled by **multiple objects**
* But you don’t want the sender to know **which object will handle it**
* Or write **large if-else / switch** logic

> Chain of Responsibility passes a request along a chain of handlers until one of them handles it.

📌 Key idea: **Decoupling sender from receiver**

---

## Real-Life Analogy

📞 **Customer Support System**

* Level 1 Support
* Level 2 Support
* Manager

If one level can’t handle the issue → it passes to the next.

---

## Bad Design (Without Chain)

```java
class SupportService {
    public void handleRequest(int level) {
        if (level == 1) {
            System.out.println("Handled by Level 1");
        } else if (level == 2) {
            System.out.println("Handled by Level 2");
        } else if (level == 3) {
            System.out.println("Handled by Manager");
        }
    }
}
```

### Problems ❌

* Too many `if-else`
* Hard to add new handlers
* Tight coupling
* Violates Open–Closed Principle

---

## Chain of Responsibility – Clean Design

### Step 1: Handler Interface / Abstract Class

```java
abstract class SupportHandler {
    protected SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(int level);
}
```

---

### Step 2: Concrete Handlers

```java
class Level1Support extends SupportHandler {
    public void handleRequest(int level) {
        if (level == 1) {
            System.out.println("Handled by Level 1 Support");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(level);
        }
    }
}

class Level2Support extends SupportHandler {
    public void handleRequest(int level) {
        if (level == 2) {
            System.out.println("Handled by Level 2 Support");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(level);
        }
    }
}

class ManagerSupport extends SupportHandler {
    public void handleRequest(int level) {
        if (level == 3) {
            System.out.println("Handled by Manager");
        } else {
            System.out.println("Request cannot be handled");
        }
    }
}
```

---

### Step 3: Build the Chain

```java
public class Main {
    public static void main(String[] args) {

        SupportHandler level1 = new Level1Support();
        SupportHandler level2 = new Level2Support();
        SupportHandler manager = new ManagerSupport();

        level1.setNextHandler(level2);
        level2.setNextHandler(manager);

        level1.handleRequest(2);
    }
}
```

---

## 6. How Chain of Responsibility Helps

✔ Removes if-else logic
✔ Sender doesn’t know the receiver
✔ Easy to add new handlers
✔ Follows **Open–Closed Principle**

---

## When to Use Chain of Responsibility?

Use it when:

* Multiple objects can handle a request
* Handler should be chosen at runtime
* You want loose coupling

Examples:

* Logging frameworks
* Filters / interceptors
* Validation pipelines
* Event handling systems

---

## When NOT to Use

❌ When request must always be handled
❌ Very long chains → performance issues
❌ Hard to debug if chain is complex

---

## Real Java Examples ⭐

* **Spring Interceptors**
* **Logging frameworks (log levels)**

---

## Interview Questions

**Q: What happens if no handler processes the request?**
✔ It can be ignored or throw an exception (design choice).

**Q: Can multiple handlers handle the same request?**
✔ Yes, if designed that way.

**Q: Which SOLID principles does it follow?**
✔ Open–Closed Principle
✔ Single Responsibility Principle

---