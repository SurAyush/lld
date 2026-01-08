# Builder Design Pattern

## Definition

> **Builder Pattern separates the construction of a complex object from its representation, allowing it to be built step by step.**

Simpler:

> **It is used when an object has many optional parameters and constructors become unmanageable.**

* ✔ Too many optional fields → constructor explosion
* ✔ Builder class associated with the target class
* ✔ Builder is usually a **static inner class**
* ✔ Builder has setter-like methods
* ✔ Methods return `this` (method chaining)
* ✔ Actual class constructor is **private**
* ✔ `build()` (or `create()`) method constructs the object

---

## Problem Without Builder (Constructor Explosion)

```java
User(String name)
User(String name, int age)
User(String name, int age, String email)
User(String name, int age, String email, String phone)
...
```

Problems:

* Hard to read
* Easy to misuse
* Poor maintainability

---

## Builder Pattern Structure

* **Product** → class being built
* **Builder** → static inner class
* **build()** → creates final object
* **Immutable object** (very important)

---

## Java Example

```java
class User {

    private final String name;
    private final int age;
    private final String email;
    private final String phone;

    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
        this.phone = builder.phone;
    }

    public static class Builder {
        private String name;
        private int age = 0; // default
        private String email = "";
        private String phone = "";

        public Builder(String name) {
            this.name = name; // required
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
```

### Client Code

```java
User user = new User.Builder("Alice")
        .age(25)
        .email("a@x.com")
        .build();
```

---

## Why Not: Default Object + Setters?

### ❌ Approach

```java
User user = new User();
user.setName("Alice");
user.setAge(25);
user.setEmail("a@x.com");
```

---

## Problems With Setters (Why Builder Is Better)

### Mutability (Very Important)

Setters make objects **mutable**:

```java
user.setAge(30); // state changes later ❌
```

Problems:

* Thread safety issues

Builder produces **immutable objects**.

---

### Validation Is Harder

Builder can:

* Validate everything in `build()`

Setters:

* Validation scattered
* Order dependent

---

### Builder Pattern separates class logic from object validation logics and requirements

* Follows SRP principle


---

## Key Design Difference (Interview Gold)

| Setters                         | Builder             |
| ------------------------------- | ------------------- |
| Mutable object                  | Immutable object    |
| Partial states allowed          | Always valid object |
| Hard to enforce required fields | Easy to enforce     |
| Not thread-safe                 | Thread-safe         |
| Hard to validate                | Central validation  |
