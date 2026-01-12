# Template Method Design Pattern (Behavioral DP)

## What Problem Does Template Method Solve?

It solves the problem of:

* When an **algorithm has a fixed sequence of steps**
* But **some steps vary** depending on implementation
* And you want to **avoid code duplication**

> Template Method Pattern defines the skeleton of an algorithm in a base class and lets subclasses override specific steps without changing the overall structure.

📌 Key idea: **Algorithm structure is fixed, details vary**

---

## Real-Life Analogy

🍳 **Cooking Recipe**

* Steps are fixed (prepare → cook → serve)
* Ingredients or cooking style can vary

---

## ML Model Training Pipeline (Perfect Example)

### Common Steps (Same for all models)

1. Load data
2. Preprocess data
3. Train model
4. Evaluate model

The **order never changes**, but **implementation differs**.

---

## Template Method – Clean Design

### Step 1: Abstract Base Class (Template)

```java
abstract class MLTrainingPipeline {

    // Template method (final to prevent override)
    public final void trainModel() {
        loadData();
        preprocessData();
        train();
        evaluate();
    }

    protected abstract void loadData();
    protected abstract void preprocessData();
    protected abstract void train();

    // Optional hook
    protected void evaluate() {
        System.out.println("Evaluating model using default metrics");
    }
}
```

---

### Step 2: Concrete Implementation – Linear Regression

```java
class LinearRegressionPipeline extends MLTrainingPipeline {

    protected void loadData() {
        System.out.println("Loading CSV data for Linear Regression");
    }

    protected void preprocessData() {
        System.out.println("Normalizing features");
    }

    protected void train() {
        System.out.println("Training Linear Regression model");
    }
}
```

---

### Step 3: Concrete Implementation – Neural Network

```java
class NeuralNetworkPipeline extends MLTrainingPipeline {

    protected void loadData() {
        System.out.println("Loading image data");
    }

    protected void preprocessData() {
        System.out.println("Scaling and augmenting images");
    }

    protected void train() {
        System.out.println("Training Neural Network model");
    }

    protected void evaluate() {
        System.out.println("Evaluating using accuracy and loss");
    }
}
```

---

### Step 4: Client Code

```java
public class Main {
    public static void main(String[] args) {

        MLTrainingPipeline lrPipeline = new LinearRegressionPipeline();
        lrPipeline.trainModel();

        System.out.println();

        MLTrainingPipeline nnPipeline = new NeuralNetworkPipeline();
        nnPipeline.trainModel();
    }
}
```

---

## Why `trainModel()` is `final` (Interview Insight)

✔ Prevents subclasses from changing algorithm order
✔ Guarantees pipeline consistency

---

## Hook Method (Important Interview Concept)

* A method with **default implementation**
* Subclasses may override it optionally

Example:

```java
protected void evaluate() {
    // default behavior
}
```

---

## How Template Method Helps

✔ Eliminates code duplication
✔ Enforces algorithm structure
✔ Supports controlled extension
✔ Follows **Open–Closed Principle**

---

## When to Use Template Method?

Use it when:

* Algorithm steps are fixed
* Subclasses customize specific steps
* Code reuse is important

---

## When NOT to Use

❌ Too much inheritance
❌ Steps vary heavily or dynamically

---