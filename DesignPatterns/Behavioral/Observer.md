# Observer Design Pattern (Behavioral DP)

## What Problem Does Observer Pattern Solve?

It solves the problem of:

* **One-to-many dependency**
* When **one object changes state**
* **All dependent objects should be notified automatically**

**Without tight coupling**

> Observer Pattern defines a one-to-many relationship where multiple observers are notified automatically when the subject’s state changes.

**“Loose coupling between subject and observers.”**

---

## Real-Life Analogy

**YouTube Channel**

* Channel → **Subject**
* Subscribers → **Observers**
* When a video is uploaded → subscribers get notified automatically

---

## Bad Design (Without Observer – Tight Coupling)

```java
class YouTubeChannel {
    public void uploadVideo() {
        System.out.println("Video uploaded");

        // tightly coupled
        notifyUserA();
        notifyUserB();
        notifyUserC();
    }

    private void notifyUserA() {}
    private void notifyUserB() {}
    private void notifyUserC() {}
}
```

### Problems ❌

* Channel knows every subscriber
* Adding/removing subscribers = modifying code
* Highly coupled
* Not scalable

---

## Observer Pattern – Clean Design

### Step 1: Observer Interface

```java
interface Subscriber {
    void update(String message);    // to notify the observer
}
```

---

### Step 2: Concrete Observer

```java
class User implements Subscriber {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public void update(String message) {
        System.out.println(name + " received notification: " + message);
    }
}
```

---

### Step 3: Subject Interface

```java
interface Channel {
    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);
    void notifySubscribers();
}
```

---

### Step 4: Concrete Subject

```java
import java.util.ArrayList;
import java.util.List;

class YouTubeChannel implements Channel {
    private List<Subscriber> subscribers = new ArrayList<>();

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifySubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.update("New video uploaded!");
        }
    }

    public void uploadVideo() {
        System.out.println("Channel uploaded a new video");
        notifySubscribers();
    }
}
```

---

### Step 5: Usage

```java
public class Main {
    public static void main(String[] args) {

        YouTubeChannel channel = new YouTubeChannel();

        Subscriber user1 = new User("Alice");
        Subscriber user2 = new User("Bob");

        channel.subscribe(user1);
        channel.subscribe(user2);

        channel.uploadVideo();
    }
}
```

---

## How Observer Pattern Helps

✔ Loose coupling
✔ Easy to add/remove observers
✔ Follows **Open–Closed Principle**
✔ Scalable event handling

---

## Push vs Pull Model (Interview Favorite)

### Push Model (we used)

### Pull Model

In the Pull Model:

- The Subject does NOT send data to observers
- It only notifies that something changed
- Observers **pull (fetch) the required data** from the subject


> In the Pull Model, observers are notified of a change and then pull the required data from the subject themselves.

---

## When to Use Observer Pattern?

* Event listeners
* Stock price updates
* Notification systems

---

## When NOT to Use

❌ Too many observers → performance issues
❌ Complex dependency chains

---


## Interview Questions You Might Get

**Q: How does Observer reduce tight coupling?**
✔ Subject depends only on observer interface.

**Q: Is Observer synchronous or asynchronous?**
✔ Can be both (depends on implementation).

---
