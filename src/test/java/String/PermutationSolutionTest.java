package String;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.Test;
import testsupport.Console;

class PermutationSolutionTest {

    @Test
    void repeatedCharactersDoNotGiveRepeatedResults() {
        assertEquals(List.of("aab", "aba", "baa"), PermutationSolution.findAllPermutations("", "aab"));
    }

    @Test
    void sampleFromMainHasTwelveDistinctPermutations() {
        List<String> result = PermutationSolution.findAllPermutations("", "abca");

        assertEquals(List.of("abca", "abac", "acba", "acab", "aabc", "aacb",
                "baca", "baac", "bcaa", "caba", "caab", "cbaa"), result);
        assertEquals(12, new HashSet<>(result).size());
    }

    @Test
    void distinctCharactersGiveEveryOrdering() {
        assertEquals(List.of("xyz", "xzy", "yxz", "yzx", "zxy", "zyx"),
                PermutationSolution.findAllPermutations("", "xyz"));
    }

    @Test
    void allCharactersEqualGiveOneResult() {
        assertEquals(List.of("aaa"), PermutationSolution.findAllPermutations("", "aaa"));
    }

    @Test
    void emptyStringHasOneEmptyPermutation() {
        assertEquals(List.of(""), PermutationSolution.findAllPermutations("", ""));
    }

    @Test
    void singleCharacter() {
        assertEquals(List.of("a"), PermutationSolution.findAllPermutations("", "a"));
    }

    @Test
    void prefixIsPutInFrontOfEveryResult() {
        assertEquals(List.of("p-ab", "p-ba"), PermutationSolution.findAllPermutations("p-", "ab"));
    }

    @Test
    void rejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> PermutationSolution.findAllPermutations("", null));
        assertThrows(IllegalArgumentException.class, () -> PermutationSolution.findAllPermutations(null, "ab"));
    }

    @Test
    void mainPrintsOnePermutationPerLineWithoutLeadingSpace() throws Exception {
        assertEquals("abca\nabac\nacba\nacab\naabc\naacb\nbaca\nbaac\nbcaa\ncaba\ncaab\ncbaa",
                Console.capture(() -> PermutationSolution.main(new String[0])));
    }
}
