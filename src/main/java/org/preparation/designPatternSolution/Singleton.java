package org.preparation.designPatternSolution;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * A singleton that stays a singleton under concurrency, reflection, serialization and cloning.
 */
public class Singleton implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    /**
     * Initialization-on-demand holder: the JVM creates the instance when Holder is first used
     * (so it is lazy) and class initialization is thread-safe (so no locking or volatile is needed).
     */
    private static class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }

    private Singleton(){
        // Guards against reflection. While Holder is creating the one real instance, INSTANCE is still null
        // and this passes. A reflective call lands here first, which makes Holder create the real instance
        // (INSTANCE is then set), so the reflective call is always the one that fails.
        if(Holder.INSTANCE != null){
            throw new IllegalStateException("Cannot create, please use getInstance()");
        }
        System.out.println("Constructing ...");
    }

    public static Singleton getInstance(){
        return Holder.INSTANCE;
    }

    /** A singleton must not be copied: "cloning" it hands back the one instance instead of a new object. */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return getInstance();
    }

    /** Deserialization calls this and uses its result, so the freshly read copy is thrown away. */
    private Object readResolve() throws ObjectStreamException{
        System.out.println(".. read resolve ..!");
        return getInstance();
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
        // in memory instead of a file under /tmp: nothing to clean up and it works on every OS
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(bytes)) {
            oos.writeObject(s1);
        }

        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes.toByteArray()))) {
            Singleton singleton = (Singleton) ois.readObject();
            testingIfTwoObjectsHaveSameHashCode("singleton", singleton);
        }
    }

    private static void violationUsingReflection() throws Exception {
        Class<?> clazz = Class.forName("org.preparation.designPatternSolution.Singleton");
        Constructor<?> ctx = clazz.getDeclaredConstructor();
        ctx.setAccessible(true);
        try {
            ctx.newInstance(); // always fails: the constructor refuses to create a second instance
        } catch (InvocationTargetException e) {
            System.out.println("Reflection blocked : " + e.getCause().getMessage());
        }
    }


    private static void testingIfTwoObjectsHaveSameHashCode(String name, Singleton object) {
            System.out.println(String.format("Object : %s, Hashcode : %s", name, object.hashCode()));
        }
}
