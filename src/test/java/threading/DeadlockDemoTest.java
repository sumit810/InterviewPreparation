package threading;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import testsupport.Console;

/**
 * The deadlocked threads are daemons and stay blocked until the test JVM exits; that is expected.
 * The first test must run before any deadlock exists, hence the explicit order.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DeadlockDemoTest {

    @Test
    @Order(1)
    void aRunningThreadIsNotReportedAsDeadlocked() throws Exception {
        Thread[] current = {Thread.currentThread()};

        assertFalse(DeadlockDemo.isDeadlocked(current));
        assertFalse(DeadlockDemo.isDeadlocked(), "no threads is not a deadlock");
        assertFalse(DeadlockDemo.awaitDeadlock(current, 30), "gives up after the timeout");
    }

    @Test
    @Order(2)
    void theTwoThreadsDeadlockEveryTime() throws Exception {
        for (int run = 0; run < 5; run++) {
            Thread[] threads = DeadlockDemo.startDeadlock();

            assertTrue(assertTimeoutPreemptively(Duration.ofSeconds(10), () -> DeadlockDemo.awaitDeadlock(threads, 5000)),
                    "run " + run + " did not deadlock");
            assertEquals(2, threads.length);
            for (Thread thread : threads) {
                assertTrue(thread.isAlive());
                assertTrue(thread.isDaemon(), "daemon threads cannot keep the JVM alive");
                assertEquals(Thread.State.BLOCKED, thread.getState());
            }
            assertEquals(List.of("Thread-1", "Thread-2"), Arrays.stream(threads).map(Thread::getName).toList());
        }
    }

    @Test
    @Order(3)
    void otherThreadsAreNotReportedWhileADeadlockExists() throws Exception {
        Thread[] deadlocked = DeadlockDemo.startDeadlock();
        assertTrue(DeadlockDemo.awaitDeadlock(deadlocked, 5000));

        assertFalse(DeadlockDemo.isDeadlocked(Thread.currentThread()));
        assertFalse(DeadlockDemo.isDeadlocked(deadlocked[0], Thread.currentThread()),
                "every given thread has to be deadlocked");
        assertTrue(DeadlockDemo.isDeadlocked(deadlocked[0]));
        assertTrue(DeadlockDemo.isDeadlocked(deadlocked[1]));
    }

    @Test
    @Order(4)
    void mainReportsTheDeadlockAndReturns() {
        String output = assertTimeoutPreemptively(Duration.ofSeconds(10),
                () -> Console.capture(() -> DeadlockDemo.main()));

        List<String> lines = List.of(output.split("\n"));
        assertEquals(5, lines.size(), output);
        assertTrue(lines.contains("Thread-1 acquired lock A"), output);
        assertTrue(lines.contains("Thread-2 acquired lock B"), output);
        assertTrue(lines.contains("Thread-1 is waiting for lock B"), output);
        assertTrue(lines.contains("Thread-2 is waiting for lock A"), output);
        assertEquals("Deadlock detected: Thread-1 and Thread-2 each hold the lock the other one is waiting for",
                lines.get(4));
        assertTrue(lines.indexOf("Thread-1 acquired lock A") < lines.indexOf("Thread-1 is waiting for lock B"));
        assertTrue(lines.indexOf("Thread-2 acquired lock B") < lines.indexOf("Thread-2 is waiting for lock A"));
    }

    @Test
    void describeNamesBothThreadsInEitherOutcome() {
        Thread[] threads = {new Thread(() -> { }, "first"), new Thread(() -> { }, "second")};

        assertEquals("Deadlock detected: first and second each hold the lock the other one is waiting for",
                DeadlockDemo.describe(threads, true));
        assertEquals("No deadlock detected between first and second", DeadlockDemo.describe(threads, false));
        assertEquals("No deadlock detected between first", DeadlockDemo.describe(new Thread[]{threads[0]}, false));
    }
}
