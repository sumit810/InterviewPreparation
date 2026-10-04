package threading;

public class printAlphabetNumberAlternatively {

    private static boolean flag = true;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Runnable alphabetTask = new Runnable() {

            public void run() {
                for (char ch = 'A'; ch <= 'Z'; ch++) {
                    synchronized (lock) {
                        if (!flag) {
                            try {
                                lock.wait();
                            } catch (InterruptedException ae) {
                                ae.getMessage();
                            }
                        }
                        System.out.print(ch);
                        flag = false;
                        lock.notifyAll();
                    }
                }
            }
        };

        Runnable digitTask = new Runnable() {

            public void run() {
                for (int i = 1; i <= 26; i++) {
                    synchronized (lock) {
                        if (flag) {
                            try {
                                lock.wait();
                            } catch (InterruptedException ae) {
                                ae.getMessage();
                            }
                        }
                        System.out.print(i);
                        flag = true;
                        lock.notifyAll();
                    }
                }
            }
        };

        Thread t1 = new Thread(alphabetTask, "Alphabet-Thread");
        Thread t2 = new Thread(digitTask, "Digit-Task");
        t1.start();
        t2.start();
    }
}
