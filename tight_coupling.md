## What is Tight Coupling?

**Tight coupling** means:

> One class is **highly dependent** on another class’s **implementation details**.

So if **Class A changes**, **Class B is likely to break**.


---

## Real-Life Analogy

Imagine:

* Your **TV remote works only with one specific TV model**
* If you change the TV → remote becomes useless

That’s **tight coupling**.

---

## Java Example: Tight Coupling (Bad Design)

```java
class EmailService {
    public void sendEmail(String message) {
        System.out.println("Sending Email: " + message);
    }
}

class NotificationService {
    private EmailService emailService = new EmailService();

    public void notifyUser(String message) {
        emailService.sendEmail(message);
    }
}
```

### Why is this tightly coupled?

* `NotificationService` **directly creates** `EmailService`
* You **cannot switch** to SMS or Push notifications
* Any change in `EmailService` affects `NotificationService`

❌ Violates **Open–Closed Principle**
❌ Hard to test (can’t mock easily)

---

## What Happens If Requirements Change?

> “Now notifications should be sent via SMS”

You must:

* Modify `NotificationService`
* Possibly rewrite logic
* Re-test everything

This is **bad design**.

---

## Loosely Coupled Version (Good Design)

```java
interface NotificationChannel {
    void send(String message);
}

class EmailService implements NotificationChannel {
    public void send(String message) {
        System.out.println("Sending Email: " + message);
    }
}

class SMSService implements NotificationChannel {
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

class NotificationService {
    private NotificationChannel channel;

    public NotificationService(NotificationChannel channel) {
        this.channel = channel;
    }

    public void notifyUser(String message) {
        channel.send(message);
    }
}
```

### Why is this loosely coupled?

✔ Depends on **interface**, not implementation
✔ Easy to add new notification types
✔ Easy to test
✔ No modification needed for extension

---

## Tight Coupling vs Loose Coupling

| Aspect         | Tight Coupling | Loose Coupling          |
| -------------- | -------------- | ----------------------- |
| Dependency     | Concrete class | Interface / abstraction |
| Change impact  | High           | Low                     |
| Flexibility    | Low            | High                    |
| Testability    | Hard           | Easy                    |
| Design Quality | Poor           | Clean                   |