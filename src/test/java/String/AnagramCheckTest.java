package String;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import testsupport.Console;

class AnagramCheckTest {

    /** Lowercase-only cases, valid for both implementations. Empty strings are written as ''. */
    private static final String SHARED_CASES = """
            anagram, nagaram, true
            listen,  silent,  true
            a,       a,       true
            '',      '',      true
            rat,     car,     false
            aab,     abb,     false
            ab,      bac,     false
            bac,     ab,      false
            abc,     ab,      false
            ab,      abc,     false
            a,       '',      false
            '',      a,       false
            aaa,     aa,      false
            """;

    @ParameterizedTest(name = "frequency count: \"{0}\" vs \"{1}\" -> {2}")
    @CsvSource(textBlock = SHARED_CASES)
    void frequencyCountVersion(String s1, String s2, boolean expected) {
        assertEquals(expected, AnagramCheck.isAnagramUsingFrequencyCount(s1, s2));
    }

    @ParameterizedTest(name = "map: \"{0}\" vs \"{1}\" -> {2}")
    @CsvSource(textBlock = SHARED_CASES)
    void mapVersion(String s1, String s2, boolean expected) {
        assertEquals(expected, AnagramCheck.isAnagram(s1, s2));
    }

    @ParameterizedTest(name = "frequency count rejects \"{0}\" vs \"{1}\"")
    @CsvSource({"Listen, Silent", "listen, Silent", "a b, b a", "abc1, 1abc", "abc, ab{", "ab`, abc"})
    void frequencyCountVersionRejectsAnythingButLowercaseLetters(String s1, String s2) {
        assertThrows(IllegalArgumentException.class, () -> AnagramCheck.isAnagramUsingFrequencyCount(s1, s2));
    }

    @Test
    void mapVersionHandlesAnyCharacterAndIsCaseSensitive() {
        assertTrue(AnagramCheck.isAnagram("a b1", "1b a"));
        assertTrue(AnagramCheck.isAnagram("Listen", "nLiset"));
        assertFalse(AnagramCheck.isAnagram("Listen", "silent"));
    }

    @Test
    void bothVersionsRejectNull() {
        assertThrows(IllegalArgumentException.class, () -> AnagramCheck.isAnagramUsingFrequencyCount(null, "a"));
        assertThrows(IllegalArgumentException.class, () -> AnagramCheck.isAnagramUsingFrequencyCount("a", null));
        assertThrows(IllegalArgumentException.class, () -> AnagramCheck.isAnagram(null, "a"));
        assertThrows(IllegalArgumentException.class, () -> AnagramCheck.isAnagram("a", null));
    }

    @Test
    void mainPrintsTrueForSample() throws Exception {
        assertEquals("true", Console.capture(() -> AnagramCheck.main(new String[0])));
    }
}
