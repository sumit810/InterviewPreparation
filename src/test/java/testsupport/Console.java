package testsupport;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/** Captures what a block of code prints to System.out, for smoke-testing main methods. */
public final class Console {

    @FunctionalInterface
    public interface Action {
        void run() throws Exception;
    }

    private Console() {
    }

    /** Runs the action and returns everything it printed, with line endings normalised to \n and trailing whitespace removed. */
    public static synchronized String capture(Action action) throws Exception {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (PrintStream replacement = new PrintStream(buffer, true, StandardCharsets.UTF_8)) {
            System.setOut(replacement);
            action.run();
        } finally {
            System.setOut(original);
        }
        return buffer.toString(StandardCharsets.UTF_8).replace("\r\n", "\n").stripTrailing();
    }

    /**
     * Reads src/test/resources/expected/&lt;name&gt;.txt: the output a main method printed before its logic
     * was extracted into methods, normalised the same way as {@link #capture}.
     */
    public static String expectedOutput(String name) throws IOException {
        try (InputStream in = Console.class.getResourceAsStream("/expected/" + name + ".txt")) {
            if (in == null) {
                throw new IllegalArgumentException("no expected output recorded for " + name);
            }
            return new String(in.readAllBytes(), StandardCharsets.UTF_8).replace("\r\n", "\n").stripTrailing();
        }
    }
}
