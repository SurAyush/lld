## What are Behavioral Design Patterns?

**Behavioral Design Patterns** focus on **how objects interact and communicate with each other**.

They:

* Define **responsibilities between objects**
* Help manage **complex control flow**
* Reduce **tight coupling** between objects
* Make systems **flexible, maintainable, and extensible**

> In short:
> **Behavioral = how objects talk and behave**

---

## When Do We Need Behavioral Patterns?

Use them when:

* Logic is spread across multiple objects
* You want to avoid large `if-else` or `switch` statements
* Behavior should change **at runtime**
* You want to **decouple sender and receiver**
* You want cleaner **separation of concerns**

---

## Key Problems They Solve

| Problem                 | Solution                      |
| ----------------------- | ----------------------------- |
| Tight coupling          | Use interfaces & delegation   |
| Hard-coded behavior     | Encapsulate behavior          |
| Complex workflows       | Define communication patterns |
| Runtime behavior change | Strategy / State              |
| One-to-many updates     | Observer                      |

---

## Common Behavioral Design Patterns (Important for Interviews)

| Pattern                     | Purpose                                        |
| --------------------------- | ---------------------------------------------- |
| **Strategy**                | Choose algorithm at runtime                    |
| **Observer**                | Notify dependent objects automatically         |
| **Command**                 | Encapsulate a request as an object             |
| **State**                   | Change behavior based on internal state        |
| **Template Method**         | Define skeleton of algorithm                   |
| **Chain of Responsibility** | Pass request along handlers                    |
| **Mediator**                | Centralize object communication                |
| **Iterator**                | Traverse collection without exposing structure |
| **Memento**                 | Capture & restore object state                 |
| **Visitor**                 | Add operations without modifying objects       |
| **Interpreter**             | Interpret language grammar                     |

---

## High-Level Characteristics

✔ Focus on **behavior, not structure**
✔ Use **interfaces & abstraction heavily**
✔ Encourage **Open–Closed Principle**
✔ Reduce **conditional complexity**

---

## Example Patterns vs Real-Life

| Pattern                 | Real-Life Example           |
| ----------------------- | --------------------------- |
| Strategy                | Payment method selection    |
| Observer                | YouTube notifications       |
| Command                 | Remote control buttons      |
| State                   | Vending machine             |
| Chain of Responsibility | Customer support escalation |
| Mediator                | Air traffic control         |

---