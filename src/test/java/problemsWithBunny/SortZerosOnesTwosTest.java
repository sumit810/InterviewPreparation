package problemsWithBunny;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;
import java.util.Arrays;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Test;
import testsupport.Console;

class SortZerosOnesTwosTest {

    /** Both implementations must behave identically, so every case runs against each of them. */
    static Stream<Arguments> implementations() {
        UnaryOperator<int[]> counting = SortZerosOnesTwos::sortZerosOnesTwos;
        UnaryOperator<int[]> dutchFlag = SortZerosOnesTwos::sortZerosOnesTwosUisngDutchNationalFlag;
        return Stream.of(Arguments.of("counting", counting), Arguments.of("dutch national flag", dutchFlag));
    }

    static Stream<int[]> validInputs() {
        return Stream.of(
                new int[]{0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 1},
                new int[]{},
                new int[]{0},
                new int[]{1},
                new int[]{2},
                new int[]{0, 0, 0},
                new int[]{1, 1, 1},
                new int[]{2, 2, 2},
                new int[]{0, 1, 2},
                new int[]{2, 1, 0},
                new int[]{2, 0},
                new int[]{1, 0},
                new int[]{2, 1},
                new int[]{2, 2, 0, 0, 1, 1},
                new int[]{1, 2, 0, 1, 2, 0, 1, 2, 0});
    }

    static Stream<int[]> invalidInputs() {
        return Stream.of(new int[]{3}, new int[]{-1}, new int[]{0, 5, 1}, new int[]{2, 1, 0, 7}, new int[]{0, 1, 2, -2});
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("implementations")
    void sortsEveryValidInput(String name, UnaryOperator<int[]> sort) {
        validInputs().forEach(input -> {
            int[] expected = input.clone();
            Arrays.sort(expected);

            int[] result = assertTimeoutPreemptively(Duration.ofSeconds(2), () -> sort.apply(input));

            assertSame(input, result, "works in place and returns the same array");
            assertArrayEquals(expected, result);
        });
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("implementations")
    void rejectsValuesOtherThanZeroOneTwoAndLeavesInputUntouched(String name, UnaryOperator<int[]> sort) {
        invalidInputs().forEach(input -> {
            int[] original = input.clone();

            assertTimeoutPreemptively(Duration.ofSeconds(2),
                    () -> assertThrows(IllegalArgumentException.class, () -> sort.apply(input),
                            "input " + Arrays.toString(original)));

            assertArrayEquals(original, input);
        });
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("implementations")
    void rejectsNullArray(String name, UnaryOperator<int[]> sort) {
        assertThrows(IllegalArgumentException.class, () -> sort.apply(null));
    }

    @Test
    void mainPrintsBothSortedSamples() throws Exception {
        assertEquals("[0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2]\n[0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2]",
                Console.capture(() -> SortZerosOnesTwos.main(new String[0])));
    }
}
