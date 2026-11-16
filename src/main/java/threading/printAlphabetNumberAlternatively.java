package threading;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class printAlphabetNumberAlternatively {

    public static void main(String[] args) throws InterruptedException {
        System.out.print(alternate());
    }

    /**
     * Runs one thread that writes A..Z and one that writes 1..26, taking strict turns,
     * waits for both and returns what they wrote: A1B2C3...Z26.
     */
    static String alternate() throws InterruptedException {
        StringBuilder out = new StringBuilder();
        Object lock = new Object();
        // true while it is the alphabet thread's turn; only read and written while holding lock
        boolean[] alphabetTurn = {true};

        Runnable alphabetTask = () -> {
            for (char ch = 'A'; ch <= 'Z'; ch++) {
                takeTurn(lock, alphabetTurn, true, out, String.valueOf(ch));
            }
        };

        Runnable digitTask = () -> {
            for (int i = 1; i <= 26; i++) {
                takeTurn(lock, alphabetTurn, false, out, String.valueOf(i));
            }
        };

        Thread t1 = new Thread(alphabetTask, "Alphabet-Thread");
        Thread t2 = new Thread(digitTask, "Digit-Task");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        return out.toString();
    }

    /**
     * Waits until it is this thread's turn, writes text and hands the turn to the other thread.
     * If the thread is interrupted while waiting it writes nothing and returns with its interrupt flag set.
     */
    static void takeTurn(Object lock, boolean[] alphabetTurn, boolean iAmAlphabet, StringBuilder out, String text) {
        synchronized (lock) {
            // "while", not "if": wait() may return without a notify (spurious wakeup),
            // so the condition has to be checked again every time
            while (alphabetTurn[0] != iAmAlphabet) {
                try {
                    lock.wait();
                } catch (InterruptedException ae) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            out.append(text);
            alphabetTurn[0] = !iAmAlphabet;
            lock.notifyAll();
        }
    }
}
