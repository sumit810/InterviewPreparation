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

class KthLargestElementsTest {

    private static final int[] SAMPLE = {19, 23, 13, 98, 34, 23, 9, 23, 76, 23};

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of("example from main", SAMPLE, 4, List.of(23, 34, 76, 98)),
                Arguments.of("duplicates kept", SAMPLE, 6, List.of(23, 23, 23, 34, 76, 98)),
                Arguments.of("k = 1 is the maximum", SAMPLE, 1, List.of(98)),
                Arguments.of("k = 0", SAMPLE, 0, List.of()),
                Arguments.of("k = n sorts the whole array", new int[]{3, 1, 2}, 3, List.of(1, 2, 3)),
                Arguments.of("k larger than the array returns everything", new int[]{3, 1, 2}, 10, List.of(1, 2, 3)),
                Arguments.of("empty array", new int[]{}, 2, List.of()),
                Arguments.of("single element", new int[]{5}, 1, List.of(5)),
                Arguments.of("negative numbers", new int[]{-7, -1, -3}, 2, List.of(-3, -1)));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("cases")
    void returnsKLargestInAscendingOrder(String name, int[] nums, int k, List<Integer> expected) {
        int[] original = nums.clone();

        assertEquals(expected, KthLargestElements.KthLargestElements(nums, k));
        assertArrayEquals(original, nums, "input must not be modified");
    }

    @Test
    void rejectsNegativeK() {
        assertThrows(IllegalArgumentException.class, () -> KthLargestElements.KthLargestElements(new int[]{1, 2}, -1));
    }

    @Test
    void rejectsNullArray() {
        assertThrows(IllegalArgumentException.class, () -> KthLargestElements.KthLargestElements(null, 1));
    }

    @Test
    void mainPrintsTopFourOfSample() throws Exception {
        assertEquals("Top 4th numbers: [23, 34, 76, 98]",
                Console.capture(() -> KthLargestElements.main(new String[0])));
    }
}
