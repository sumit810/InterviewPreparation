package String;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testsupport.Console;

class SolutionTest {

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of("example from main", "Java is Amazing", "is Java Amazing"),
                Arguments.of("already ordered", "a bb ccc", "a bb ccc"),
                Arguments.of("reverse ordered", "ccc bb a", "a bb ccc"),
                Arguments.of("equal lengths keep input order", "bb aa c dd", "c bb aa dd"),
                Arguments.of("single word", "Java", "Java"),
                Arguments.of("empty string", "", ""),
                Arguments.of("only spaces", "    ", ""),
                Arguments.of("leading space", " Java is", "is Java"),
                Arguments.of("trailing space", "Java is ", "is Java"),
                Arguments.of("several spaces between words", "Java    is   fun", "is fun Java"),
                Arguments.of("tabs and newlines separate words", "Java\tis\nfun", "is fun Java"));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("cases")
    void ordersWordsByLength(String name, String input, String expected) {
        assertEquals(expected, Solution.sortWords(input));
    }

    @Test
    void rejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> Solution.sortWords(null));
    }

    @Test
    void mainPrintsSortedSample() throws Exception {
        assertEquals("is Java Amazing", Console.capture(() -> Solution.main(new String[0])));
    }
}
