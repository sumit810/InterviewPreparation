package org.preparation.designPattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import testsupport.Console;

/**
 * Tests the exercise version of Singleton and its TestCase demo. (The name SingletonTest is already taken
 * by a main class in this package.) The exercise is deliberately breakable, so the "broken by" tests
 * assert that a second instance DOES appear; the hardened class is tested in
 * org.preparation.designPatternSolution.SingletonTest.
 */
class EagerSingletonTest {

    @Test
    void repeatedCallsReturnTheSameInstance() {
        Singleton first = Singleton.getInstance();

        assertNotNull(first);
        assertSame(first, Singleton.getInstance());
    }

    @Test
    void eagerInitialisationIsThreadSafe() throws Exception {
        Set<Singleton> seen = ConcurrentHashMap.newKeySet();
        ExecutorService pool = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 200; i++) {
            pool.submit(() -> seen.add(Singleton.getInstance()));
        }
        pool.shutdown();
        assertTrue(pool.awaitTermination(10, TimeUnit.SECONDS));

        assertEquals(1, seen.size());
    }

    @Test
    void brokenByReflection() throws Exception {
        Singleton viaReflection = TestCase.newInstanceUsingReflection();

        assertNotSame(Singleton.getInstance(), viaReflection);
        assertNotSame(viaReflection, TestCase.newInstanceUsingReflection());
    }

    @Test
    void brokenBySerialization() throws Exception {
        Singleton original = Singleton.getInstance();

        Singleton copy = TestCase.copyUsingSerialization(original);

        assertNotNull(copy);
        assertNotSame(original, copy);
    }

    @Test
    void brokenByClone() throws Exception {
        Singleton original = Singleton.getInstance();

        Singleton copy = TestCase.copyUsingClone(original);

        assertNotNull(copy);
        assertNotSame(original, copy);
    }

    @Test
    void mainShowsOneHashCodeForTheRealInstanceAndADifferentOneForEachViolation() throws Exception {
        String output = Console.capture(() -> TestCase.main(new String[0]));

        Pattern line = Pattern.compile("Object : (s\\d), Hashcode: (-?\\d+)");
        List<String> names = new java.util.ArrayList<>();
        List<String> hashes = new java.util.ArrayList<>();
        for (String printed : output.split("\n")) {
            Matcher matcher = line.matcher(printed);
            if (matcher.matches()) {
                names.add(matcher.group(1));
                hashes.add(matcher.group(2));
            }
        }

        assertEquals(List.of("s1", "s2", "s3", "s4", "s5"), names, output);
        assertEquals(hashes.get(0), hashes.get(1), "s1 and s2 are the same object");
        // identity hash codes of different objects could collide in theory, so only the instances are compared strictly above
        assertNotEquals(1, hashes.stream().collect(Collectors.toSet()).size(), output);
        assertTrue(output.contains("Creating Singleton ..."), "the reflective constructor call prints");
    }
}
