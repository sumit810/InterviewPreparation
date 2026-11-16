import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class Solution {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(countConcurrently(2, 100000));
    }

    /**
     * Starts the given number of threads, has each one increment a shared counter incrementsPerThread times,
     * waits for all of them and returns the final count. AtomicInteger makes each increment atomic,
     * so the result is always threads * incrementsPerThread.
     */
    static int countConcurrently(int threads, int incrementsPerThread) throws InterruptedException {
        if(threads < 0 || incrementsPerThread < 0){
            throw new IllegalArgumentException("threads and incrementsPerThread must not be negative");
        }

        // local, not static: every call starts from zero
        AtomicInteger count = new AtomicInteger(0);

        Runnable r1 = () -> {
            for(int i = 0 ; i < incrementsPerThread; i++){
                count.incrementAndGet();
            }
        };

        Thread[] workers = new Thread[threads];
        for(int i = 0 ; i < threads; i++){
            workers[i] = new Thread(r1);
            workers[i].start();
        }

        for(Thread worker: workers){
            worker.join();
        }

        return count.get();
    }
}
