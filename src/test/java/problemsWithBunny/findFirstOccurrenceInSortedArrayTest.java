package problemsWithBunny;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testsupport.Console;

class findFirstOccurrenceInSortedArrayTest {

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of("example from main", new int[]{2, 5, 6, 6, 6, 6, 8, 9, 9, 9}, 6, 2),
                Arguments.of("target is the only element", new int[]{5}, 5, 0),
                Arguments.of("target at index 0", new int[]{6, 7}, 6, 0),
                Arguments.of("target at index 0 of a longer array", new int[]{2, 5, 6, 6, 8, 9}, 2, 0),
                Arguments.of("every element is the target", new int[]{6, 6, 6, 6}, 6, 0),
                Arguments.of("target at the last index", new int[]{1, 2, 3}, 3, 2),
                Arguments.of("run of targets at the end", new int[]{1, 2, 9, 9, 9}, 9, 2),
                Arguments.of("single occurrence in the middle", new int[]{1, 3, 5, 7, 9}, 5, 2),
                Arguments.of("target smaller than everything", new int[]{2, 4, 6}, 1, -1),
                Arguments.of("target larger than everything", new int[]{2, 4, 6}, 7, -1),
                Arguments.of("target falls between elements", new int[]{2, 4, 6}, 5, -1),
                Arguments.of("single element, no match", new int[]{5}, 4, -1),
                Arguments.of("empty array", new int[]{}, 5, -1),
                Arguments.of("negative numbers", new int[]{-5, -5, -2, 0, 3}, -5, 0),
                Arguments.of("negative target in the middle", new int[]{-5, -5, -2, -2, 0}, -2, 2));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("cases")
    void findsIndexOfFirstOccurrence(String name, int[] nums, int target, int expected) {
        assertEquals(expected, findFirstOccurrenceInSortedArray.findFirstOccurrenceInSortedArray(nums, target));
    }

    @Test
    void rejectsNullArray() {
        assertThrows(IllegalArgumentException.class,
                () -> findFirstOccurrenceInSortedArray.findFirstOccurrenceInSortedArray(null, 1));
    }

    @Test
    void mainPrintsIndexForSampleInput() throws Exception {
        assertEquals("2", Console.capture(() -> findFirstOccurrenceInSortedArray.main(new String[0])));
    }
}
