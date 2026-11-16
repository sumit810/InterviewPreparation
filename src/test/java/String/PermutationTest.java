package String;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.Test;
import testsupport.Console;

class PermutationTest {

    @Test
    void listsEveryOrderingInLexicalPositionOrder() {
        assertEquals(List.of("xyz", "xzy", "yxz", "yzx", "zxy", "zyx"), Permutation.permutation("", "xyz"));
    }

    @Test
    void emptyStringHasOneEmptyPermutation() {
        assertEquals(List.of(""), Permutation.permutation("", ""));
    }

    @Test
    void singleCharacter() {
        assertEquals(List.of("a"), Permutation.permutation("", "a"));
    }

    @Test
    void twoCharacters() {
        assertEquals(List.of("ab", "ba"), Permutation.permutation("", "ab"));
    }

    @Test
    void prefixIsPutInFrontOfEveryResult() {
        assertEquals(List.of("p-ab", "p-ba"), Permutation.permutation("p-", "ab"));
    }

    @Test
    void emptyStringWithPrefixGivesJustThePrefix() {
        assertEquals(List.of("p"), Permutation.permutation("p", ""));
    }

    @Test
    void fourDistinctCharactersGiveTwentyFourDistinctResults() {
        List<String> result = Permutation.permutation("", "abcd");

        assertEquals(24, result.size());
        assertEquals(24, new HashSet<>(result).size());
        assertTrue(result.stream().allMatch(p -> p.length() == 4 && p.chars().distinct().count() == 4));
    }

    @Test
    void repeatedCharactersGiveRepeatedResults() {
        List<String> result = Permutation.permutation("", "aab");

        assertEquals(List.of("aab", "aba", "aab", "aba", "baa", "baa"), result);
    }

    @Test
    void rejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> Permutation.permutation("", null));
        assertThrows(IllegalArgumentException.class, () -> Permutation.permutation(null, "ab"));
    }

    @Test
    void mainPrintsOnePermutationPerLine() throws Exception {
        assertEquals("xyz\nxzy\nyxz\nyzx\nzxy\nzyx", Console.capture(() -> Permutation.main()));
    }
}
