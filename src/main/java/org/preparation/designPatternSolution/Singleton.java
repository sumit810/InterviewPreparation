package org.preparation.designPatternSolution;

import java.io.*;
import java.lang.reflect.Constructor;

public class Singleton implements Serializable, Cloneable {

    private static Singleton soleInstance = null;

    private Singleton(){
        /*if(soleInstance != null){
            throw new RuntimeException("Cannot create, please use getInstance()");
        }*/
        System.out.println("Constructing ...");
    }

    public static Singleton getInstance(){
        if(soleInstance == null){
            soleInstance = new Singleton();
        }
        return soleInstance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    private Object readResolve() throws ObjectStreamException{
        System.out.println(".. read resolve ..!");
        return soleInstance;
    }

    public static void main(String[] args) throws Exception{
            Singleton s1 = Singleton.getInstance();
            Singleton s2 = Singleton.getInstance();

            testingIfTwoObjectsHaveSameHashCode("s1", s1);
            testingIfTwoObjectsHaveSameHashCode("s2", s2);

            //1. Violation using Reflection
            violationUsingReflection();
            //2. Violation using Serialization/de-serialization
            violationUsingSerializationAndDeserilaization(s1);
            //3. Violation using cloning
            violationUsingCloning(s1);

        }



    private static void violationUsingCloning(Singleton singleton) throws Exception{
        Singleton singletonClone = (Singleton) singleton.clone();
        testingIfTwoObjectsHaveSameHashCode("singletonClone", singletonClone);
    }

    private static void violationUsingSerializationAndDeserilaization(Singleton s1) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/tmp/s1.ser"));
        oos.writeObject(s1);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/tmp/s1.ser"));
        Singleton singleton = (Singleton) ois.readObject();
        testingIfTwoObjectsHaveSameHashCode("singleton", singleton);
    }

    private static void violationUsingReflection() throws Exception {
        Class clazz = Class.forName("org.preparation.designPatternSolution.Singleton");
        Constructor<Singleton> ctx = clazz.getDeclaredConstructor();
        ctx.setAccessible(true);
        Singleton singleton = ctx.newInstance();
        testingIfTwoObjectsHaveSameHashCode("singleton", singleton);
    }


    private static void testingIfTwoObjectsHaveSameHashCode(String name, Singleton object) {
            System.out.println(String.format("Object : %s, Hashcode : %s", name, object.hashCode()));
        }
}
