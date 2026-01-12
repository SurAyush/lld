# Mediator Design Pattern (Behavioral DP)

## What Problem Does Mediator Pattern Solve?

It solves the problem of:

* **Many-to-many communication** between objects
* Objects becoming **tightly coupled** by talking to each other directly
* Complex interaction logic spread across multiple classes

---

> Mediator Pattern defines an object that encapsulates how a set of objects interact, promoting loose coupling by preventing direct communication between them.

📌 Key idea: **Objects talk to the mediator, not to each other**

---

## Real-Life Analogy

💬 **Chat Room**

* Users don’t send messages directly to other users
* Messages go through a **ChatRoom (Mediator)**
* ChatRoom decides how and to whom messages are delivered

---

## 4. Bad Design (Without Mediator – Tight Coupling)

```java
class User {
    public void sendMessage(User user, String msg) {
        user.receiveMessage(msg);
    }

    public void receiveMessage(String msg) {
        System.out.println(msg);
    }
}
```

### Problems ❌

* Users know each other
* Hard to add features (broadcast, mute, logging)
* Tight coupling
* Hard to maintain

---

## Mediator Pattern – Clean Design

### Step 1: Mediator Interface

```java
interface ChatMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}
```

---

### Step 2: Concrete Mediator (Chat Room)

```java
import java.util.ArrayList;
import java.util.List;

class ChatRoom implements ChatMediator {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public void sendMessage(String message, User sender) {
        for (User user : users) {
            if (user != sender) {
                user.receiveMessage(message, sender);
            }
        }
    }
}
```

---

### Step 3: Colleague (User)

```java
abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void sendMessage(String message);
    public abstract void receiveMessage(String message, User sender);
}
```

---

### Step 4: Concrete User

```java
class ChatUser extends User {

    public ChatUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    public void sendMessage(String message) {
        System.out.println(name + " sends: " + message);
        mediator.sendMessage(message, this);
    }

    public void receiveMessage(String message, User sender) {
        System.out.println(name + " received from " + sender.name + ": " + message);
    }
}
```

---

### Step 5: Client Code

```java
public class Main {
    public static void main(String[] args) {

        ChatMediator chatRoom = new ChatRoom();

        User alice = new ChatUser(chatRoom, "Alice");
        User bob = new ChatUser(chatRoom, "Bob");
        User charlie = new ChatUser(chatRoom, "Charlie");

        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        chatRoom.addUser(charlie);

        alice.sendMessage("Hi everyone!");
    }
}
```

---

## 6. How Mediator Pattern Helps

✔ Eliminates direct object-to-object communication
✔ Centralizes interaction logic
✔ Loose coupling
✔ Easier to extend (logging, filtering, mute users)

---


## When to Use Mediator Pattern?

Use it when:

* Many objects communicate with each other
* Interaction logic becomes complex
* You want to centralize communication

Examples:

* Chat systems
* Air traffic control
* UI dialog controllers
* Event coordination systems

---

## When NOT to Use

❌ Simple interactions don’t need mediator

---

## Mediator vs Observer

| Mediator             | Observer           |
| -------------------- | ------------------ |
| Many-to-many         | One-to-many        |
| Central coordination | Notification only  |
| Logic in mediator    | Logic in observers |

---
