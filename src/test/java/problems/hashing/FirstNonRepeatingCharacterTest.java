package problems.hashing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import testsupport.Console;

class FirstNonRepeatingCharacterTest {

    @ParameterizedTest(name = "\"{0}\" -> {1}")
    @CsvSource(textBlock = """
            aabbcdde, c
            a,        a
            abc,      a
            aabbc,    c
            abcabcd,  d
            Aa,       A
            aAa,      A
            'a a b',  b
            swiss,    w
            """)
    void findsFirstCharacterThatOccursOnce(String input, char expected) {
        assertEquals(expected, FirstNonRepeatingCharacter.findFirstNonRepeatingCharacter(input));
    }

    @ParameterizedTest(name = "\"{0}\" has none")
    @ValueSource(strings = {"", "aabb", "aa", "abab", "zzz"})
    void returnsNullWhenEveryCharacterRepeats(String input) {
        assertNull(FirstNonRepeatingCharacter.findFirstNonRepeatingCharacter(input));
    }

    @Test
    void rejectsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> FirstNonRepeatingCharacter.findFirstNonRepeatingCharacter(null));
    }

    @Test
    void mainPrintsCharacterForSample() throws Exception {
        assertEquals("First Non-Repeating chanracter is : c",
                Console.capture(() -> FirstNonRepeatingCharacter.main(new String[0])));
    }
}
