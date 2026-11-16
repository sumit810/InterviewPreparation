import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import testsupport.Console;

/** Tests the thread-safe counter in the default-package Solution class. */
class SolutionTest {

    @RepeatedTest(5)
    void twoThreadsNeverLoseAnIncrement() {
        assertEquals(200_000, assertTimeoutPreemptively(Duration.ofSeconds(10),
                () -> Solution.countConcurrently(2, 100_000)));
    }

    @ParameterizedTest(name = "{0} threads x {1} increments = {2}")
    @CsvSource({"1, 1000, 1000", "8, 25000, 200000", "16, 1, 16", "0, 1000, 0", "4, 0, 0", "0, 0, 0"})
    void countIsThreadsTimesIncrements(int threads, int increments, int expected) {
        assertEquals(expected, assertTimeoutPreemptively(Duration.ofSeconds(10),
                () -> Solution.countConcurrently(threads, increments)));
    }

    @Test
    void everyCallStartsFromZero() throws Exception {
        assertEquals(300, Solution.countConcurrently(3, 100));
        assertEquals(300, Solution.countConcurrently(3, 100));
    }

    @ParameterizedTest
    @CsvSource({"-1, 10", "2, -1", "-1, -1"})
    void rejectsNegativeArguments(int threads, int increments) {
        assertThrows(IllegalArgumentException.class, () -> Solution.countConcurrently(threads, increments));
    }

    @Test
    void mainPrintsTheSameTotalEveryTimeItRuns() throws Exception {
        assertEquals("200000", Console.capture(() -> Solution.main(new String[0])));
        assertEquals("200000", Console.capture(() -> Solution.main(new String[0])));
    }
}
