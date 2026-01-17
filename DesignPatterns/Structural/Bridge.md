## Bridge Pattern


> “Each concept is broken into two parts: High-level (abstraction) and low-level (implementation)”

> Bridge decouples an abstraction from its implementation so both can vary independently.

> “HLP has-a relationship with LLP”

> Bridge Pattern separates abstraction from implementation so that both can change independently.

---

## 🔴 Problem Without Bridge (Class Explosion)

You have:

• Car types = `m` → Sedan, SUV, Hatchback
• Engine types = `n` → Petrol, Diesel, Electric

❌ Without Bridge, you’d create classes like:

```
PetrolSedan
DieselSedan
ElectricSedan

PetrolSUV
DieselSUV
ElectricSUV

PetrolHatchback
DieselHatchback
ElectricHatchback
```

➡️ Total classes = **m × n**
This does NOT scale.

---

## 🟢 Solution With Bridge Pattern

Split into two hierarchies:

### 1️⃣ Low-Level Part (Implementation) → Engines

```java
interface Engine {
    void start();
}
```

```java
class PetrolEngine implements Engine {
    public void start() {
        System.out.println("Starting Petrol Engine");
    }
}

class DieselEngine implements Engine {
    public void start() {
        System.out.println("Starting Diesel Engine");
    }
}

class ElectricEngine implements Engine {
    public void start() {
        System.out.println("Starting Electric Motor");
    }
}
```

---

### 2️⃣ High-Level Part (Abstraction) → Cars

```java
abstract class Car {
    protected Engine engine;   // HAS-A

    protected Car(Engine engine) {
        this.engine = engine;
    }

    abstract void drive();
}
```

```java
class Sedan extends Car {
    public Sedan(Engine engine) {
        super(engine);
    }

    public void drive() {
        engine.start();
        System.out.println("Driving Sedan");
    }
}

class SUV extends Car {
    public SUV(Engine engine) {
        super(engine);
    }

    public void drive() {
        engine.start();
        System.out.println("Driving SUV");
    }
}
```

---

## 🟡 Resulting Classes

• Car side = `m` classes
• Engine side = `n` classes

➡️ Total = **m + n** ✔
Instead of **m × n** ❌

---

## 👨‍💻 Client Code

```java
public class BridgeDemo {
    public static void main(String[] args) {

        Engine petrol = new PetrolEngine();
        Engine electric = new ElectricEngine();

        Car sedanWithPetrol = new Sedan(petrol);
        Car suvWithElectric = new SUV(electric);

        sedanWithPetrol.drive();
        suvWithElectric.drive();
    }
}
```

---

## 🎯 Interview Summary

> Without Bridge → m×n class explosion
> With Bridge → m + n flexible combinations
> Abstraction (Car) has-a Implementation (Engine)
> Bridge separates *what you do* from *how you do it*.

---

## 🔑 Key Interview Points

✔ Decouples abstraction from implementation
✔ Avoids class explosion
✔ Both sides can evolve independently
✔ Uses composition over inheritance

---

## 🤝 Bridge vs Strategy – Clear Differentiation

They *look similar* because both use composition, but they solve **different problems**:

| Aspect      | Bridge                                | Strategy                  |
| ----------- | ------------------------------------- | ------------------------- |
| Category    | Structural                            | Behavioral                |
| Purpose     | Separate abstraction & implementation | Swap algorithms           |
| Focus       | Object structure                      | Object behavior           |
| What varies | Both abstraction & implementor        | Only algorithm            |
| Use case    | Multiple dimensions of change         | One dimension (algorithm) |

---

### 🔍 Example Difference

• **Bridge**
Remote ↔ Device
Two independent hierarchies

• **Strategy**
Sort ↔ SortingAlgorithm
Only behavior changes

---

**Bridge:**

> Decouples abstraction from implementation.

**Strategy:**

> Encapsulates interchangeable algorithms.

---