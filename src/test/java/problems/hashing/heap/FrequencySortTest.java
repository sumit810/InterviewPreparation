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

class FrequencySortTest {

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of("example from main", new int[]{1, 1, 1, 3, 2, 1, 3, 2, 4}, List.of(1, 1, 1, 1, 2, 2, 3, 3, 4)),
                Arguments.of("most frequent first even when it is the largest", new int[]{1, 9, 9, 9, 2, 2}, List.of(9, 9, 9, 2, 2, 1)),
                Arguments.of("equal frequency orders by value", new int[]{3, 1, 2}, List.of(1, 2, 3)),
                Arguments.of("equal frequency pairs order by value", new int[]{5, 5, 4, 4}, List.of(4, 4, 5, 5)),
                Arguments.of("empty array", new int[]{}, List.of()),
                Arguments.of("single element", new int[]{7}, List.of(7)),
                Arguments.of("all equal", new int[]{7, 7, 7}, List.of(7, 7, 7)),
                Arguments.of("negative numbers", new int[]{-1, -1, -2, 0}, List.of(-1, -1, -2, 0)));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("cases")
    void ordersByFrequencyThenValue(String name, int[] nums, List<Integer> expected) {
        int[] original = nums.clone();

        assertEquals(expected, FrequencySort.frequencySort(nums));
        assertArrayEquals(original, nums, "input must not be modified");
    }

    @Test
    void rejectsNullArray() {
        assertThrows(IllegalArgumentException.class, () -> FrequencySort.frequencySort(null));
    }

    @Test
    void mainPrintsSortedSample() throws Exception {
        assertEquals("[1, 1, 1, 1, 2, 2, 3, 3, 4]", Console.capture(() -> FrequencySort.main(new String[0])));
    }
}
