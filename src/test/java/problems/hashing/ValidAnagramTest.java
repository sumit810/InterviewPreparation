package problems.hashing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import testsupport.Console;

class ValidAnagramTest {

    @ParameterizedTest(name = "\"{0}\" vs \"{1}\" -> {2}")
    @CsvSource(textBlock = """
            listen,  silent,  true
            sumit,   timus,   true
            sumita,  timuse,  false
            a,       a,       true
            '',      '',      true
            rat,     car,     false
            aab,     abb,     false
            abb,     aab,     false
            ab,      abc,     false
            abc,     ab,      false
            a,       '',      false
            Listen,  silent,  false
            'a b',   'b a',   true
            ab1,     1ba,     true
            aabb,    abab,    true
            """)
    void checksWhetherTwoStringsAreAnagrams(String s1, String s2, boolean expected) {
        assertEquals(expected, ValidAnagram.isAnagram(s1, s2));
    }

    @Test
    void rejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> ValidAnagram.isAnagram(null, "a"));
        assertThrows(IllegalArgumentException.class, () -> ValidAnagram.isAnagram("a", null));
    }

    @Test
    void mainPrintsThreeResults() throws Exception {
        assertEquals("true\ntrue\nfalse", Console.capture(() -> ValidAnagram.main(new String[0])));
    }
}
