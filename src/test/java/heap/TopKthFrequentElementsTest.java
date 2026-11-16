package heap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testsupport.Console;

class TopKthFrequentElementsTest {

    private static final int[] SAMPLE = {1, 1, 1, 1, 2, 3, 4, 3, 2, 2, 4, 4, 5};

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of("example from main", SAMPLE, 3, Set.of(1, 2, 4)),
                Arguments.of("k = 1 is the most frequent", SAMPLE, 1, Set.of(1)),
                Arguments.of("k = number of distinct values", SAMPLE, 5, Set.of(1, 2, 3, 4, 5)),
                Arguments.of("k larger than distinct values returns them all", SAMPLE, 9, Set.of(1, 2, 3, 4, 5)),
                Arguments.of("k = 0", SAMPLE, 0, Set.of()),
                Arguments.of("empty array", new int[]{}, 3, Set.of()),
                Arguments.of("single element", new int[]{8}, 1, Set.of(8)),
                Arguments.of("negative numbers", new int[]{-1, -1, -2, 3, 3, 3}, 2, Set.of(-1, 3)),
                Arguments.of("tie at the cut-off keeps the smaller value", new int[]{1, 1, 2, 2, 3}, 1, Set.of(1)),
                Arguments.of("tie at the cut-off, larger value seen first", new int[]{7, 7, 3, 3}, 1, Set.of(3)),
                Arguments.of("three-way tie, k = 2", new int[]{30, 20, 10}, 2, Set.of(10, 20)));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("cases")
    void returnsTheKMostFrequentValues(String name, int[] nums, int k, Set<Integer> expected) {
        List<Integer> result = TopKthFrequentElements.topKthFrequentElements(nums, k);

        assertEquals(expected, new HashSet<>(result));
        assertEquals(expected.size(), result.size(), "no value may appear twice");
    }

    @Test
    void resultIsOrderedFromLeastToMostFrequent() {
        assertEquals(List.of(3, 2, 1),
                TopKthFrequentElements.topKthFrequentElements(new int[]{1, 1, 1, 2, 2, 3}, 3));
    }

    @Test
    void equallyFrequentValuesAreOrderedLargestFirst() {
        assertEquals(List.of(4, 2, 1), TopKthFrequentElements.topKthFrequentElements(SAMPLE, 3));
    }

    @Test
    void rejectsNegativeK() {
        assertThrows(IllegalArgumentException.class,
                () -> TopKthFrequentElements.topKthFrequentElements(new int[]{1, 2}, -1));
    }

    @Test
    void rejectsNullArray() {
        assertThrows(IllegalArgumentException.class,
                () -> TopKthFrequentElements.topKthFrequentElements(null, 1));
    }

    @Test
    void mainPrintsTopThreeOfSample() throws Exception {
        assertEquals("[4, 2, 1]", Console.capture(() -> TopKthFrequentElements.main(new String[0])));
    }
}
