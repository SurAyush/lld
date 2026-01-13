# State Design Pattern (Behavioral DP)

## What Problem Does State Pattern Solve?

It solves the problem of:

* An object whose behavior **changes based on its internal state**
* Avoiding large **if-else / switch** statements
* Allowing state-specific behavior


> State Pattern allows an object to alter its behavior when its internal state changes, appearing as if the object changed its class.

📌 Key idea: **Behavior varies with state**

---

## Real-Life Analogy

🥤 **Vending Machine**

* No coin → cannot dispense
* Has coin → can select item
* Sold out → cannot accept coins
* Dispensing → item being delivered

Same machine, different behavior.

---

## Without State Pattern (Bad Design)

```java
class VendingMachine {
    String state;

    public void insertCoin() {
        if (state.equals("NO_COIN")) {
            state = "HAS_COIN";
        } else if (state.equals("SOLD_OUT")) {
            System.out.println("Cannot insert coin");
        }
    }
}
```

❌ Too many conditionals
❌ Hard to maintain
❌ Violates Open–Closed Principle

---

## State Pattern – Clean Design

### Step 1: State Interface

```java
interface State {
    void insertCoin();
    void returnCoin();
    void selectItem();
    void dispense();
    void refill(int count);
}
```

---

### Step 2: Context (Vending Machine)

```java
class VendingMachine {

    State noCoinState;
    State hasCoinState;
    State dispenseState;
    State soldOutState;

    State currentState;
    int itemCount;

    public VendingMachine(int count) {
        this.itemCount = count;

        noCoinState = new NoCoinState(this);
        hasCoinState = new HasCoinState(this);
        dispenseState = new DispenseState(this);
        soldOutState = new SoldOutState(this);

        currentState = count > 0 ? noCoinState : soldOutState;
    }

    // Delegate actions to current state
    public void insertCoin() { currentState.insertCoin(); }
    public void returnCoin() { currentState.returnCoin(); }
    public void selectItem() { currentState.selectItem(); }
    public void dispense() { currentState.dispense(); }
    public void refill(int count) { currentState.refill(count); }

    // State transitions
    void setState(State state) {
        this.currentState = state;
    }

    void releaseItem() {
        if (itemCount > 0) {
            itemCount--;
            System.out.println("Item dispensed");
        }
    }
}
```

---

## Concrete States

### NoCoinState

```java
class NoCoinState implements State {
    VendingMachine machine;

    public NoCoinState(VendingMachine machine) {
        this.machine = machine;
    }

    public void insertCoin() {
        System.out.println("Coin inserted");
        machine.setState(machine.hasCoinState);
    }

    public void returnCoin() {
        System.out.println("No coin to return");
    }

    public void selectItem() {
        System.out.println("Insert coin first");
    }

    public void dispense() {
        System.out.println("Payment required");
    }

    public void refill(int count) {
        machine.itemCount += count;
        System.out.println("Machine refilled");
    }
}
```

---

### HasCoinState

```java
class HasCoinState implements State {
    VendingMachine machine;

    public HasCoinState(VendingMachine machine) {
        this.machine = machine;
    }

    public void insertCoin() {
        System.out.println("Coin already inserted");
    }

    public void returnCoin() {
        System.out.println("Coin returned");
        machine.setState(machine.noCoinState);
    }

    public void selectItem() {
        System.out.println("Item selected");
        machine.setState(machine.dispenseState);
    }

    public void dispense() {
        System.out.println("Select item first");
    }

    public void refill(int count) {
        System.out.println("Cannot refill during transaction");
    }
}
```

---

### DispenseState

```java
class DispenseState implements State {
    VendingMachine machine;

    public DispenseState(VendingMachine machine) {
        this.machine = machine;
    }

    public void insertCoin() {
        System.out.println("Please wait, dispensing");
    }

    public void returnCoin() {
        System.out.println("Already dispensing");
    }

    public void selectItem() {
        System.out.println("Already selected");
    }

    public void dispense() {
        machine.releaseItem();
        if (machine.itemCount > 0) {
            machine.setState(machine.noCoinState);
        } else {
            machine.setState(machine.soldOutState);
        }
    }

    public void refill(int count) {
        System.out.println("Cannot refill while dispensing");
    }
}
```

---

### SoldOutState

```java
class SoldOutState implements State {
    VendingMachine machine;

    public SoldOutState(VendingMachine machine) {
        this.machine = machine;
    }

    public void insertCoin() {
        System.out.println("Machine is sold out");
    }

    public void returnCoin() {
        System.out.println("No coin inserted");
    }

    public void selectItem() {
        System.out.println("Sold out");
    }

    public void dispense() {
        System.out.println("No item to dispense");
    }

    public void refill(int count) {
        machine.itemCount += count;
        System.out.println("Machine refilled");
        machine.setState(machine.noCoinState);
    }
}
```

---

## Client Code

```java
public class Main {
    public static void main(String[] args) {

        VendingMachine machine = new VendingMachine(2);

        machine.insertCoin();
        machine.selectItem();
        machine.dispense();

        machine.insertCoin();
        machine.selectItem();
        machine.dispense();

        machine.insertCoin(); // Sold out
        machine.refill(3);
        machine.insertCoin();
    }
}
```

---

## How State Pattern Helps

✔ Eliminates if-else logic
✔ Encapsulates state-specific behavior
✔ Easy to add new states
✔ Follows Open–Closed Principle

---

## State vs Strategy (Very Important)

| State                          | Strategy             |
| ------------------------------ | -------------------- |
| Behavior changes automatically | Chosen by client     |
| Internal transitions           | External selection   |
| Represents condition           | Represents algorithm |

---