package org.preparation.designPattern;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Singleton implements Serializable, Cloneable{

    private static Singleton soleInstance = new Singleton(); //eagerly created object

    private Singleton(){
        System.out.println("Creating Singleton ...");
    }

    public static Singleton getInstance(){
        return soleInstance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class TestCase{
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException, CloneNotSupportedException {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        
        print("s1", s1);
        print("s2", s2);

        //1. Violating using Reflection
        Class clazz = Class.forName("org.preparation.designPattern.Singleton");
        Constructor<Singleton> declaredConstructor = clazz.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        Singleton s3 = declaredConstructor.newInstance();
        print("s3", s3);

        //2. Using Serialization/de-serialization
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/tmp/s1.ser"));
        oos.writeObject(s2);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/tmp/s1.ser"));
        Singleton s4 = (Singleton) ois.readObject();
        print("s4", s4);

        //3. using clone
        Singleton s5 = (Singleton) s2.clone();
        print("s5", s5);

    }

    private static void print(String name, Singleton object) {
        System.out.println(String.format("Object : %s, Hashcode: %d", name, object.hashCode()));
    }
}
