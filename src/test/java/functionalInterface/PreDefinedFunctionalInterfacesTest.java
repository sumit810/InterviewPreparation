package functionalInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import testsupport.Console;

class PreDefinedFunctionalInterfacesTest {

    @ParameterizedTest(name = "isEven({0}) = {1}")
    @CsvSource({"20, true", "21, false", "0, true", "-2, true", "-3, false", "1, false",
            "2147483647, false", "-2147483648, true"})
    void predicateTestsForEvenNumbers(int number, boolean expected) {
        assertEquals(expected, PreDefinedFunctionalInterfaces.IS_EVEN.test(number));
    }

    @Test
    void predicateCanBeNegatedAndCombined() {
        assertEquals(true, PreDefinedFunctionalInterfaces.IS_EVEN.negate().test(21));
        assertEquals(false, PreDefinedFunctionalInterfaces.IS_EVEN.and(n -> n > 100).test(20));
    }

    @ParameterizedTest(name = "length(\"{0}\") = {1}")
    @CsvSource({"sumit gupta, 11", "a, 1", "'', 0", "'  ', 2"})
    void functionReturnsStringLength(String input, int expected) {
        assertEquals(expected, PreDefinedFunctionalInterfaces.LENGTH.apply(input));
    }

    @Test
    void functionAndPredicateFailOnNull() {
        assertThrows(NullPointerException.class, () -> PreDefinedFunctionalInterfaces.LENGTH.apply(null));
        assertThrows(NullPointerException.class, () -> PreDefinedFunctionalInterfaces.IS_EVEN.test(null));
    }

    @Test
    void consumerPrintsTheName() throws Exception {
        assertEquals("my name is: sumit gupta",
                Console.capture(() -> PreDefinedFunctionalInterfaces.PRINT_NAME.accept("sumit gupta")));
        assertEquals("my name is: null",
                Console.capture(() -> PreDefinedFunctionalInterfaces.PRINT_NAME.accept(null)));
    }

    @Test
    void supplierReturnsTheSameValueOnEveryCall() {
        assertEquals("sumitgupta", PreDefinedFunctionalInterfaces.FULL_NAME.get());
        assertEquals("sumitgupta", PreDefinedFunctionalInterfaces.FULL_NAME.get());
    }

    @Test
    void mainPrintsOneLinePerExample() throws Exception {
        assertEquals("true\nfalse\n11\nmy name is: sumit gupta\nsumitgupta",
                Console.capture(() -> PreDefinedFunctionalInterfaces.main(new String[0])));
    }
}
