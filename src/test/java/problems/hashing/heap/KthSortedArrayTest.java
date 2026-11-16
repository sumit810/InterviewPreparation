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

class KthSortedArrayTest {

    /** Every input here is k-sorted: each element is at most k positions from its sorted position. */
    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of("example from main", new int[]{6, 5, 3, 2, 8, 10, 9}, 3, new int[]{2, 3, 5, 6, 8, 9, 10}),
                Arguments.of("k = 1, adjacent swaps", new int[]{2, 1, 4, 3}, 1, new int[]{1, 2, 3, 4}),
                Arguments.of("k = 0 on a sorted array", new int[]{1, 2, 3}, 0, new int[]{1, 2, 3}),
                Arguments.of("k larger than needed", new int[]{2, 1, 4, 3}, 3, new int[]{1, 2, 3, 4}),
                Arguments.of("k = n - 1 sorts any array", new int[]{5, 4, 3, 2, 1}, 4, new int[]{1, 2, 3, 4, 5}),
                Arguments.of("k larger than the array sorts any array", new int[]{3, 1, 2}, 50, new int[]{1, 2, 3}),
                Arguments.of("empty array", new int[]{}, 2, new int[]{}),
                Arguments.of("single element", new int[]{7}, 1, new int[]{7}),
                Arguments.of("duplicates", new int[]{2, 2, 1, 3, 3}, 2, new int[]{1, 2, 2, 3, 3}),
                Arguments.of("negative numbers", new int[]{-1, -3, 0, -2}, 2, new int[]{-3, -2, -1, 0}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("cases")
    void sortsKSortedArrayInPlace(String name, int[] nums, int k, int[] expected) {
        KthSortedArray.KthSortedArray(nums, k);

        assertArrayEquals(expected, nums);
    }

    @Test
    void rejectsNegativeK() {
        assertThrows(IllegalArgumentException.class, () -> KthSortedArray.KthSortedArray(new int[]{1, 2}, -1));
    }

    @Test
    void rejectsNullArray() {
        assertThrows(IllegalArgumentException.class, () -> KthSortedArray.KthSortedArray(null, 1));
    }

    @Test
    void mainPrintsSortedSample() throws Exception {
        assertEquals("[2, 3, 5, 6, 8, 9, 10]", Console.capture(() -> KthSortedArray.main(new String[0])));
    }
}
