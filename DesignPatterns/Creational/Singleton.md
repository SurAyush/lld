# Singleton Design Pattern

## Definition

> **Singleton Pattern ensures that a class has only one instance and provides a global point of access to that instance.**

---

* Only **one instance** throughout the application
* Used for **logging, configuration, database-related objects**
* Constructor is **private**

### Important
> 🔑 Singleton controls *instance count*, not *thread safety of methods*.

---

## Problem It Solves

Without Singleton:

```java
Logger l1 = new Logger();
Logger l2 = new Logger(); // multiple instances
```

Problems:

* Wasted resources
* Inconsistent state
* Hard to coordinate shared resources

---

## Key Characteristics

* Private constructor
* Static instance reference
* Global access point
* Controlled instantiation

---

## Basic (Lazy) Singleton — ❌ Not Thread-Safe

```java
class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

⚠️ **Problem**: Multiple threads can create multiple instances.

---

## Thread-Safe Singleton Approaches (Java)

### 🔹 1. Synchronized Method (Simple but Slow)

```java
class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

✅ Thread-safe
❌ Performance overhead

---

### 🔹 2. Double-Checked Locking

```java
class Singleton {
    private static volatile Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

✅ Thread-safe
✅ High performance
📌 `volatile` is **mandatory**

> synchronized (Singleton.class) - inside synchronized a object needs to be passed which needs to be locked before entering. className.class is a object representing the class in the JVM. There is a one object for the class. We cannot use 'this' as it a static method. We cannot use 'instance' as in beginning instance is null.

> In the Singleton pattern, the instance variable is declared volatile to prevent threads from using a cached copy of the variable. Without volatile, one thread may create the object while another thread reads an outdated or partially initialized instance from CPU cache due to instruction reordering. volatile forces every thread to read the latest value from main memory, ensuring visibility and thread safety in double-checked locking.

---

### 🔹 3. Eager Initialization

```java
class Singleton {
    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }
}
```

✅ Thread-safe
❌ Instance created even if unused

---

### 🔹 4. Static Inner Class (Best Practice)

```java
class Singleton {

    private Singleton() {
    }

    private static class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return Holder.INSTANCE;
    }
}
```

✅ Lazy
✅ Thread-safe
✅ No synchronization overhead

Static Inner class is loaded by JVM when it is first referenced by Holder.INSTANCE in getInstance()

❌ Problem With simple Static Block
- NOT Lazy
- Instance created when class is loaded, even if getInstance() is never called

---

### 🔹 5. Enum Singleton (Most Robust)

```java
enum Singleton {
    INSTANCE;

    public void log(String msg) {
        System.out.println(msg);
    }
}
```

✅ Thread-safe
⚠️ Less flexible (cannot extend)

---

## When to Use Singleton

* Logging frameworks
* Configuration management
* Thread pools
* Caching
* Device drivers

---

## When NOT to Use Singleton

* When you need multiple instances
* When unit testing requires mocking
* When global state causes hidden dependencies

---

## Pros & Cons

### ✅ Pros

* Controlled access to single instance
* Reduced memory usage
* Centralized resource management

### ❌ Cons

* Global state

---

## 🔥 Common Interview Questions & Traps

### ❓ Is Singleton thread-safe?
> Depends on implementation.

### ❓ Does Singleton prevent race conditions?
> No, it prevents multiple instances, not concurrent access.

### ❓ Best Singleton implementation?
> Static inner class or enum.

### ❓ Can Singleton be broken?

Yes:

* Cloning -- override clone() and disbale it

---