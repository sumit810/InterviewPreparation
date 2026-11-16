package threading;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class DeadlockDemo{

    // the deadlock check scans every thread in the JVM, so it is not run in a tight loop
    private static final long POLL_INTERVAL_MILLIS = 50;

    public static void main(String... s) throws InterruptedException {
        Thread[] threads = startDeadlock();

        System.out.println(describe(threads, awaitDeadlock(threads, 5000)));
        // the two threads are daemons, so the JVM can still exit although they are stuck forever
    }

    static String describe(Thread[] threads, boolean deadlocked) {
        String names = Arrays.stream(threads).map(Thread::getName).collect(Collectors.joining(" and "));
        if(deadlocked){
            return "Deadlock detected: " + names + " each hold the lock the other one is waiting for";
        }
        return "No deadlock detected between " + names;
    }

    /**
     * Starts two threads that are guaranteed to deadlock: Thread-1 takes lock A then wants lock B,
     * Thread-2 takes lock B then wants lock A. Each one waits until the other holds its first lock
     * before asking for the second, so the outcome does not depend on timing.
     */
    static Thread[] startDeadlock() {
        Object lockA = new Object();
        Object lockB = new Object();
        Semaphore thread1HoldsA = new Semaphore(0);
        Semaphore thread2HoldsB = new Semaphore(0);

        Thread t1 = lockInOrder("Thread-1", lockA, "lock A", lockB, "lock B", thread1HoldsA, thread2HoldsB);
        Thread t2 = lockInOrder("Thread-2", lockB, "lock B", lockA, "lock A", thread2HoldsB, thread1HoldsA);

        t1.start();
        t2.start();
        return new Thread[]{t1, t2};
    }

    private static Thread lockInOrder(String threadName, Object first, String firstName, Object second,
                                      String secondName, Semaphore iHoldFirst, Semaphore otherHoldsFirst) {
        Thread thread = new Thread(() -> {
            synchronized(first){
                System.out.println(threadName + " acquired " + firstName);
                iHoldFirst.release();
                otherHoldsFirst.acquireUninterruptibly();

                System.out.println(threadName + " is waiting for " + secondName);
                synchronized(second){
                    // never reached: the other thread holds this lock and is waiting for ours
                }
            }
        }, threadName);
        thread.setDaemon(true);
        return thread;
    }

    /** Checks every POLL_INTERVAL_MILLIS, for at most timeoutMillis, whether all the given threads are deadlocked. */
    static boolean awaitDeadlock(Thread[] threads, long timeoutMillis) throws InterruptedException {
        long deadline = System.currentTimeMillis() + timeoutMillis;
        while(!isDeadlocked(threads)){
            if(System.currentTimeMillis() >= deadline){
                return false;
            }
            Thread.sleep(POLL_INTERVAL_MILLIS);
        }
        return true;
    }

    /** True when at least one thread is given and the JVM reports every one of them as part of a deadlock. */
    static boolean isDeadlocked(Thread... threads) {
        if(threads.length == 0){
            return false;
        }
        // findDeadlockedThreads() returns null, not an empty array, when nothing is deadlocked
        long[] deadlockedIds = ManagementFactory.getThreadMXBean().findDeadlockedThreads();
        if(deadlockedIds == null){
            return false;
        }
        return Arrays.stream(threads)
                .allMatch(thread -> Arrays.stream(deadlockedIds).anyMatch(id -> id == thread.threadId()));
    }
}
