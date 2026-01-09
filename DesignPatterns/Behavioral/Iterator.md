# Iterator Design Pattern (Behavioral DP)

## What Problem Does Iterator Pattern Solve?

It solves the problem of:

* Traversing a collection
* **Without exposing its internal structure**
* And without writing traversal logic again and again
 

> Iterator Pattern provides a way to sequentially access elements of a collection without exposing its underlying representation.

---

## Real-Life Analogy

📺 **TV Remote**

* You press *Next*
* You don’t know how channels are stored
* You just move one by one

---

## Bad Design (Without Iterator)

```java
class BookShelf {
    Book[] books;

    public Book[] getBooks() {
        return books; // exposes internal structure ❌
    }
}
```

### Problems ❌

* Client depends on internal data structure
* Changing array → list breaks client code
* Tight coupling

---

## Iterator Pattern – Clean Design

### Step 1: Iterator Interface

```java
interface Iterator<T> {
    boolean hasNext();
    T next();
}
```

---

### Step 2: Aggregate Interface

```java
interface Aggregate<T> {
    Iterator<T> createIterator();
}
```

---

### Step 3: Concrete Aggregate

```java
import java.util.List;

class BookShelf implements Aggregate<Book> {
    private List<Book> books;

    public BookShelf(List<Book> books) {
        this.books = books;
    }

    public Iterator<Book> createIterator() {
        return new BookIterator(books);
    }
}
```

---

### Step 4: Concrete Iterator

```java
class BookIterator implements Iterator<Book> {
    private List<Book> books;
    private int index = 0;

    public BookIterator(List<Book> books) {
        this.books = books;
    }

    public boolean hasNext() {
        return index < books.size();
    }

    public Book next() {
        return books.get(index++);
    }
}
```

---

### Step 5: Usage

```java
public class Main {
    public static void main(String[] args) {

        List<Book> books = List.of(
                new Book("Clean Code"),
                new Book("Design Patterns"),
                new Book("Refactoring")
        );

        BookShelf shelf = new BookShelf(books);
        Iterator<Book> iterator = shelf.createIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next().getName());
        }
    }
}
```

---

## How Iterator Pattern Helps

✔ Hides internal structure
✔ Loose coupling
✔ Single Responsibility Principle
✔ Multiple traversal strategies possible

---


## Java Real-World Example ⭐

* `java.util.Iterator`
* `List.iterator()`
* `Set.iterator()`

Example:

```java
Iterator<Integer> it = list.iterator();
```

---

## External vs Internal Iterator (Interview Favorite)

### 1. External Iterator

> An external iterator is one where the client controls the iteration process using methods like `hasNext()` and `next()`.

---

### Java Examples

#### Example 1: Using `Iterator`

```java
List<Integer> numbers = List.of(1, 2, 3);

Iterator<Integer> it = numbers.iterator();
while (it.hasNext()) {
    System.out.println(it.next());
}
```

#### Example 2: for-each loop (Syntactic Sugar)

```java
for (Integer num : numbers) {
    System.out.println(num);
}
```

✔ for-each internally uses `Iterator`
✔ Client controls iteration flow
✔ **Still an external iterator**

---

#### Key Characteristics

* Iterator object is exposed
* Client decides when to move to next element
* Easy to break early
* Classic **Iterator Design Pattern**

---

### 2. Internal Iterator

> An internal iterator hides the traversal logic and allows the client to only specify what operation to perform on each element.

---

### Java Examples

#### Example 1: `forEach()`

```java
numbers.forEach(num -> System.out.println(num));
```

#### Example 2: Java Streams

```java
numbers.stream()
       .filter(n -> n % 2 == 0)
       .forEach(System.out::println);
```

✔ No `hasNext()` or `next()`
✔ Collection controls iteration
✔ Client provides behavior only

---

#### Key Characteristics

* Traversal logic hidden
* No direct control over iteration
* Cleaner, functional style
* Can support parallel execution (Streams)

---

## When to Use Iterator Pattern?

Use it when:

* Collection structure should be hidden
* Multiple traversal logic needed
* You want uniform traversal for different collections

---

## When NOT to Use

❌ Simple collections with no special traversal
❌ Performance-critical code with overhead concerns

---


## 13. Interview Questions You Might Get

**Q: Why not expose collection directly?**
✔ Breaks encapsulation.

**Q: Is Java Iterator thread-safe?**
✔ No (fail-fast behavior).

**Q: What is fail-fast?**
✔ Throws `ConcurrentModificationException`.

---