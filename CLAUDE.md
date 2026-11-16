# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this repo is

A personal Java interview-prep workspace: standalone DSA problems (hashing, heaps, strings, arrays), Java language topics (streams, functional interfaces, collections, threading, reflection), and design-pattern exercises. It is a single Maven module whose only dependencies are Lombok (compile time only) and JUnit 5 (test scope), with no linter. `README.md` has a package-by-topic table.

## Build and run

`pom.xml` targets Java 21 and passes `--enable-preview` to javac. The default JDK on this machine is Corretto 21, so a plain build works:

```bash
mvn compile
```

If Maven ever picks up a different JDK (`invalid target release: 21`), point it at 21:

```bash
JAVA_HOME=$(/usr/libexec/java_home -v 21) mvn compile
```

There is no shared entry point: each problem class is run on its own through its `main` method after compiling:

```bash
java --enable-preview -cp target/classes heap.KthLargest
```

## Tests and coverage

```bash
mvn test                          # all tests, plus a JaCoCo report in target/site/jacoco/index.html
mvn test -Dtest=KthLargestTest    # one test class
mvn verify                        # tests plus the coverage gate (95% line, 90% branch for the whole project)
```

- Tests live in `src/test/java`, in the **same package** as the class under test, named `<Class>Test`. Same package is required because many solution methods are package-private, and because a class in the package named `String` cannot be referenced by qualified name from another package.
- Name exceptions in `org.preparation.designPattern`: `SingletonTest` there is a main class, not a test, so the tests for that package's singletons are `EagerSingletonTest` (for `Singleton` and `TestCase`) and `LazySingletonTest` (for the main class `SingletonTest`).
- `testsupport.Console.capture(...)` (test sources) returns what a block prints to `System.out`; each test class uses it for one smoke test of `main`. `Console.expectedOutput(name)` reads a recorded output from `src/test/resources/expected/` and is used for the three stream classes, whose `main` output is long.
- Surefire's `argLine` is `@{argLine} --enable-preview`; the `@{argLine}` part carries the JaCoCo agent, so keep it when editing the pom.
- Known uncovered code, all unreachable by design: the lines after each thread blocks in `threading.DeadlockDemo`, the reflective `newInstance()` call that always throws in `org.preparation.designPatternSolution.Singleton`, and the `false` outcome of `s1 == s2` in `designPattern.Singleton.main`.

## Runtime notes

No source currently uses a preview feature, so the compiled classes are not preview-marked and `--enable-preview` is optional at runtime; it becomes required only once a file does use one.

## Conventions

- One problem or concept per file: a static solution method that **returns** its result (public or package-private), plus a `main` that runs it on hard-coded input and prints the result. Logic does not live inside `main`, so it can be tested. Follow this pattern for new problems and add a `<Class>Test` next to it.
- Solution methods validate their input and throw `IllegalArgumentException` for `null` arguments and impossible values (`k` outside 1..n for "kth" problems, negative `k`, capacity below 1). "Top k" list methods return fewer elements when `k` exceeds what exists and an empty list for `k = 0`. Exceptions: the stream exercises and functional-interface lambdas do not null-check, and `LongestSubstringWithAtmostKDistinctCharacters` returns 0 for a `null` string.
- Classes that only hold static methods carry `@NoArgsConstructor(access = AccessLevel.PRIVATE)`. Without it the compiler adds an implicit public constructor that nothing calls, and JaCoCo counts that as an uncovered line in every such class. Add the annotation to new problem classes.
- Data classes use Lombok instead of hand-written accessors: `@Data` on `collection.Booking`, `org.preparation.designPattern.Address`, `streams.curiousCoder.model.Employee` and `org.preparation.Employee` (the last two have only `final` fields, so they get a constructor and getters but no setters). The builder exercise classes (`Employee` in `Builder.java`, `User` in `BuilderDemo.java`) use `@Getter` and `@ToString` only; their builders are hand-written because the Builder pattern is the exercise, so do not swap them for `@Builder`. `Employee.getDob()`/`getDoj()` stay hand-written because they return defensive copies.
- `lombok.config` sets `lombok.addLombokGeneratedAnnotation = true`, which is what keeps Lombok-generated methods out of the JaCoCo report. `toString()` output is Lombok's format, `Type(field=value, ...)`.
- Where ties are possible the tie-break is defined and tested: top-k-frequent and k-closest keep the smaller value, `BookingEvents.topKUsers` orders equal totals by user id.
- Packages are informal and inconsistently cased (`String`, `DataStructure`, `problemsWithBunny`, and lowercase class names such as `findFirstOccurrenceInSortedArray`). Put new code in the package that matches the topic rather than renaming existing packages.
- Some topics appear in more than one place (for example, Kth-largest/smallest exists in both `heap/` and `problems/hashing/heap/`, and Singleton in `designPattern/`, `org/preparation/designPattern/` and `org/preparation/designPatternSolution/`). These are separate practice attempts, not duplicates to consolidate.
- `org/preparation/designPattern/` holds exercise versions and `org/preparation/designPatternSolution/` holds the reference solutions. The exercise singletons (`Singleton`, `SingletonTest`) are deliberately breakable (reflection, serialization, clone, and a thread race in `SingletonTest`); their tests assert that they break, so do not "fix" them. The solution `Singleton` is the hardened one.
- `main` is not always in the public class: `org/preparation/designPattern/Singleton.java` runs via its package-private companion `org.preparation.designPattern.TestCase`. Grep for `static void main` rather than assuming the file name.
- `Solution.java` is the name used for ad-hoc scratch files. There are two: one in the default package (`src/main/java/Solution.java`, run as `java -cp target/classes Solution`) and `String/Solution.java` (run as `String.Solution`).
- There are three unrelated `Employee` classes: `org.preparation.Employee` (immutability exercise), `streams.curiousCoder.model.Employee` (sample data for the stream exercises, built by `streams.curiousCoder.util.EmployeeUtil.getEmployeesList()`) and a package-private `Employee` declared inside `org/preparation/designPattern/Builder.java`.
- `org.preparation.designPattern` has package-private top-level classes sharing files with the public ones: `Employee` (in `Builder.java`), `User` (in `BuilderDemo.java`) and `TestCase` (in `Singleton.java`). A new file in that package that declares a class with one of those names will fail to compile with a duplicate-class error.
- `threading.DeadlockDemo.startDeadlock()` leaves two daemon threads blocked for the life of the JVM. That is the point of the demo; they do not stop the JVM or the test run from exiting.
- A few classes are data or helper types with no `main` and cannot be run on their own: `collection.Booking`, `functionalInterface.Greeting`, `org.preparation.Employee`, `org.preparation.designPattern.Address`, `streams.curiousCoder.model.Employee` and `streams.curiousCoder.util.EmployeeUtil`.
- `.idea/` is partly tracked. `.idea/dbnavigator.xml` is rewritten by the IDE and usually shows as modified; that is IDE noise, not part of a code change, so leave it out of commits unless asked.
