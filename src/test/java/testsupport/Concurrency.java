package testsupport;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/** Helpers for tests that need many threads to do the same thing at the same moment. */
public final class Concurrency {

    private Concurrency() {
    }

    /**
     * Has the given number of threads call the supplier at the same instant and returns the distinct
     * values they got back. All threads are parked on a latch first and released together, so they
     * genuinely race instead of running one after another as the pool starts them.
     */
    public static <T> Set<T> valuesSeenByThreadsCallingAtOnce(int threads, Supplier<T> supplier)
            throws InterruptedException {
        Set<T> seen = ConcurrentHashMap.newKeySet();
        CountDownLatch ready = new CountDownLatch(threads);
        CountDownLatch go = new CountDownLatch(1);
        ExecutorService pool = Executors.newFixedThreadPool(threads);
        try {
            for (int i = 0; i < threads; i++) {
                pool.submit(() -> {
                    ready.countDown();
                    go.await();
                    seen.add(supplier.get());
                    return null;
                });
            }
            if (!ready.await(5, TimeUnit.SECONDS)) {
                throw new IllegalStateException("the threads did not start within 5 seconds");
            }
            go.countDown();
        } finally {
            pool.shutdown();
        }
        if (!pool.awaitTermination(10, TimeUnit.SECONDS)) {
            throw new IllegalStateException("the threads did not finish within 10 seconds");
        }
        return seen;
    }
}
