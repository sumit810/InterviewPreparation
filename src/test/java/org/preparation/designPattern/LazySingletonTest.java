package org.preparation.designPattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import testsupport.Console;

/**
 * Tests the main class that is itself named SingletonTest: a lazy singleton that is deliberately not
 * thread-safe. Only its single-threaded behaviour is asserted; under concurrency it may or may not
 * create two instances, so nothing is asserted about how many it creates there.
 */
class LazySingletonTest {

    @Test
    void repeatedCallsFromOneThreadReturnTheSameInstance() {
        SingletonTest first = SingletonTest.getInstance();

        assertNotNull(first);
        assertSame(first, SingletonTest.getInstance());
    }

    @Test
    void mainWaitsForBothTasksAndEachPrintsItsInstance() {
        String output = assertTimeoutPreemptively(Duration.ofSeconds(15),
                () -> Console.capture(() -> SingletonTest.main(new String[0])));

        List<String> objectLines = List.of(output.split("\n")).stream()
                .filter(line -> line.matches("Object : singletonTest, Hashcode: -?\\d+"))
                .collect(Collectors.toList());
        assertEquals(2, objectLines.size(), output);
    }
}
