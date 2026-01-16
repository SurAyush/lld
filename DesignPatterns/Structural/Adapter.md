## Adapter Pattern

The **Adapter Pattern** allows two incompatible interfaces to work together.

> It acts like a *plug converter* 🔌 — you have something, the client expects something else, and the adapter converts between them.


> ✔️ **Correct** — Adapter helps integrate 3rd-party / legacy code.


There are **two types**:

1. **Class Adapter** → uses *inheritance* (extends)
2. **Object Adapter** → uses *composition* (has-a)

➡️ In Java interviews, **Object Adapter is preferred**.

You said:

> ✔️ Yes — the adapter wraps the third-party object.

> "Adapter converts formats like XML → JSON". That’s a perfect real-world use case!


> **Adapter Pattern converts the interface of a class into another interface that the client expects, so incompatible classes can work together.**

---

## Java Example – Object Adapter

### Step 1: Client expects this interface

```java
interface PaymentProcessor {
    void pay(int amount);
}
```

---

### Step 2: Third-party / legacy class (incompatible)

```java
class ThirdPartyPaymentGateway {
    public void makePayment(double money) {
        System.out.println("Paid " + money + " using ThirdParty Gateway");
    }
}
```

---

### Step 3: Adapter class

```java
class PaymentAdapter implements PaymentProcessor {

    private ThirdPartyPaymentGateway gateway;

    public PaymentAdapter(ThirdPartyPaymentGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void pay(int amount) {
        // Adapter converts and delegates
        gateway.makePayment((double) amount);
    }
}
```

---

### Step 4: Client Code

```java
public class AdapterDemo {
    public static void main(String[] args) {
        ThirdPartyPaymentGateway thirdParty = new ThirdPartyPaymentGateway();
        PaymentProcessor adapter = new PaymentAdapter(thirdParty);

        adapter.pay(500);
    }
}
```

---

## 🧩 Where Adapter Pattern is Used

✔ Integrating 3rd-party libraries
✔ Legacy code integration
✔ Data format conversion (XML ↔ JSON, CSV ↔ Object)
✔ Wrapping incompatible APIs

---

## 🔑 Key Interview Points

• Adapter makes **incompatible interfaces compatible**
• It uses **composition (preferred)** or **inheritance**
• Promotes **Open/Closed Principle**
• Avoids modifying existing/3rd-party code

---
