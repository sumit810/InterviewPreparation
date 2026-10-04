# Interview Preparation

Java practice code for technical interviews: data structures and algorithms problems, core Java topics and design patterns.

## Topics

| Package | What's in it |
| --- | --- |
| `problems/hashing`, `problems/hashing/heap` | Hashing and sliding-window problems (anagrams, duplicates, longest substring variants) and heap problems (Kth largest/smallest/closest, top K frequent, frequency sort, connect ropes) |
| `heap` | Kth largest/smallest and top-K problems using `PriorityQueue` |
| `String` | Anagram checks and grouping, permutations, string reversal |
| `problemsWithBunny` | Array problems: duplicates, segregating even/odd, sorting 0s/1s/2s, first occurrence in a sorted array |
| `DataStructure` | LRU cache |
| `streams` | Java Stream API interview questions, using a shared sample employee dataset |
| `functionalInterface` | Custom and built-in functional interfaces, lambdas |
| `collection` | Collections examples: `Set` behaviour, top K users by total booking amount |
| `threading` | Deadlock demo, printing letters and numbers alternately from two threads |
| `designPattern`, `org/preparation/designPattern` | Singleton and Builder exercises |
| `org/preparation/designPatternSolution` | Reference solutions for the design-pattern exercises |
| `org/preparation` | Immutable classes and breaking immutability with reflection |

## Requirements

- JDK 21 (the build uses `--enable-preview`)
- Maven 3.x

## Build and run

```bash
mvn compile
```

If Maven picks up an older JDK, point it at JDK 21. On macOS:

```bash
JAVA_HOME=$(/usr/libexec/java_home -v 21) mvn compile
```

Each class is a standalone program with its own `main` method that runs the solution on sample input. Run one with:

```bash
java --enable-preview -cp target/classes heap.KthLargest
```

You can also open the project in IntelliJ IDEA and run any class directly.
