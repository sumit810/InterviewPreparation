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

class KthSmallestTest {

    private static final int[] SAMPLE = {19, 23, 13, 98, 34, 23, 9, 23, 76, 23};

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of("example from main", SAMPLE, 2, 13),
                Arguments.of("k = 1 is the minimum", SAMPLE, 1, 9),
                Arguments.of("k = n is the maximum", SAMPLE, 10, 98),
                Arguments.of("duplicates count separately", SAMPLE, 5, 23),
                Arguments.of("all equal", new int[]{7, 7, 7}, 3, 7),
                Arguments.of("single element", new int[]{7}, 1, 7),
                Arguments.of("negative numbers", new int[]{-1, -5, -3}, 2, -3),
                Arguments.of("extreme values", new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE}, 1, Integer.MIN_VALUE));
    }

    static Stream<Arguments> invalid() {
        return Stream.of(
                Arguments.of("k = 0", new int[]{1, 2, 3}, 0),
                Arguments.of("negative k", new int[]{1, 2, 3}, -1),
                Arguments.of("k larger than the array", new int[]{1, 2}, 3),
                Arguments.of("empty array", new int[]{}, 1),
                Arguments.of("null array", null, 1));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("cases")
    void findsKthSmallest(String name, int[] nums, int k, int expected) {
        int[] original = nums.clone();

        assertEquals(expected, KthSmallest.findKthSmallest(nums, k));
        assertArrayEquals(original, nums, "input must not be modified");
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalid")
    void rejectsInvalidArguments(String name, int[] nums, int k) {
        assertThrows(IllegalArgumentException.class, () -> KthSmallest.findKthSmallest(nums, k));
    }

    @Test
    void mainPrintsResultForSample() throws Exception {
        assertEquals("Smallest 2nd number is : 13", Console.capture(() -> KthSmallest.main(new String[0])));
    }
}
