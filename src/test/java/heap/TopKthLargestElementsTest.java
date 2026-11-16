package heap;

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

class TopKthLargestElementsTest {

    private static final int[] SAMPLE = {10, 10, 10, 34, 76, 23, 1, 34, 23, 9};

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of("example from main, duplicates kept", SAMPLE, 3, List.of(34, 34, 76)),
                Arguments.of("k = 1 is the maximum", SAMPLE, 1, List.of(76)),
                Arguments.of("k = 0", SAMPLE, 0, List.of()),
                Arguments.of("k = n sorts the whole array", new int[]{3, 1, 2}, 3, List.of(1, 2, 3)),
                Arguments.of("k larger than the array returns everything", new int[]{3, 1, 2}, 10, List.of(1, 2, 3)),
                Arguments.of("empty array", new int[]{}, 2, List.of()),
                Arguments.of("single element", new int[]{5}, 1, List.of(5)),
                Arguments.of("negative numbers", new int[]{-7, -1, -3}, 2, List.of(-3, -1)),
                Arguments.of("all equal", new int[]{4, 4, 4}, 2, List.of(4, 4)));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("cases")
    void returnsKLargestInAscendingOrder(String name, int[] nums, int k, List<Integer> expected) {
        int[] original = nums.clone();

        assertEquals(expected, TopKthLargestElements.topKthLargestElements(nums, k));
        assertArrayEquals(original, nums, "input must not be modified");
    }

    @Test
    void rejectsNegativeK() {
        assertThrows(IllegalArgumentException.class,
                () -> TopKthLargestElements.topKthLargestElements(new int[]{1, 2}, -1));
    }

    @Test
    void rejectsNullArray() {
        assertThrows(IllegalArgumentException.class,
                () -> TopKthLargestElements.topKthLargestElements(null, 1));
    }

    @Test
    void mainPrintsTopThreeOfSample() throws Exception {
        assertEquals("[34, 34, 76]", Console.capture(() -> TopKthLargestElements.main(new String[0])));
    }
}
