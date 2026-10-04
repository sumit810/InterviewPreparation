# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this repo is

A personal Java interview-prep workspace: standalone DSA problems (hashing, heaps, strings, arrays), Java language topics (streams, functional interfaces, collections, threading, reflection), and design-pattern exercises. It is a single Maven module with no dependencies and no tests (`src/test/java` is empty).

## Build and run

`pom.xml` targets **Java 21 with `--enable-preview`**. Maven on this machine defaults to JDK 20, which fails with `invalid target release: 21`, so point it at JDK 21:

```bash
JAVA_HOME=$(/usr/libexec/java_home -v 21) mvn compile
```

Every class is run on its own through its `main` method; there is no shared entry point. To run one class after compiling:

```bash
java --enable-preview -cp target/classes heap.KthLargest
```

## Conventions

- One problem or concept per file: a `public static` solution method plus a `main` that runs it on hard-coded input and prints the result. Follow this pattern for new problems.
- Packages are informal and inconsistently cased (`String`, `DataStructure`, `problemsWithBunny`, and lowercase class names such as `findFirstOccurrenceInSortedArray`). Put new code in the package that matches the topic rather than renaming existing packages.
- Some topics appear in more than one place (for example, Kth-largest/smallest exists in both `heap/` and `problems/hashing/heap/`, and Singleton in `designPattern/`, `org/preparation/designPattern/` and `org/preparation/designPatternSolution/`). These are separate practice attempts, not duplicates to consolidate.
- `org/preparation/designPattern/` holds exercise versions and `org/preparation/designPatternSolution/` holds the reference solutions.
- The stream exercises in `streams/curiousCoder/` share sample data from `util/EmployeeUtil.getEmployeesList()` and the `model/Employee` class.
