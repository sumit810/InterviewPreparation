package org.preparation.designPattern;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingletonTest {

    private static SingletonTest soleInstance = null; //lazy initialisation

    private SingletonTest() {
        System.out.println("Constructing ...");
    }

    private static SingletonTest getInstance() {
        if(soleInstance == null){
            soleInstance = new SingletonTest();
        }
        return soleInstance;
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(SingletonTest::useSingleton);
        executorService.submit(SingletonTest::useSingleton);
        executorService.shutdown();
    }

    private static void useSingleton() {
        SingletonTest singletonTest = SingletonTest.getInstance();
        print("singletonTest", singletonTest);
    }

    private static void print(String name, SingletonTest obj) {
        System.out.printf("Object : %s, Hashcode: %d%n", name, obj.hashCode());
    }
}
