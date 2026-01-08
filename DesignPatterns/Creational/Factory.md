# Factory Design Pattern (Factory Method)

## Definition (Interview-Ready)

> **Factory Method Pattern defines an interface for creating an object, but lets subclasses decide which class to instantiate.**

Simpler:

> **It delegates object creation to a factory instead of the client creating objects directly.**

---


* ✔ Factory creates objects of **related classes**
* ✔ Client does **not use `new`**
* ✔ Object creation logic is **centralized**
* ✔ Easy to maintain and extend
* Factory usually returns an **interface or abstract type**
* Client remains **decoupled from concrete classes**

---

## Problem It Solves

### ❌ Without Factory

```java
if (type.equals("car")) {
    vehicle = new Car();
} else if (type.equals("truck")) {
    vehicle = new Truck();
}
```

Problems:

* Tight coupling
* Violates **OCP**
* Hard to maintain
* Client knows too much

---

## Factory Pattern Solution

* Move creation logic to a factory
* Client depends on abstraction
* New types added with minimal changes


---

## Structure

### Participants

* **Product** → interface
* **ConcreteProduct** → implementations
* **Factory** → creates objects

---

## Java Example (Vehicle Factory)

### 🔹 Step 1: Product Interface

```java
interface Vehicle {
    void drive();
}
```

---

### 🔹 Step 2: Concrete Products

```java
class Car implements Vehicle {
    public void drive() {
        System.out.println("Driving a car");
    }
}

class Truck implements Vehicle {
    public void drive() {
        System.out.println("Driving a truck");
    }
}

class Bike implements Vehicle {
    public void drive() {
        System.out.println("Riding a bike");
    }
}
```

---

### 🔹 Step 3: Factory Class

```java
class VehicleFactory {

    public static Vehicle createVehicle(String type) {

        if (type.equalsIgnoreCase("car")) {
            return new Car();
        } else if (type.equalsIgnoreCase("truck")) {
            return new Truck();
        } else if (type.equalsIgnoreCase("bike")) {
            return new Bike();
        }

        throw new IllegalArgumentException("Unknown vehicle type");
    }
}
```

---

### 🔹 Step 4: Client Code

```java
Vehicle vehicle = VehicleFactory.createVehicle("car");
vehicle.drive();
```

✔ Client doesn’t know **which class is created**

---

## 7️⃣ When to Use Factory Pattern

* Object creation logic is complex
* Many related classes
* Client should not depend on concrete classes
* New types expected in future

---

## 8️⃣ When NOT to Use

* Only one concrete class (unless a single class creation logic is complex and need to be centralized we may use a single class factory)
* No variation in object creation
* Over-engineering risk

---

## 9️⃣ Pros & Cons

### ✅ Pros

* Loose coupling
* Centralized creation logic
* Easy maintenance
* Follows **OCP** and **DIP**

### ❌ Cons

* Extra class
* Can become large if too many types

---

### ❓ How to avoid `if-else` in factory?

> Use map / reflection / registration pattern

