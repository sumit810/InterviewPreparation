package functionalInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import testsupport.Console;

class SumDemoTest {

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({"10, 20, 30", "0, 0, 0", "-5, 5, 0", "-3, -4, -7", "2147483647, 0, 2147483647"})
    void addsTwoNumbers(int a, int b, int expected) {
        assertEquals(expected, SumDemo.ADD.add(a, b));
    }

    @Test
    void additionWrapsAroundLikeAnyIntArithmetic() {
        assertEquals(Integer.MIN_VALUE, SumDemo.ADD.add(Integer.MAX_VALUE, 1));
    }

    @Test
    void mainPrintsThirty() throws Exception {
        assertEquals("30", Console.capture(() -> SumDemo.main(new String[0])));
    }
}
