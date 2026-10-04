package threading;

public class DeadlockDemo{
    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    public static void main(String... s){

        Thread t1 = new Thread(() -> {

            synchronized(lockA){

                System.out.println("Thread-1 acquired Lock A");
                try{
                    Thread.sleep(2000);
                }catch(InterruptedException ae){
                    ae.getMessage();
                }

                System.out.println("Thread-1 is waiting for lockB");

                synchronized(lockB){
                    System.out.println("Thread-1 acquired lock B");
                }
            }
        }, "Thread-1");

        Thread t2 = new Thread(() -> {

            synchronized(lockA){

                System.out.println("Thread-2 acquired Lock B");
                try{
                    Thread.sleep(2000);
                }catch(InterruptedException ae){
                    ae.getMessage();
                }

                System.out.println("Thread-2 is waiting for lockA");

                synchronized(lockB){
                    System.out.println("Thread-2 acquired lock A");
                }
            }
        }, "Thread-2");

        t1.start();
        t2.start();


    }
}