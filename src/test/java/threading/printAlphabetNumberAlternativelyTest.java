package threading;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import testsupport.Console;

class printAlphabetNumberAlternativelyTest {

    private static final String EXPECTED =
            "A1B2C3D4E5F6G7H8I9J10K11L12M13N14O15P16Q17R18S19T20U21V22W23X24Y25Z26";

    /** Repeated because a turn-taking bug would only show up under some thread interleavings. */
    @RepeatedTest(20)
    void lettersAndNumbersStrictlyAlternate() {
        assertEquals(EXPECTED, assertTimeoutPreemptively(Duration.ofSeconds(5),
                printAlphabetNumberAlternatively::alternate));
    }

    @Test
    void takeTurnWritesImmediatelyWhenItIsItsTurnAndHandsOver() {
        Object lock = new Object();
        boolean[] alphabetTurn = {true};
        StringBuilder out = new StringBuilder();

        printAlphabetNumberAlternatively.takeTurn(lock, alphabetTurn, true, out, "A");

        assertEquals("A", out.toString());
        assertFalse(alphabetTurn[0], "the turn passes to the digit thread");

        printAlphabetNumberAlternatively.takeTurn(lock, alphabetTurn, false, out, "1");

        assertEquals("A1", out.toString());
        assertTrue(alphabetTurn[0], "the turn passes back to the alphabet thread");
    }

    @Test
    void takeTurnWaitsUntilTheOtherSideHasWritten() throws Exception {
        Object lock = new Object();
        boolean[] alphabetTurn = {true};
        StringBuilder out = new StringBuilder();

        Thread digit = new Thread(() -> printAlphabetNumberAlternatively.takeTurn(lock, alphabetTurn, false, out, "1"));
        digit.start();
        // the digit thread cannot write yet; give it time to reach wait()
        Thread.sleep(50);
        synchronized (lock) {
            assertEquals("", out.toString());
        }

        printAlphabetNumberAlternatively.takeTurn(lock, alphabetTurn, true, out, "A");
        digit.join(5000);

        assertFalse(digit.isAlive());
        assertEquals("A1", out.toString());
    }

    @Test
    void takeTurnGivesUpWithoutWritingWhenInterruptedWhileWaiting() throws Exception {
        Object lock = new Object();
        boolean[] alphabetTurn = {true};
        StringBuilder out = new StringBuilder();
        AtomicBoolean interruptFlagKept = new AtomicBoolean();

        Thread digit = new Thread(() -> {
            printAlphabetNumberAlternatively.takeTurn(lock, alphabetTurn, false, out, "1");
            interruptFlagKept.set(Thread.currentThread().isInterrupted());
        });
        digit.start();
        digit.interrupt();
        digit.join(5000);

        assertFalse(digit.isAlive());
        assertEquals("", out.toString());
        assertTrue(alphabetTurn[0], "the turn is not handed over");
        assertTrue(interruptFlagKept.get(), "the interrupt is not swallowed");
    }

    @Test
    void mainPrintsTheSequenceWithoutATrailingNewline() throws Exception {
        assertEquals(EXPECTED, Console.capture(() -> printAlphabetNumberAlternatively.main(new String[0])));
    }
}
