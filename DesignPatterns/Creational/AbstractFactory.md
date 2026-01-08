# Abstract Factory Design Pattern

## Definition 

> **Abstract Factory provides an interface for creating families of related or dependent objects without specifying their concrete classes.**

Shorter:

> **It creates groups of related objects using multiple factories behind a common interface.**

---

* ✔ “Factory of factories” — **conceptually correct**
* ✔ Delegates creation to **sub-factories**
* ✔ Useful in **large systems**
* ❌ Not only for *very large* codebases
* ✅ Used when **multiple related objects must be created together**

---

## The Real Problem It Solves

### ❌ Without Abstract Factory

```java
Button button = new WindowsButton();
Checkbox checkbox = new MacCheckbox(); // ❌ inconsistent UI
```

Problems:

* Inconsistent object families
* Client must know concrete classes
* Hard to switch entire families

---

## Abstract Factory Solution

* One factory = one **family**
* Client picks a factory
* Factory creates consistent objects

---

## Structure 

### Participants

* **AbstractFactory** → interface
* **ConcreteFactory** → implements factory
* **AbstractProduct** → interfaces
* **ConcreteProduct** → implementations
* **Client** → uses only interfaces

---

## Java Example (UI Toolkit)

---

### 🔹 Step 1: Abstract Products

```java
interface Button {
    void paint();
}

interface Checkbox {
    void paint();
}
```

---

### 🔹 Step 2: Concrete Products

```java
class WindowsButton implements Button {
    public void paint() {
        System.out.println("Windows Button");
    }
}

class MacButton implements Button {
    public void paint() {
        System.out.println("Mac Button");
    }
}

class WindowsCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Windows Checkbox");
    }
}

class MacCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Mac Checkbox");
    }
}
```

---

### 🔹 Step 3: Abstract Factory

```java
interface UIFactory {
    Button createButton();
    Checkbox createCheckbox();
}
```

---

### 🔹 Step 4: Concrete Factories

```java
class WindowsFactory implements UIFactory {
    public Button createButton() {
        return new WindowsButton();
    }
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

class MacFactory implements UIFactory {
    public Button createButton() {
        return new MacButton();
    }
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}
```

---

### 🔹 Step 5: Client Code

```java
UIFactory factory = new WindowsFactory();
Button button = factory.createButton();
Checkbox checkbox = factory.createCheckbox();

button.paint();
checkbox.paint();
```

✔ Client never touches concrete classes
✔ Family consistency guaranteed

---

## When to Use Abstract Factory

* You need **families of related objects**
* Objects must be **used together consistently**
* You want to switch product families easily
* You want to hide concrete implementations

---

## When NOT to Use

* Only one product
* No related object families
* High chance of frequently adding new product types (painful)

---

## Pros & Cons

### ✅ Pros

* Strong consistency
* Loose coupling
* Easy to switch families

### ❌ Cons

* Adding new product types is hard
* More classes
* More abstraction

---

## 🔥 Factory vs Abstract Factory (Interview Gold)

| Factory Method      | Abstract Factory           |
| ------------------- | -------------------------- |
| Creates one product | Creates families           |
| One factory         | Multiple related factories |
| Easier to extend    | Harder to extend           |

---
