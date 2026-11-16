package org.preparation.designPattern;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Exercise version: an eager singleton that is deliberately left open to the three classic attacks
 * (reflection, serialization, cloning) so that TestCase can demonstrate them.
 * The hardened version is org.preparation.designPatternSolution.Singleton.
 */
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

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
class TestCase{
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException, CloneNotSupportedException {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        
        print("s1", s1);
        print("s2", s2);

        //1. Violating using Reflection
        Singleton s3 = newInstanceUsingReflection();
        print("s3", s3);

        //2. Using Serialization/de-serialization
        Singleton s4 = copyUsingSerialization(s2);
        print("s4", s4);

        //3. using clone
        Singleton s5 = copyUsingClone(s2);
        print("s5", s5);

    }

    /** The private constructor does not stop reflection: this returns a second instance. */
    static Singleton newInstanceUsingReflection() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName("org.preparation.designPattern.Singleton");
        Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        return (Singleton) declaredConstructor.newInstance();
    }

    /** Without readResolve(), deserialization builds a new object: this returns another instance. */
    static Singleton copyUsingSerialization(Singleton singleton) throws IOException, ClassNotFoundException {
        // in memory instead of a file under /tmp: nothing to clean up and it works on every OS
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(bytes)) {
            oos.writeObject(singleton);
        }

        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes.toByteArray()))) {
            return (Singleton) ois.readObject();
        }
    }

    /** clone() just calls super.clone(): this returns another instance. */
    static Singleton copyUsingClone(Singleton singleton) throws CloneNotSupportedException {
        return (Singleton) singleton.clone();
    }

    private static void print(String name, Singleton object) {
        System.out.println(String.format("Object : %s, Hashcode: %d", name, object.hashCode()));
    }
}
