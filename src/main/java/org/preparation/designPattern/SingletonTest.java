package org.preparation.designPattern;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Exercise version: a lazy singleton that is deliberately NOT thread-safe. getInstance() has no
 * synchronization, so two threads can both see null and each construct an instance; main shows
 * that by sometimes printing "Constructing ..." twice with two different hash codes.
 * Despite its name this is not a unit test. The thread-safe version is
 * org.preparation.designPatternSolution.Singleton.
 */
public class SingletonTest {

    private static SingletonTest soleInstance = null; //lazy initialisation

    private SingletonTest() {
        System.out.println("Constructing ...");
    }

    static SingletonTest getInstance() {
        if(soleInstance == null){
            soleInstance = new SingletonTest();
        }
        return soleInstance;
    }


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(SingletonTest::useSingleton);
        executorService.submit(SingletonTest::useSingleton);
        executorService.shutdown();
        // wait for both tasks, so main does not return while they are still printing
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }

    private static void useSingleton() {
        SingletonTest singletonTest = SingletonTest.getInstance();
        print("singletonTest", singletonTest);
    }

    private static void print(String name, SingletonTest obj) {
        System.out.printf("Object : %s, Hashcode: %d%n", name, obj.hashCode());
    }
}
