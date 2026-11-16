package problems.hashing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import testsupport.Console;

class LongestSubstringWithoutRepeatingCharactersTest {

    @ParameterizedTest(name = "\"{0}\" -> {1}")
    @CsvSource(textBlock = """
            abcabcbb, 3
            bbbbb,    1
            pwwkew,   3
            dvdf,     3
            abba,     2
            tmmzuxt,  5
            abcdef,   6
            a,        1
            '',       0
            ' ',      1
            'a b a',  3
            aA,       2
            aab,      2
            abcda,    4
            """)
    void findsLengthOfLongestSubstringWithoutRepeats(String input, int expected) {
        assertEquals(expected,
                LongestSubstringWithoutRepeatingCharacters.longestSubstringWithoutRepeatingCharacters(input));
    }

    @Test
    void rejectsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> LongestSubstringWithoutRepeatingCharacters.longestSubstringWithoutRepeatingCharacters(null));
    }

    @Test
    void mainPrintsLengthForSample() throws Exception {
        assertEquals("Longest Substring without any characters repeating: 3",
                Console.capture(() -> LongestSubstringWithoutRepeatingCharacters.main(new String[0])));
    }
}
