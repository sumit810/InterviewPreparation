package problems.hashing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import testsupport.Console;

class CountOccurrencesAnagramTest {

    /** Both implementations must agree on every case. Empty strings are written as ''. */
    private static final String CASES = """
            forxxorfxdofr, for,  3
            aaa,           a,    3
            abab,          ab,   3
            abc,           abc,  1
            abc,           cba,  1
            aabaa,         aab,  3
            abc,           abcd, 0
            abc,           xyz,  0
            aab,           abb,  0
            Abc,           abc,  0
            '',            a,    0
            abc,           '',   0
            '',            '',   0
            """;

    @ParameterizedTest(name = "countAnagrams(\"{0}\", \"{1}\") -> {2}")
    @CsvSource(textBlock = CASES)
    void forLoopVersion(String text, String pattern, int expected) {
        assertEquals(expected, assertTimeoutPreemptively(Duration.ofSeconds(2),
                () -> CountOccurrencesAnagram.countAnagrams(text, pattern)));
    }

    @ParameterizedTest(name = "countAnagramsNew(\"{0}\", \"{1}\") -> {2}")
    @CsvSource(textBlock = CASES)
    void whileLoopVersion(String text, String pattern, int expected) {
        assertEquals(expected, assertTimeoutPreemptively(Duration.ofSeconds(2),
                () -> CountOccurrencesAnagram.countAnagramsNew(text, pattern)));
    }

    @Test
    void bothVersionsRejectNull() {
        assertThrows(IllegalArgumentException.class, () -> CountOccurrencesAnagram.countAnagrams(null, "a"));
        assertThrows(IllegalArgumentException.class, () -> CountOccurrencesAnagram.countAnagrams("a", null));
        assertThrows(IllegalArgumentException.class, () -> CountOccurrencesAnagram.countAnagramsNew(null, "a"));
        assertThrows(IllegalArgumentException.class, () -> CountOccurrencesAnagram.countAnagramsNew("a", null));
    }

    @Test
    void mainPrintsCountForSample() throws Exception {
        assertEquals("3", Console.capture(() -> CountOccurrencesAnagram.main(new String[0])));
    }
}
