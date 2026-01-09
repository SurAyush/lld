# Strategy Design Pattern (Behavioral DP)

## What Problem Does Strategy Pattern Solve?

It solves the problem of:

* **Multiple algorithms / behaviors**
* Selected **at runtime**
* Without using **large if-else or switch statements**

> Strategy Pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable at runtime.

📌 Key phrase interviewers love:
**“Behavior can be selected at runtime without modifying existing code.”**

---

## Real-Life Analogy

💳 **Payment System**

* Pay via **Credit Card**
* Pay via **UPI**
* Pay via **Net Banking**

Same action → *Pay*,
Different **strategies** → chosen at runtime.

---

## 4. Bad Design (Without Strategy – Tight Coupling)

```java
class PaymentService {

    public void pay(String paymentType, int amount) {
        if (paymentType.equals("CREDIT")) {
            System.out.println("Paid using Credit Card");
        } else if (paymentType.equals("UPI")) {
            System.out.println("Paid using UPI");
        } else if (paymentType.equals("NETBANKING")) {
            System.out.println("Paid using Net Banking");
        }
    }
}
```

### Problems ❌

* Too many `if-else`
* Violates **Open–Closed Principle**
* Adding new payment method = modifying existing code
* Hard to test & maintain

---

## Strategy Pattern – Clean Design

### Step 1: Create Strategy Interface

```java
interface PaymentStrategy {
    void pay(int amount);
}
```

---

### Step 2: Implement Different Strategies

```java
class CreditCardPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}

class UPIPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using UPI");
    }
}

class NetBankingPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Net Banking");
    }
}
```

---

### Step 3: Context Class (Uses Strategy)

```java
class PaymentService {
    private PaymentStrategy paymentStrategy;

    public PaymentService(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void payAmount(int amount) {
        paymentStrategy.pay(amount);
    }
}
```

---

### Step 4: Runtime Strategy Selection

```java
public class Main {
    public static void main(String[] args) {

        PaymentStrategy strategy = new UPIPayment();
        PaymentService service = new PaymentService(strategy);
        service.payAmount(1000);

        // Change strategy at runtime
        strategy = new CreditCardPayment();
        service = new PaymentService(strategy);
        service.payAmount(2000);
    }
}
```

---

## How Strategy Pattern Helps

✔ Removes `if-else` logic
✔ Promotes **Open–Closed Principle**
✔ Supports **runtime behavior change**
✔ Loosely coupled design

---

## When to Use Strategy Pattern?

Use it when:

* You have **multiple ways to perform an action**
* Behavior needs to change **dynamically**
* You want to eliminate conditional logic

---

## When NOT to Use

❌ If only 1 algorithm exists
❌ Too many tiny strategies causing class explosion

---

## Questions

**Q: How is Strategy different from if-else?**
✔ Strategy uses **polymorphism** instead of conditionals.

**Q: Can strategy be changed at runtime?**
✔ Yes.

**Q: Which SOLID principle does it follow?**
✔ Open–Closed Principle
✔ Dependency Inversion Principle
