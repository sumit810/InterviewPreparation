package problems.hashing.heap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testsupport.Console;

class ConnectRopesMinimiseCostTest {

    private static final long MAX = Integer.MAX_VALUE;

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of("example from main", new int[]{1, 2, 3, 4, 5}, 33L),
                Arguments.of("classic example", new int[]{4, 3, 2, 6}, 29L),
                Arguments.of("no ropes", new int[]{}, 0L),
                Arguments.of("one rope needs no joining", new int[]{9}, 0L),
                Arguments.of("two ropes", new int[]{4, 3}, 7L),
                Arguments.of("equal lengths", new int[]{2, 2, 2, 2}, 16L),
                Arguments.of("zero-length rope", new int[]{0, 5}, 5L),
                Arguments.of("order of input does not matter", new int[]{5, 4, 3, 2, 1}, 33L),
                Arguments.of("total above int range", new int[]{Integer.MAX_VALUE, 1}, MAX + 1),
                Arguments.of("intermediate rope above int range",
                        new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}, 5 * MAX));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("cases")
    void computesMinimumTotalCost(String name, int[] ropes, long expected) {
        int[] original = ropes.clone();

        assertEquals(expected, ConnectRopesMinimiseCost.connectRopesMinimiseCost(ropes));
        assertArrayEquals(original, ropes, "input must not be modified");
    }

    @Test
    void rejectsNegativeLength() {
        assertThrows(IllegalArgumentException.class,
                () -> ConnectRopesMinimiseCost.connectRopesMinimiseCost(new int[]{3, -1, 2}));
    }

    @Test
    void rejectsNullArray() {
        assertThrows(IllegalArgumentException.class, () -> ConnectRopesMinimiseCost.connectRopesMinimiseCost(null));
    }

    @Test
    void mainPrintsCostForSample() throws Exception {
        assertEquals("33", Console.capture(() -> ConnectRopesMinimiseCost.main(new String[0])));
    }
}
