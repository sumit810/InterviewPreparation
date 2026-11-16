package String;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import testsupport.Console;

class ReverseCharsTest {

    @ParameterizedTest(name = "\"{0}\" -> \"{1}\"")
    @CsvSource(textBlock = """
            sumit,   timus
            abcd,    dcba
            ab,      ba
            a,       a
            '',      ''
            aba,     aba
            aab,     baa
            'a b',   'b a'
            Ab1!,    !1bA
            """)
    void reversesCharacters(String input, String expected) {
        assertArrayEquals(expected.toCharArray(), ReverseChars.reverse(input.toCharArray()));
    }

    @Test
    void reversesInPlaceAndReturnsTheSameArray() {
        char[] chars = {'x', 'y', 'z'};

        char[] result = ReverseChars.reverse(chars);

        assertSame(chars, result);
        assertArrayEquals(new char[]{'z', 'y', 'x'}, chars);
    }

    @Test
    void reversingTwiceRestoresTheOriginal() {
        char[] chars = "interview".toCharArray();

        ReverseChars.reverse(ReverseChars.reverse(chars));

        assertArrayEquals("interview".toCharArray(), chars);
    }

    @Test
    void rejectsNullArray() {
        assertThrows(IllegalArgumentException.class, () -> ReverseChars.reverse(null));
    }

    @Test
    void mainPrintsReversedSample() throws Exception {
        assertEquals("Reverse String [t, i, m, u, s]", Console.capture(() -> ReverseChars.main(new String[0])));
    }
}
