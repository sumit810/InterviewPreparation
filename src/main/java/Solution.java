import java.util.concurrent.atomic.AtomicInteger;

public class Solution {

    private static final AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        Runnable r1 = () -> {
            for(int i = 0 ; i < 100000; i++){
                count.incrementAndGet();
            }
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r1);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(count.get());
    }
}
