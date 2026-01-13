# Memento Design Pattern (Behavioral DP)

## What Problem Does Memento Pattern Solve?

It solves the problem of:

* **Saving and restoring an object’s state**
* Without exposing its internal implementation
* Useful for **undo / rollback**

> Memento Pattern captures and externalizes an object’s internal state so it can be restored later without violating encapsulation.

📌 Key idea: **Snapshot + Restore**

---

## Real-Life Analogy

💾 **Database Transaction**

* Begin transaction
* Make changes
* Commit or Rollback

Rollback restores **previous consistent state**.

---

## Core Components

| Component  | Role                       |
| ---------- | -------------------------- |
| Originator | Object whose state changes |
| Memento    | Snapshot of state          |
| Caretaker  | Manages mementos           |

---

## Memento Pattern – Clean Design

### Step 1: Memento (Snapshot)

```java
class TransactionMemento {
    private final String state;

    public TransactionMemento(String state) {
        this.state = state;
    }

    String getState() {
        return state;
    }
}
```

✔ State is immutable
✔ No setters

---

### Step 2: Originator (Transaction Manager)

```java
class TransactionManager {
    private String currentState;

    public void execute(String operation) {
        currentState = operation;
        System.out.println("Executing: " + operation);
    }

    public TransactionMemento save() {
        return new TransactionMemento(currentState);
    }

    public void restore(TransactionMemento memento) {
        currentState = memento.getState();
        System.out.println("Rolled back to: " + currentState);
    }
}
```

---

### Step 3: Caretaker (Transaction Controller)

```java
import java.util.Stack;

class TransactionController {
    private Stack<TransactionMemento> history = new Stack<>();

    public void save(TransactionMemento memento) {
        history.push(memento);
    }

    public TransactionMemento getLast() {
        return history.pop();
    }
}
```

---

### Step 4: Client Code

```java
public class Main {
    public static void main(String[] args) {

        TransactionManager manager = new TransactionManager();
        TransactionController controller = new TransactionController();

        manager.execute("INSERT INTO users");
        controller.save(manager.save());

        manager.execute("UPDATE users");
        controller.save(manager.save());

        manager.execute("DELETE FROM users");

        // rollback
        manager.restore(controller.getLast());
    }
}
```

---

## How Memento Pattern Helps

✔ Supports rollback / undo
✔ Preserves encapsulation
✔ Clean separation of concerns
✔ Useful for state recovery

---

## UML-Level Understanding (Simple)

```
Caretaker → Memento ← Originator
```

Caretaker:

* Stores mementos
* Does NOT inspect contents

---

## Important Interview Insight

✔ Caretaker **should not modify** memento
✔ Only originator can access internal state

---

## When to Use Memento Pattern?

Use it when:

* You need undo/rollback
* State restoration is required
* State should not be exposed

Examples:

* Text editors
* Transactions
* Game checkpoints

---

## When NOT to Use

❌ Large object states (memory overhead)
❌ High-frequency state changes

---

## Memento vs Command (Very Common Question)

| Memento               | Command                 |
| --------------------- | ----------------------- |
| Stores state          | Stores action           |
| Rollback via snapshot | Undo via reverse action |
| State-based           | Action-based            |

---

## Interview Questions You Might Get

**Q: Is memento mutable?**
✔ No, should be immutable.

**Q: Who owns memento?**
✔ Originator creates it.

---