package problemsWithBunny;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testsupport.Console;

class IsDuplicateTest {

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of("example from main", new int[]{5, 7, 2, 1, 5, 6, 7, 0}, true),
                Arguments.of("all distinct", new int[]{1, 2, 3, 4}, false),
                Arguments.of("empty", new int[]{}, false),
                Arguments.of("single element", new int[]{9}, false),
                Arguments.of("pair", new int[]{9, 9}, true),
                Arguments.of("duplicate at the ends", new int[]{4, 1, 2, 3, 4}, true),
                Arguments.of("negative duplicate", new int[]{-1, 2, -1}, true),
                Arguments.of("negative and positive of same magnitude", new int[]{-1, 1}, false),
                Arguments.of("zeros", new int[]{0, 0}, true),
                Arguments.of("extreme values", new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE}, true));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("cases")
    void detectsDuplicates(String name, int[] nums, boolean expected) {
        assertEquals(expected, IsDuplicate.isDuplicate(nums));
    }

    @Test
    void rejectsNullArray() {
        assertThrows(IllegalArgumentException.class, () -> IsDuplicate.isDuplicate(null));
    }

    @Test
    void mainPrintsTrueForSample() throws Exception {
        assertEquals("true", Console.capture(() -> IsDuplicate.main(new String[0])));
    }
}
