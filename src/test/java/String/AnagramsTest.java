package String;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testsupport.Console;

class AnagramsTest {

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of("example from main", "cbaebabacd", "abc", List.of(0, 6)),
                Arguments.of("overlapping matches", "abab", "ab", List.of(0, 1, 2)),
                Arguments.of("whole string is the only match", "abc", "cab", List.of(0)),
                Arguments.of("match only at the end", "xxabc", "cba", List.of(2)),
                Arguments.of("single-character pattern", "aXa", "a", List.of(0, 2)),
                Arguments.of("repeated letters in the pattern", "aabaa", "aab", List.of(0, 1, 2)),
                Arguments.of("no match", "abcdef", "xyz", List.of()),
                Arguments.of("right letters, wrong counts", "aab", "abb", List.of()),
                Arguments.of("pattern longer than the text", "ab", "abc", List.of()),
                Arguments.of("empty text", "", "a", List.of()),
                Arguments.of("empty pattern", "ab", "", List.of()),
                Arguments.of("both empty", "", "", List.of()),
                Arguments.of("case-sensitive", "Abc", "abc", List.of()));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("cases")
    void findsStartIndexOfEveryAnagram(String name, String s, String p, List<Integer> expected) {
        assertEquals(expected, Anagrams.findAnagrams(s, p));
    }

    @Test
    void rejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> Anagrams.findAnagrams(null, "a"));
        assertThrows(IllegalArgumentException.class, () -> Anagrams.findAnagrams("a", null));
    }

    @Test
    void mainPrintsIndicesForSample() throws Exception {
        assertEquals("[[0, 6]]", Console.capture(() -> Anagrams.main(new String[0])));
    }
}
