package streams.problems;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;
import testsupport.Console;

class ProblemsTest {

    @Test
    void distinctSortedRemovesRepeatsAndSortsAscending() {
        assertEquals(List.of(1, 2, 3, 4, 5), Problems.distinctSorted(List.of(5, 3, 1, 3, 2, 5, 4)));
    }

    @Test
    void distinctSortedEdgeCases() {
        assertEquals(List.of(), Problems.distinctSorted(List.of()));
        assertEquals(List.of(7), Problems.distinctSorted(List.of(7)));
        assertEquals(List.of(7), Problems.distinctSorted(List.of(7, 7, 7)));
        assertEquals(List.of(-3, -1, 0, 2), Problems.distinctSorted(List.of(2, -1, 0, -3, -1)));
    }

    @Test
    void distinctSortedLeavesTheInputAlone() {
        List<Integer> input = new java.util.ArrayList<>(List.of(3, 1, 3));

        Problems.distinctSorted(input);

        assertEquals(List.of(3, 1, 3), input);
    }

    @Test
    void squaresOfEvenKeepsInputOrder() {
        assertEquals(List.of(4, 16, 36), Problems.squaresOfEven(List.of(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void squaresOfEvenEdgeCases() {
        assertEquals(List.of(), Problems.squaresOfEven(List.of()));
        assertEquals(List.of(), Problems.squaresOfEven(List.of(1, 3, 5)));
        assertEquals(List.of(0, 4, 4), Problems.squaresOfEven(List.of(0, -2, 2)));
        assertEquals(List.of(16, 16), Problems.squaresOfEven(List.of(4, 4)));
    }

    @Test
    void mainPrintsTheSameOutputAsBeforeTheLogicWasExtracted() throws Exception {
        assertEquals(Console.expectedOutput("streams.problems.Problems"),
                Console.capture(() -> Problems.main(new String[0])));
    }
}
