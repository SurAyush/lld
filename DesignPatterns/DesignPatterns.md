# 1️⃣ What are Design Patterns?

Design patterns are **reusable, proven solutions to recurring software design problems** in a given context.

### Key Characteristics

- Not code, but templates / blueprints
- Language-independent (Java, C++, Python, etc.)
- Based on real-world experience
- Describe structure and interaction, not implementation details

### Simple Explanation

When developers face the same design problem repeatedly, certain solutions work better than others.
These solutions are documented as design patterns so we don’t **reinvent the wheel**.

### Example

Problem:
You need to create objects, but object creation logic is getting complex.

Solution:
Use a Factory Pattern
➡️ That solution is a design pattern.

>*Design patterns are concrete solutions that apply design principles to solve specific design problems.*

## 🔷 1. Creational Design Patterns – *How objects are created*

👉 Focus: **Object creation mechanisms**

They deal with *how* and *when* objects are created to make systems more flexible.

### Goals:

* Hide object creation logic
* Control how many objects are created
* Improve reuse and decoupling

### Examples:

* Singleton
* Factory / Abstract Factory
* Builder
* Prototype

🧠 Think:

> “How should I create objects?”

---

## 🔷 2. Structural Design Patterns – *How objects are composed*

👉 Focus: **Class and object composition**

They help you build **larger structures from smaller pieces** while keeping things flexible and efficient.

### Goals:

* Combine objects and classes into bigger structures
* Add functionality without modifying existing code
* Reduce tight coupling

### Examples:

* Adapter
* Bridge
* Composite
* Decorator
* Facade
* Flyweight
* Proxy

🧠 Think:

> “How do I connect and organize classes/objects?”

---

## 🔷 3. Behavioral Design Patterns – *How objects communicate*

👉 Focus: **Communication and responsibility**

They define *how objects interact*, share responsibilities, and coordinate behavior.

### Goals:

* Encapsulate behavior
* Reduce complex conditionals
* Improve communication flow between objects

### Examples:

* Observer
* Strategy
* Command
* Chain of Responsibility
* State
* Mediator
* Iterator
* Template Method

🧠 Think:

> “How do objects talk and work together?”

## 🔍 Quick Comparison Table

| Type       | Focus Area         | Main Question It Answers                     |
| ---------- | ------------------ | -------------------------------------------- |
| Creational | Object creation    | How do I create objects?                     |
| Structural | Object composition | How do I organize & combine objects?         |
| Behavioral | Object interaction | How do objects communicate & share behavior? |
