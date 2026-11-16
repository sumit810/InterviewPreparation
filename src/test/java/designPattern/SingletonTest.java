package designPattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import testsupport.Concurrency;
import testsupport.Console;

/** Tests the double-checked-locking singleton. The concurrent test runs first so that it races on the very first creation. */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SingletonTest {

    @Test
    @Order(1)
    void manyThreadsCallingAtOnceAllGetTheSameInstance() throws Exception {
        Set<Singleton> seen = Concurrency.valuesSeenByThreadsCallingAtOnce(50, Singleton::getInstance);

        assertEquals(1, seen.size(), "more than one instance was created");
    }

    @Test
    @Order(2)
    void repeatedCallsReturnTheSameInstance() {
        Singleton first = Singleton.getInstance();

        assertNotNull(first);
        assertSame(first, Singleton.getInstance());
    }

    @Test
    @Order(3)
    void mainPrintsThatBothReferencesAreTheSameObject() throws Exception {
        String output = Console.capture(() -> Singleton.main());

        assertTrue(output.endsWith("true"), output);
    }
}
