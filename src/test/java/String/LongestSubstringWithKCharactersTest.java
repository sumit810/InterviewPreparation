package String;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import testsupport.Console;

class LongestSubstringWithKCharactersTest {

    @ParameterizedTest(name = "\"{0}\", k = {1} -> {2}")
    @CsvSource(textBlock = """
            ccaabbb, 2, 5
            eceba,   2, 3
            abaccc,  2, 4
            aaaa,    1, 4
            abcabc,  1, 1
            abc,     3, 3
            abc,     5, 3
            a,       1, 1
            a,       0, 0
            abc,     0, 0
            '',      2, 0
            '',      0, 0
            aabbcc,  2, 4
            abcdef,  2, 2
            aAaA,    1, 1
            """)
    void findsLongestSubstringWithAtMostKDistinctCharacters(String s, int k, int expected) {
        assertEquals(expected, LongestSubstringWithKCharacters.longestSubstringWithAtmostKCharacters(s, k));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "ab", ""})
    void rejectsNegativeK(String s) {
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> assertThrows(IllegalArgumentException.class,
                () -> LongestSubstringWithKCharacters.longestSubstringWithAtmostKCharacters(s, -1)));
    }

    @Test
    void rejectsNullString() {
        assertThrows(IllegalArgumentException.class,
                () -> LongestSubstringWithKCharacters.longestSubstringWithAtmostKCharacters(null, 2));
    }

    @Test
    void mainPrintsLengthForSample() throws Exception {
        assertEquals("5", Console.capture(() -> LongestSubstringWithKCharacters.main(new String[0])));
    }
}
