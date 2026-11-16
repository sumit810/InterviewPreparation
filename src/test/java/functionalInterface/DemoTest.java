package functionalInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import testsupport.Console;

class DemoTest {

    @Test
    void greetingPrintsHelloWorld() throws Exception {
        assertEquals("Hello World", Console.capture(Demo.HELLO_WORLD::greet));
    }

    @Test
    void greetingCanBeImplementedWithAnyLambda() {
        StringBuilder calls = new StringBuilder();
        Greeting greeting = () -> calls.append("hi");

        greeting.greet();
        greeting.greet();

        assertEquals("hihi", calls.toString());
    }

    @Test
    void mainPrintsHelloWorld() throws Exception {
        assertEquals("Hello World", Console.capture(() -> Demo.main(new String[0])));
    }
}
