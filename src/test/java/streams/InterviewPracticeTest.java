package streams;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import testsupport.Console;

class InterviewPracticeTest {

    // ---- characterCounts

    @Test
    void countsEachCharacter() {
        assertEquals(Map.of('M', 1L, 'i', 4L, 's', 4L, 'p', 2L), InterviewPractice.characterCounts("Mississippi"));
    }

    @Test
    void characterCountsAreCaseSensitiveAndIncludeSpaces() {
        assertEquals(Map.of('a', 1L, 'A', 1L, ' ', 2L), InterviewPractice.characterCounts("a A "));
    }

    @Test
    void emptyStringHasNoCharacterCounts() {
        assertEquals(Map.of(), InterviewPractice.characterCounts(""));
    }

    // ---- sumOfDistinct

    @Test
    void sumsEachDistinctValueOnce() {
        assertEquals(22, InterviewPractice.sumOfDistinct(new int[]{1, 6, 7, 8, 1, 1, 8, 8, 7}));
    }

    @Test
    void sumOfDistinctEdgeCases() {
        assertEquals(0, InterviewPractice.sumOfDistinct(new int[]{}));
        assertEquals(5, InterviewPractice.sumOfDistinct(new int[]{5}));
        assertEquals(5, InterviewPractice.sumOfDistinct(new int[]{5, 5, 5}));
        assertEquals(-3, InterviewPractice.sumOfDistinct(new int[]{-1, -2, -1, 0}));
    }

    // ---- firstNonRepeatedCharacter

    @ParameterizedTest(name = "\"{0}\" -> {1}")
    @CsvSource({"Hello World, H", "aabbc, c", "swiss, w", "a, a", "aAa, A", "'a a b', b"})
    void findsFirstNonRepeatedCharacter(String input, char expected) {
        assertEquals(expected, InterviewPractice.firstNonRepeatedCharacter(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "aa", "abab"})
    void firstNonRepeatedCharacterIsNullWhenEverythingRepeats(String input) {
        assertNull(InterviewPractice.firstNonRepeatedCharacter(input));
    }

    // ---- firstRepeatedCharacter

    @ParameterizedTest(name = "\"{0}\" -> {1}")
    @CsvSource({"Hello World, l", "abca, a", "abcb, b", "aa, a", "abba, a"})
    void findsRepeatedCharacterThatAppearsEarliest(String input, char expected) {
        assertEquals(expected, InterviewPractice.firstRepeatedCharacter(input));
    }

    @Test
    void spaceCountsAsACharacterWhenLookingForRepeats() {
        assertEquals(' ', InterviewPractice.firstRepeatedCharacter("a b c"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "abc", "aA"})
    void firstRepeatedCharacterIsNullWhenNothingRepeats(String input) {
        assertNull(InterviewPractice.firstRepeatedCharacter(input));
    }

    // ---- integersOnly

    @Test
    void keepsOnlyTheStringsThatAreIntegers() {
        assertEquals(List.of(123, 652),
                InterviewPractice.integersOnly(new String[]{"abc", "def", "123", "652", "xyz"}));
    }

    @Test
    void integersOnlyAcceptsLeadingZeros() {
        assertEquals(List.of(7, 0), InterviewPractice.integersOnly(new String[]{"007", "0"}));
    }

    @Test
    void integersOnlySkipsAnythingThatIsNotMadeOfDigitsOnly() {
        assertEquals(List.of(),
                InterviewPractice.integersOnly(new String[]{"", " ", "1.5", "12a", " 12", "1 2", "-", "-5", "+5", "\u0661\u0662"}));
    }

    @Test
    void integersOnlySkipsNumbersAboveTheIntRange() {
        assertEquals(List.of(Integer.MAX_VALUE),
                InterviewPractice.integersOnly(new String[]{"2147483647", "2147483648", "99999999999"}));
    }

    @Test
    void integersOnlyRejectsANullElement() {
        org.junit.jupiter.api.Assertions.assertThrows(NullPointerException.class,
                () -> InterviewPractice.integersOnly(new String[]{"1", null}));
    }

    @Test
    void integersOnlyOnEmptyArray() {
        assertEquals(List.of(), InterviewPractice.integersOnly(new String[]{}));
    }

    // ---- productOfFirstTwo

    @Test
    void multipliesTheFirstTwoElements() {
        assertEquals(60L, InterviewPractice.productOfFirstTwo(new int[]{12, 5, 6, 9, 2, 4}));
    }

    @Test
    void productOfFirstTwoEdgeCases() {
        assertEquals(1L, InterviewPractice.productOfFirstTwo(new int[]{}));
        assertEquals(7L, InterviewPractice.productOfFirstTwo(new int[]{7}));
        assertEquals(0L, InterviewPractice.productOfFirstTwo(new int[]{0, 9}));
        assertEquals(-12L, InterviewPractice.productOfFirstTwo(new int[]{-3, 4, 100}));
    }

    @Test
    void productOfFirstTwoDoesNotOverflow() {
        long max = Integer.MAX_VALUE;

        assertEquals(max * max, InterviewPractice.productOfFirstTwo(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}));
    }

    @Test
    void mainPrintsTheSameOutputAsBeforeTheLogicWasExtracted() throws Exception {
        assertEquals(Console.expectedOutput("streams.InterviewPractice"),
                Console.capture(() -> InterviewPractice.main(new String[0])));
    }
}
