
# 1️⃣ SOLID Principles (Core of LLD)

SOLID is a **set of 5 object-oriented design principles** that help you build **maintainable, extensible, and testable systems**.

---

## S — Single Responsibility Principle (SRP)

### Definition

> A class should have **only one reason to change**.

### What it really means in LLD

* One class = **one job**
* Changes in one feature should not break unrelated logic

---

### ❌ Bad Example (Violates SRP)

```java
class Invoice {
    void calculateTotal() { }
    void printInvoice() { }
    void saveToDatabase() { }
}
```

---

### ✅ Good Example (SRP Applied)

```java
class Invoice {
    double calculateTotal() { }
}

class InvoicePrinter {
    void print(Invoice invoice) { }
}

class InvoiceRepository {
    void save(Invoice invoice) { }
}
```

### Benefits

* Easier to modify
* Easier to test
* Cleaner separation of concerns

---

## O — Open/Closed Principle (OCP)

### Definition

> Software entities should be **open for extension but closed for modification**.

### Meaning in LLD

* Add new behavior **without changing existing code**
* Use **polymorphism & abstraction**

---

### ❌ Bad Example

```java
class DiscountCalculator {
    double calculate(String type) {
        if (type.equals("FESTIVAL")) return 0.2;
        if (type.equals("SEASONAL")) return 0.1;
        return 0;
    }
}
```

**Problem**

* Every new discount → modify class → bug risk

---

### ✅ Good Example (OCP Applied)

```java
interface Discount {
    double apply(double amount);
}

class FestivalDiscount implements Discount {
    public double apply(double amount) {
        return amount * 0.2;
    }
}

class DiscountCalculator {
    double calculate(Discount discount, double amount) {
        return discount.apply(amount);
    }
}
```

### Benefits

* Easy feature extension
* Less regression bugs

---

## L — Liskov Substitution Principle (LSP)

### Definition

> Subtypes must be substitutable for their base types.

### Meaning

* Child class **must not break parent behavior**
* Avoid unexpected behavior in polymorphism

---

### ❌ Bad Example

```java
class Bird {
    void fly() { }
}

class Penguin extends Bird {
    void fly() {
        throw new UnsupportedOperationException();
    }
}
```

**Problem**

* Penguin is not a flying bird → breaks LSP

---

### ✅ Good Example

```java
interface Bird { }

interface FlyingBird extends Bird {
    void fly();
}

class Sparrow implements FlyingBird {
    public void fly() { }
}

class Penguin implements Bird { }
```

### Benefits

* Reliable polymorphism
* Fewer runtime surprises

---

## I — Interface Segregation Principle (ISP)

### Definition

> Clients should not be forced to depend on methods they do not use.

---

### ❌ Bad Example

```java
interface Worker {
    void work();
    void eat();
}

class Robot implements Worker {
    public void eat() { } // meaningless
}
```

---

### ✅ Good Example

```java
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class Human implements Workable, Eatable { }
class Robot implements Workable { }
```

### Benefits

* Clean interfaces
* Less unnecessary code

---

## D — Dependency Inversion Principle (DIP)

### Definition

> High-level modules should not depend on low-level modules. 
> Both should depend on abstractions.

In LLD, the real problem is tight coupling.
Tight coupling means:
- Changing one class forces changes in many other classes
- Hard to test
- Hard to extend
- Hard to replace implementations

---

### ❌ Bad Example

```java
class EmailService {
    void sendEmail() { }
}

class Notification {
    EmailService email = new EmailService();
}
```

---

### ✅ Good Example

```java
interface MessageService {
    void send();
}

class EmailService implements MessageService {
    public void send() { }
}

class Notification {
    MessageService service;

    Notification(MessageService service) {
        this.service = service;
    }
}
```

### Benefits

* Easy testing (mocking)
* Flexible architecture

---

# 2️⃣ DRY — Don’t Repeat Yourself

### Definition

> Every piece of knowledge must have **a single, unambiguous representation**.

---

### ❌ Bad Example

```java
double calculateTaxA(double price) {
    return price * 0.18;
}

double calculateTaxB(double price) {
    return price * 0.18;
}
```

---

### ✅ Good Example

```java
double calculateTax(double price) {
    return price * 0.18;
}
```

### Where DRY applies

* Business rules
* Validation logic
* Constants
* SQL queries
* Configuration

### Caution ⚠️

❌ Don’t over-abstract unrelated code
DRY ≠ premature abstraction

---

# 3️⃣ KISS — Keep It Simple, Stupid

### Definition

> Simplicity should be a key design goal.

---

### ❌ Over-engineered Example

```java
class CalculatorFactoryManagerStrategyProvider {
    Calculator getCalculator(String type) { }
}
```

---

### ✅ Simple & Clear

```java
class Calculator {
    int add(int a, int b) {
        return a + b;
    }
}
```

### KISS in LLD

* Prefer readable code
* Avoid unnecessary patterns
* Optimize for **clarity first**

---

### When to apply

* Small systems
* Early stages of development
* Non-scalable logic

---

# 4️⃣ YAGNI — You Aren’t Gonna Need It

### Definition

> Do not add functionality until it is actually needed.

---

### ❌ Bad Example

```java
class Payment {
    void pay();
    void refund();
    void cancel();
    void cryptoPay();
}
```

*(Only `pay()` is currently required)*

---

### ✅ YAGNI Applied

```java
class Payment {
    void pay();
}
```

Add other methods **only when required**.

---

### Why YAGNI matters

* Less code → fewer bugs
* Faster development
* Easier maintenance

---

# 🔥 How These Work Together in LLD

| Principle | Focus                   |
| --------- | ----------------------- |
| SOLID     | Structure & flexibility |
| DRY       | Maintainability         |
| KISS      | Simplicity              |
| YAGNI     | Avoid overengineering   |

---

# 🧠 Interview Tip

When designing systems:

1. Start with **KISS**
2. Apply **YAGNI**
3. Refactor using **SOLID**
4. Remove duplication using **DRY**

