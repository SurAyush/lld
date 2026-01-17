## Flyweight Pattern

> “Saves memory by splitting into intrinsic and extrinsic properties”

> **Flyweight Pattern reduces memory usage by sharing common (intrinsic) state between many objects, and keeping varying (extrinsic) state outside.**

- **Intrinsic state** → shared, immutable
- **Extrinsic state** → passed in from outside

✔ Flyweights are created and cached using a **Factory (usually HashMap)**

> Flyweight Pattern uses sharing to support large numbers of fine-grained objects efficiently.

---

## 🔧Flyweight Example – Text Rendering System

### Step 1: Flyweight Interface

```java
interface Glyph {
    void draw(int x, int y);  // extrinsic: position
}
```

---

### Step 2: Concrete Flyweight (Intrinsic State Stored)

```java
class CharacterGlyph implements Glyph {

    private final char symbol;     // intrinsic
    private final String font;     // intrinsic
    private final int size;        // intrinsic

    public CharacterGlyph(char symbol, String font, int size) {
        this.symbol = symbol;
        this.font = font;
        this.size = size;
    }

    public void draw(int x, int y) {   // x,y = extrinsic
        System.out.println(
            "Draw '" + symbol + "' with " + font + " size " + size +
            " at (" + x + "," + y + ")"
        );
    }
}
```

---

### Step 3: Flyweight Factory (HashMap Cache)

```java
import java.util.*;

class GlyphFactory {

    private static final Map<String, Glyph> cache = new HashMap<>();

    public static Glyph getGlyph(char c, String font, int size) {
        String key = c + "_" + font + "_" + size;

        if (!cache.containsKey(key)) {
            cache.put(key, new CharacterGlyph(c, font, size));
        }

        return cache.get(key);
    }
}
```

---

### Step 4: Client Code

```java
public class FlyweightDemo {
    public static void main(String[] args) {

        Glyph g1 = GlyphFactory.getGlyph('A', "Arial", 12);
        Glyph g2 = GlyphFactory.getGlyph('A', "Arial", 12);
        Glyph g3 = GlyphFactory.getGlyph('A', "Times", 12);

        System.out.println(g1 == g2); // true (shared)
        System.out.println(g1 == g3); // false (different intrinsic state)

        g1.draw(10, 20);
        g2.draw(30, 40);
        g3.draw(50, 60);
    }
}
```

---

## 🧩 Where Flyweight is Used

✔ Text editors
✔ Graphics (pixels, trees in games)
✔ Caching objects
✔ String pool in Java

---

## 🔑 Key Interview Points

✔ Saves memory
✔ Uses factory + cache (HashMap)
✔ Intrinsic = shared
✔ Extrinsic = provided at runtime

---