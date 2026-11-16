package collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import testsupport.Console;

class SetDemoTest {

    @ParameterizedTest(name = "\"{0}\" -> \"{1}\"")
    @CsvSource(textBlock = """
            zxyzxyz, xxyyzzz
            cba,     abc
            abc,     abc
            a,       a
            '',      ''
            bBaA,    ABab
            b1a,     1ab
            'b a',   ' ab'
            """)
    void sortsCharactersAscending(String input, String expected) {
        assertEquals(expected, SetDemo.sortChars(input));
    }

    @Test
    void leavesTheOriginalStringUnchanged() {
        String original = "zxy";

        SetDemo.sortChars(original);

        assertEquals("zxy", original);
    }

    @Test
    void rejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> SetDemo.sortChars(null));
    }

    @Test
    void mainPrintsSetHoldingTheSortedString() throws Exception {
        assertEquals("[xxyyzzz]", Console.capture(() -> SetDemo.main(new String[0])));
    }
}
