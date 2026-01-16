## Facade Pattern

> Facade provides a **simple interface** to a complex subsystem.

> **Facade Pattern hides system complexity and provides a unified, high-level interface to the client.**

The client talks only to the *Facade*, not to all the internal classes.

> Facade Pattern provides a **simplified interface** to a set of interfaces in a subsystem.

---

## 🔧 Java Example – Starting a Computer

### Subsystem Classes (Complex System)

```java
class CPU {
    void freeze() { System.out.println("CPU freezing..."); }
    void execute() { System.out.println("CPU executing..."); }
}

class Memory {
    void load() { System.out.println("Memory loading..."); }
}

class HardDrive {
    void read() { System.out.println("HardDrive reading data..."); }
}
```

---

### Facade Class

```java
class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public ComputerFacade() {
        cpu = new CPU();
        memory = new Memory();
        hardDrive = new HardDrive();
    }

    public void startComputer() {
        cpu.freeze();
        memory.load();
        hardDrive.read();
        cpu.execute();
    }
}
```

---

### Client Code

```java
public class FacadeDemo {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.startComputer();
    }
}
```

---

## 🔑 Key Points for Interviews

✔ Simplifies client usage
✔ Hides internal complexity
✔ Promotes loose coupling
✔ Does NOT change subsystem behavior
✔ Just provides a cleaner entry point

---

## 🤔 Facade vs Command 


| Facade Pattern                          | Command Pattern                             |
| --------------------------------------- | ------------------------------------------- |
| Simplifies access to a system           | Encapsulates a request as an object         |
| Focus: **Interface simplification**     | Focus: **Request & action encapsulation**   |
| Client wants a simple way to use system | Client wants to execute/queue/undo commands |
| Facade does many things internally      | Command represents *one* action             |
