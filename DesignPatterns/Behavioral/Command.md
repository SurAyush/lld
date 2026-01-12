# Command Design Pattern (Behavioral DP)

## What Problem Does Command Pattern Solve?

It solves the problem of:

* Decoupling **invoker** (who triggers an action)
* From **receiver** (who actually performs the action)
* By encapsulating a request as an object

> Command Pattern encapsulates a request as an object, allowing parameterization of clients with different requests and supporting undo/redo operations.

---

## Real-Life Analogy

🏠 **Smart Home Remote**

* Remote → doesn’t know how devices work
* Buttons → trigger commands
* Devices → light, fan, AC

Remote just calls **execute()**.

---

## Core Components

| Component       | Role                          |
| --------------- | ----------------------------- |
| Command         | Interface (`execute`, `undo`) |
| ConcreteCommand | LightOnCommand, FanOffCommand |
| Receiver        | Light, Fan                    |
| Invoker         | RemoteControl                 |
| Client          | Wires everything              |

---

## Without Command Pattern (Bad Design)

```java
class RemoteControl {
    Light light = new Light();
    Fan fan = new Fan();

    public void pressLightOn() {
        light.on();
    }

    public void pressFanOn() {
        fan.on();
    }
}
```

❌ Tight coupling
❌ No undo support
❌ Hard to extend
❌ Violates OCP

---

## Command Pattern – Clean Design

### Step 1: Command Interface

```java
interface Command {
    void execute();
    void undo();
}
```

---

### Step 2: Receivers (Devices)

```java
class Light {
    public void on() {
        System.out.println("Light is ON");
    }

    public void off() {
        System.out.println("Light is OFF");
    }
}

class Fan {
    public void on() {
        System.out.println("Fan is ON");
    }

    public void off() {
        System.out.println("Fan is OFF");
    }
}
```

---

### Step 3: Concrete Commands

```java
class LightCommand implements Command {
    private Light light;

    public LightCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.on();
    }

    public void undo() {
        light.off();
    }
}

class FanCommand implements Command {
    private Fan fan;

    public FanCommand(Fan fan) {
        this.fan = fan;
    }

    public void execute() {
        fan.on();
    }

    public void undo() {
        fan.off();
    }
}
```

---

### Step 4: Invoker (Remote Control)

```java
class RemoteControl {
    private Command command;
    private boolean toggle = true;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        if(toggle){
            command.execute();
        }
        else{
            command.undo();
        }
        toggle = !toggle;
    }
}
```

---

### Step 5: Client Code

```java
public class Main {
    public static void main(String[] args) {

        RemoteControl remote = new RemoteControl();

        Light livingRoomLight = new Light();
        Fan ceilingFan = new Fan();

        Command lightOn = new LightCommand(livingRoomLight);
        Command fanOn = new FanCommand(ceilingFan);

        remote.setCommand(lightOn);
        remote.pressButton(); // Light ON
        remote.pressButton();   // Light OFF

        remote.setCommand(fanOn);
        remote.pressButton(); // Fan ON
        remote.pressButton();   // Fan OFF
    }
}
```

---

## Benefits

✔ Decouples invoker and receiver
✔ Supports undo/redo
✔ Easy to add new commands
✔ Follows Open–Closed Principle

---

## When to Use Command Pattern?

Use it when:

* You want undo/redo support
* You want to queue or log requests
* You want to decouple UI from business logic

Examples:

* GUI buttons
* Job queues
* Transaction systems
