package problems.hashing;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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

class FindDuplicatesTest {

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of("example from main", new int[]{1, 2, 2, 3, 4, 4, 5}, Set.of(2, 4)),
                Arguments.of("no duplicates", new int[]{1, 2, 3}, Set.of()),
                Arguments.of("empty array", new int[]{}, Set.of()),
                Arguments.of("single element", new int[]{7}, Set.of()),
                Arguments.of("value seen three times is reported once", new int[]{3, 3, 3}, Set.of(3)),
                Arguments.of("duplicates far apart", new int[]{9, 1, 2, 3, 9}, Set.of(9)),
                Arguments.of("every value duplicated", new int[]{1, 1, 2, 2}, Set.of(1, 2)),
                Arguments.of("negative numbers and zero", new int[]{-1, 0, -1, 0, 5}, Set.of(-1, 0)),
                Arguments.of("extreme values", new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE},
                        Set.of(Integer.MIN_VALUE)));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("cases")
    void findsValuesThatOccurMoreThanOnce(String name, int[] nums, Set<Integer> expected) {
        int[] original = nums.clone();

        List<Integer> result = FindDuplicates.findDuplicates(nums);

        assertEquals(expected, new HashSet<>(result));
        assertEquals(expected.size(), result.size(), "each duplicate is reported once");
        assertArrayEquals(original, nums, "input must not be modified");
    }

    @Test
    void rejectsNullArray() {
        assertThrows(IllegalArgumentException.class, () -> FindDuplicates.findDuplicates(null));
    }

    @Test
    void mainPrintsDuplicatesOfSample() throws Exception {
        assertEquals("Duplicate Elements in a list : [2, 4]", Console.capture(() -> FindDuplicates.main(new String[0])));
    }
}
