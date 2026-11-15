# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this repo is

A personal Java interview-prep workspace: standalone DSA problems (hashing, heaps, strings, arrays), Java language topics (streams, functional interfaces, collections, threading, reflection), and design-pattern exercises. It is a single Maven module with no dependencies, no tests (`src/test/java` is empty) and no linter. `README.md` has a package-by-topic table.

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

No source currently uses a preview feature, so the compiled classes are not preview-marked and `--enable-preview` is optional at runtime; it becomes required only once a file does use one.

## Conventions

- One problem or concept per file: a `public static` solution method plus a `main` that runs it on hard-coded input and prints the result. Follow this pattern for new problems.
- Packages are informal and inconsistently cased (`String`, `DataStructure`, `problemsWithBunny`, and lowercase class names such as `findFirstOccurrenceInSortedArray`). Put new code in the package that matches the topic rather than renaming existing packages.
- Some topics appear in more than one place (for example, Kth-largest/smallest exists in both `heap/` and `problems/hashing/heap/`, and Singleton in `designPattern/`, `org/preparation/designPattern/` and `org/preparation/designPatternSolution/`). These are separate practice attempts, not duplicates to consolidate.
- `org/preparation/designPattern/` holds exercise versions and `org/preparation/designPatternSolution/` holds the reference solutions.
- `main` is not always in the public class: `org/preparation/designPattern/Singleton.java` runs via its package-private companion `org.preparation.designPattern.TestCase`. Grep for `static void main` rather than assuming the file name.
- `Solution.java` is the name used for ad-hoc scratch files, including one in the default package (`src/main/java/Solution.java`, run as `java -cp target/classes Solution`).
- There are two unrelated `Employee` classes: `org.preparation.Employee` (immutability exercise) and `streams.curiousCoder.model.Employee` (sample data for the stream exercises, built by `streams.curiousCoder.util.EmployeeUtil.getEmployeesList()`).
