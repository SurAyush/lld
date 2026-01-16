## Decorator Pattern

> “Decorator inherits the base class and also has-a relationship with it”

Decorator pattern uses **both**:
• **IS-A** → via inheritance / interface
• **HAS-A** → via composition

This allows:
• Wrapping an object
• Adding behavior dynamically
• Without changing the original class

> **Decorator Pattern attaches additional responsibilities to an object dynamically by wrapping it.**

---

## 🔧 Java Example – Coffee with Add-ons

### Step 1: Component Interface

```java
interface Coffee {
    String getDescription();
    int getCost();
}
```

---

### Step 2: Concrete Component

```java
class SimpleCoffee implements Coffee {
    public String getDescription() {
        return "Simple Coffee";
    }

    public int getCost() {
        return 50;
    }
}
```

---

### Step 3: Abstract Decorator

```java
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;   // HAS-A

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
}
```

---

### Step 4: Concrete Decorators

```java
class MilkDecorator extends CoffeeDecorator {

    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    public int getCost() {
        return coffee.getCost() + 10;
    }
}

class SugarDecorator extends CoffeeDecorator {

    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }

    public int getCost() {
        return coffee.getCost() + 5;
    }
}
```

---

### Step 5: Client Code

```java
public class DecoratorDemo {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);

        System.out.println(coffee.getDescription());
        System.out.println("Cost: " + coffee.getCost());
    }
}
```

---

## 🔑 Key Interview Points

✔ Adds behavior dynamically
✔ Uses wrapping
✔ Follows Open/Closed Principle
✔ Avoids class explosion

---

## Where is Decorator Pattern used in real life?

Here are **common real-world / Java examples**:

### Java I/O Streams (Classic Example)

```java
InputStream in =
    new BufferedInputStream(
        new DataInputStream(
            new FileInputStream("data.txt")));
```

✔ `FileInputStream` = base
✔ `BufferedInputStream` = decorator
✔ `DataInputStream` = decorator

Each wrapper adds behavior (buffering, data reading, etc.).

---

### Logging Frameworks

```java
Logger logger = new FileLogger(new ConsoleLogger());
```

Each decorator adds:
• Timestamp
• File output
• Formatting

---

### Security / Validation Layers

```java
Service secure = new AuthDecorator(new RateLimitDecorator(new BaseService()));
```

Each decorator adds:
• Authentication
• Rate limiting
• Logging

---

## 🧱 Class Decorator vs Object Decorator

### 🔹 1. Class Decorator (via Inheritance)

Uses **inheritance only**.

```java
class LoggedService extends BaseService {
    @Override
    void execute() {
        System.out.println("Log before");
        super.execute();
    }
}
```

⚠️ Downside:
• One behavior per subclass
• Leads to class explosion

---

### 🔹 2. Object Decorator (Preferred ✔)

Uses **composition + interface**.

```java
class LoggingDecorator implements Service {
    private Service service;

    public void execute() {
        System.out.println("Log before");
        service.execute();
    }
}
```

✔ Flexible
✔ Stackable
✔ Runtime behavior change

---

