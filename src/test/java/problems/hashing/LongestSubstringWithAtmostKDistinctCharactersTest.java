package problems.hashing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import testsupport.Console;

class LongestSubstringWithAtmostKDistinctCharactersTest {

    @ParameterizedTest(name = "\"{0}\", k = {1} -> {2}")
    @CsvSource(textBlock = """
            eceba,   2, 3
            ccaabbb, 2, 5
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
        assertEquals(expected,
                LongestSubstringWithAtmostKDistinctCharacters.longestSubstringWithAtmostKDistinctCharacters(s, k));
    }

    @Test
    void nullStringHasLengthZero() {
        assertEquals(0,
                LongestSubstringWithAtmostKDistinctCharacters.longestSubstringWithAtmostKDistinctCharacters(null, 2));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "ab", ""})
    void rejectsNegativeK(String s) {
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> assertThrows(IllegalArgumentException.class,
                () -> LongestSubstringWithAtmostKDistinctCharacters
                        .longestSubstringWithAtmostKDistinctCharacters(s, -1)));
    }

    @Test
    void rejectsNegativeKEvenForNullString() {
        assertThrows(IllegalArgumentException.class,
                () -> LongestSubstringWithAtmostKDistinctCharacters
                        .longestSubstringWithAtmostKDistinctCharacters(null, -1));
    }

    @Test
    void mainPrintsLengthForSample() throws Exception {
        assertEquals("3", Console.capture(() -> LongestSubstringWithAtmostKDistinctCharacters.main(new String[0])));
    }
}
