# Creational Design Patterns

> **Creational design patterns are a category of design patterns that focus on how objects are created, abstracting and encapsulating the instantiation process to make a system independent of how its objects are created, composed, and represented.**
---


## Why Do We Need Creational Design Patterns?

```java
Car car = new Sedan();
```

Issues:
- Client depends on concrete classes
- Changes of object creation logic ripple across the codebase
- Violates:
  - **OCP** (Open/Closed Principle)
  - **DIP** (Dependency Inversion Principle)

---

## What Creational Patterns Do

- Hide **object creation logic**
- Delegate responsibility of creation
- Make systems easier to extend and test

---

## Key Goals of Creational Design Patterns

1. **Reduce coupling**
2. **Encapsulate object creation**
3. **Control object lifecycle**
4. **Support complex object construction**
5. **Improve code readability and maintainability**

## Types of Creational Design Patterns

| Pattern | Core Intent |
|------|------------|
| **Singleton** | Ensure only one instance exists |
| **Factory Method** | Let subclasses decide which object to create |
| **Abstract Factory** | Create families of related objects |
| **Builder** | Construct complex objects step-by-step |
| **Prototype** | Create objects by cloning |

---

## When to Use Creational Design Patterns

Use them when:
- Object creation logic is complex
- You need to decouple client code from concrete implementations
- Objects require multiple configuration steps
- You want to control instance count or lifecycle

---

## When NOT to Use Them

Avoid overusing when:
- Object creation is simple
- There’s no foreseeable change
- Added abstraction complicates understanding

---

## Relationship With SOLID Principles

| Principle | How Creational Patterns Help |
|--------|------------------------------|
| SRP | Separate creation from usage |
| OCP | Add new types without modifying client |
| DIP | Depend on abstractions |

---

