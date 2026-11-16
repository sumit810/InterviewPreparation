package heap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testsupport.Console;

class KthLargestTest {

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of("example from main", new int[]{60, 30, 40, 12, 20, 30}, 3, 30),
                Arguments.of("k = 1 is the maximum", new int[]{60, 30, 40, 12, 20, 30}, 1, 60),
                Arguments.of("k = n is the minimum", new int[]{60, 30, 40, 12, 20, 30}, 6, 12),
                Arguments.of("duplicates count separately", new int[]{5, 5, 4}, 2, 5),
                Arguments.of("all equal", new int[]{7, 7, 7}, 3, 7),
                Arguments.of("single element", new int[]{7}, 1, 7),
                Arguments.of("negative numbers", new int[]{-1, -5, -3}, 2, -3),
                Arguments.of("ascending input", new int[]{1, 2, 3, 4, 5}, 2, 4),
                Arguments.of("descending input", new int[]{5, 4, 3, 2, 1}, 4, 2),
                Arguments.of("extreme values", new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE}, 3, Integer.MIN_VALUE));
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
    void findsKthLargest(String name, int[] nums, int k, int expected) {
        int[] original = nums.clone();

        assertEquals(expected, KthLargest.kthLargest(nums, k));
        assertArrayEquals(original, nums, "input must not be modified");
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalid")
    void rejectsInvalidArguments(String name, int[] nums, int k) {
        assertThrows(IllegalArgumentException.class, () -> KthLargest.kthLargest(nums, k));
    }

    @Test
    void mainPrintsThirdLargestOfSample() throws Exception {
        assertEquals("30", Console.capture(() -> KthLargest.main(new String[0])));
    }
}
