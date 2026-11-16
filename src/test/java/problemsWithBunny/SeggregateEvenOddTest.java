package problemsWithBunny;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testsupport.Console;

class SeggregateEvenOddTest {

    static Stream<Arguments> inputs() {
        return Stream.of(
                Arguments.of("example from main", new int[]{12, 34, 45, 9, 8, 90, 3}),
                Arguments.of("empty", new int[]{}),
                Arguments.of("single even", new int[]{4}),
                Arguments.of("single odd", new int[]{7}),
                Arguments.of("all even", new int[]{2, 4, 6, 8}),
                Arguments.of("all odd", new int[]{1, 3, 5, 7}),
                Arguments.of("already segregated", new int[]{2, 4, 1, 3}),
                Arguments.of("odds first", new int[]{1, 3, 2, 4}),
                Arguments.of("alternating", new int[]{1, 2, 3, 4, 5, 6}),
                Arguments.of("zero counts as even", new int[]{1, 0, 3, 0}),
                Arguments.of("duplicates", new int[]{3, 3, 2, 2, 3, 2}),
                Arguments.of("negative odd numbers only", new int[]{-1, -3}),
                Arguments.of("negative odd numbers, same value", new int[]{-1, -1}),
                Arguments.of("negative odd and even mixed", new int[]{-1, 2, -3, 4, -6, 5}),
                Arguments.of("negative even numbers", new int[]{-2, 1, -4, 3}),
                Arguments.of("extreme values", new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, -1, 0}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("inputs")
    void putsEveryEvenNumberBeforeEveryOddNumber(String name, int[] input) {
        int[] original = input.clone();

        int[] result = assertTimeoutPreemptively(Duration.ofSeconds(2),
                () -> SeggregateEvenOdd.seggregateEvenOdd(input));

        assertSame(input, result, "works in place and returns the same array");
        assertEvensThenOdds(result);
        assertSameElements(original, result);
    }

    @Test
    void sampleInputGivesTheDocumentedArrangement() {
        assertArrayEquals(new int[]{12, 34, 90, 8, 9, 45, 3},
                SeggregateEvenOdd.seggregateEvenOdd(new int[]{12, 34, 45, 9, 8, 90, 3}));
    }

    @Test
    void rejectsNullArray() {
        assertThrows(IllegalArgumentException.class, () -> SeggregateEvenOdd.seggregateEvenOdd(null));
    }

    @Test
    void mainPrintsSegregatedSample() throws Exception {
        assertEquals("[12, 34, 90, 8, 9, 45, 3]", Console.capture(() -> SeggregateEvenOdd.main(new String[0])));
    }

    private static void assertEvensThenOdds(int[] nums) {
        boolean seenOdd = false;
        for (int num : nums) {
            boolean odd = num % 2 != 0;
            assertTrue(odd || !seenOdd, "even number after an odd one in " + Arrays.toString(nums));
            seenOdd = seenOdd || odd;
        }
    }

    private static void assertSameElements(int[] expected, int[] actual) {
        int[] a = expected.clone();
        int[] b = actual.clone();
        Arrays.sort(a);
        Arrays.sort(b);
        assertArrayEquals(a, b, "no element may be lost or invented");
    }
}
