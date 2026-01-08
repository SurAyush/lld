# Prototype Design Pattern

## 1️⃣ Definition (Interview-Ready)

> **Prototype Pattern creates new objects by copying (cloning) an existing object instead of creating them from scratch.**

Shorter:

> **It avoids expensive object creation by cloning a prototype.**

* ✔ Clone from a template instead of creating from scratch
* ✔ Modify only required fields
* ✔ Very common in **game development**
* ✔ Shallow vs deep copy matters
* ✔ `clone()` method is used
* ❌ Prototype is **not just about performance**, also about **decoupling**
* ⚠️ `Cloneable` is optional in modern Java (but still common)
* ✅ Prototype avoids `new` and complex creation logic

---

## Problem It Solves

### ❌ Without Prototype

```java
Character c = new Character();
c.loadTextures();
c.loadWeapons();
c.loadAbilities();
```

Problems:

* Expensive initialization
* Repeated logic
* Tight coupling to concrete classes

---

## Prototype Solution

* Create a **base object**
* Clone it
* Customize only what’s needed

---

## Structure

### Participants

* **Prototype interface** → declares `clone()`
* **ConcretePrototype** → implements cloning
* **Client** → clones instead of `new`

---

## Simple Java Example

---

### 🔹 Step 1: Prototype Interface

```java
interface GameCharacter extends Cloneable {
    GameCharacter clone();
}
```

---

### 🔹 Step 2: Concrete Prototype

```java
class Warrior implements GameCharacter {

    String weapon;
    int strength;

    public Warrior(String weapon, int strength) {
        this.weapon = weapon;
        this.strength = strength;
    }

    @Override
    public Warrior clone() {
        try {
            return (Warrior) super.clone(); // shallow copy
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
```

---

### 🔹 Step 3: Client Usage

```java
Warrior baseWarrior = new Warrior("Sword", 100);

Warrior player1 = baseWarrior.clone();
player1.weapon = "Axe";

Warrior player2 = baseWarrior.clone();
player2.weapon = "Spear";
```

✔ Shared base
✔ Custom variations
✔ No expensive creation

---

## Shallow Copy vs Deep Copy (Important)

### 🔸 Shallow Copy

* Copies primitive values
* Copies **references**, not objects
* Faster
* Risky if mutable fields exist

```java
return (Warrior) super.clone();
```

---

### 🔸 Deep Copy

* Copies referenced objects too
* Safer but slower

```java
@Override
public Warrior clone() {
    Warrior copy = new Warrior(this.weapon, this.strength);
    copy.skills = new ArrayList<>(this.skills);
    return copy;
}
```

---

> “Use shallow copy when referenced objects are immutable; deep copy when they are mutable.”

---

## When to Use Prototype Pattern

* Object creation is expensive
* Objects are similar with small variations
* Many instances required
* Creation logic is complex
* Want to avoid subclass explosion

---

## When NOT to Use

* Simple object creation
* Few instances
* Deep copy is very complex
* Immutable objects with simple constructors

---

## 🔥 Pros & Cons

### ✅ Pros

* Faster object creation
* Less boilerplate
* Decouples client from concrete classes

### ❌ Cons

* Cloning logic can be tricky
* Deep copy complexity
* `Cloneable` has design issues

---

## Common Interview Questions

### ❓ Is Prototype always faster than `new`?

> Not always — depends on cloning complexity.

### ❓ Is `Cloneable` mandatory?

> No. You can implement custom copy methods or copy constructors.

### ❓ Shallow vs Deep copy?

> Depends on mutability of fields.