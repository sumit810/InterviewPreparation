package String;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testsupport.Console;

class GroupAnagramsTest {

    /** The order of the groups is unspecified; the order inside a group is input order. */
    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of("example from main", new String[]{"eat", "tea", "tan", "ate", "nat", "bat"},
                        Set.of(List.of("eat", "tea", "ate"), List.of("tan", "nat"), List.of("bat"))),
                Arguments.of("empty array", new String[]{}, Set.of()),
                Arguments.of("single word", new String[]{"abc"}, Set.of(List.of("abc"))),
                Arguments.of("no anagrams", new String[]{"a", "b", "c"}, Set.of(List.of("a"), List.of("b"), List.of("c"))),
                Arguments.of("all anagrams", new String[]{"abc", "bca", "cab"}, Set.of(List.of("abc", "bca", "cab"))),
                Arguments.of("identical words stay together", new String[]{"aa", "aa"}, Set.of(List.of("aa", "aa"))),
                Arguments.of("empty strings group together", new String[]{"", "a", ""}, Set.of(List.of("", ""), List.of("a"))),
                Arguments.of("same letters, different counts", new String[]{"aab", "abb"}, Set.of(List.of("aab"), List.of("abb"))),
                Arguments.of("different lengths", new String[]{"ab", "abc"}, Set.of(List.of("ab"), List.of("abc"))),
                Arguments.of("case-sensitive", new String[]{"Ab", "ba"}, Set.of(List.of("Ab"), List.of("ba"))));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("cases")
    void groupsWordsThatAreAnagrams(String name, String[] words, Set<List<String>> expected) {
        String[] original = words.clone();

        List<List<String>> result = GroupAnagrams.groupAnagrams(words);

        assertEquals(expected, new HashSet<>(result));
        assertEquals(expected.size(), result.size(), "no group may appear twice");
        org.junit.jupiter.api.Assertions.assertArrayEquals(original, words, "input must not be modified");
    }

    @Test
    void rejectsNullArray() {
        assertThrows(IllegalArgumentException.class, () -> GroupAnagrams.groupAnagrams(null));
    }

    @Test
    void rejectsNullWord() {
        assertThrows(IllegalArgumentException.class, () -> GroupAnagrams.groupAnagrams(new String[]{"a", null}));
    }

    @Test
    void mainPrintsGroupsForSample() throws Exception {
        assertEquals("[[eat, tea, ate], [bat], [tan, nat]]", Console.capture(() -> GroupAnagrams.main(new String[0])));
    }
}
