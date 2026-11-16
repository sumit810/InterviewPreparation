package problems.hashing.heap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testsupport.Console;

class KthClosetTest {

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of("example from main", new int[]{5, 6, 7, 8, 9}, 3, 7, List.of(6, 7, 8)),
                Arguments.of("equal distances keep the smaller value", new int[]{1, 2, 3, 4, 5}, 4, 3, List.of(1, 2, 3, 4)),
                Arguments.of("equal distances, k = 1", new int[]{4, 6}, 1, 5, List.of(4)),
                Arguments.of("equal distances, larger value first in input", new int[]{6, 4}, 1, 5, List.of(4)),
                Arguments.of("x above every element", new int[]{1, 2, 3}, 2, 10, List.of(2, 3)),
                Arguments.of("x below every element", new int[]{1, 2, 3}, 2, -10, List.of(1, 2)),
                Arguments.of("x not present in the array", new int[]{1, 5, 10}, 1, 6, List.of(5)),
                Arguments.of("unsorted input", new int[]{9, 5, 8, 6, 7}, 3, 7, List.of(6, 7, 8)),
                Arguments.of("duplicates", new int[]{5, 5, 6}, 2, 5, List.of(5, 5)),
                Arguments.of("k = 0", new int[]{1, 2, 3}, 0, 2, List.of()),
                Arguments.of("k = n returns everything sorted", new int[]{3, 1, 2}, 3, 2, List.of(1, 2, 3)),
                Arguments.of("k larger than the array returns everything", new int[]{3, 1, 2}, 9, 2, List.of(1, 2, 3)),
                Arguments.of("empty array", new int[]{}, 2, 5, List.of()),
                Arguments.of("negative numbers", new int[]{-10, -4, -1, 3}, 2, -3, List.of(-4, -1)),
                Arguments.of("distance from MIN_VALUE to 0 does not wrap",
                        new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE}, 1, 0, List.of(0)),
                Arguments.of("distance wider than int range, x = 1",
                        new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}, 1, 1, List.of(Integer.MAX_VALUE)),
                Arguments.of("distance wider than int range, x = -1",
                        new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}, 1, -1, List.of(Integer.MIN_VALUE)),
                Arguments.of("distance difference wider than int range",
                        new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 5}, 2, Integer.MAX_VALUE,
                        List.of(5, Integer.MAX_VALUE)));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("cases")
    void returnsKClosestInAscendingOrder(String name, int[] nums, int k, int x, List<Integer> expected) {
        int[] original = nums.clone();

        assertEquals(expected, KthCloset.findKClosestNumbers(nums, k, x));
        assertArrayEquals(original, nums, "input must not be modified");
    }

    @Test
    void rejectsNegativeK() {
        assertThrows(IllegalArgumentException.class, () -> KthCloset.findKClosestNumbers(new int[]{1, 2}, -1, 1));
    }

    @Test
    void rejectsNullArray() {
        assertThrows(IllegalArgumentException.class, () -> KthCloset.findKClosestNumbers(null, 1, 1));
    }

    @Test
    void mainPrintsThreeClosestToSeven() throws Exception {
        assertEquals("[6, 7, 8]", Console.capture(() -> KthCloset.main(new String[0])));
    }
}
