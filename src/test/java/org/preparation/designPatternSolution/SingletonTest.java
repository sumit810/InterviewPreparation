package org.preparation.designPatternSolution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import testsupport.Concurrency;
import testsupport.Console;

/** The hardened singleton must survive every attack the exercise version falls to. The concurrent test runs first so that it races on the very first creation. */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SingletonTest {

    @Test
    @Order(1)
    void manyThreadsCallingAtOnceAllGetTheSameInstance() throws Exception {
        Set<Singleton> seen = Concurrency.valuesSeenByThreadsCallingAtOnce(50, Singleton::getInstance);

        assertEquals(1, seen.size(), "more than one instance was created");
    }

    @Test
    @Order(2)
    void repeatedCallsReturnTheSameInstance() {
        Singleton first = Singleton.getInstance();

        assertNotNull(first);
        assertSame(first, Singleton.getInstance());
    }

    @Test
    @Order(3)
    void reflectionCannotCreateASecondInstance() throws Exception {
        Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException thrown = assertThrows(InvocationTargetException.class, constructor::newInstance);

        assertInstanceOf(IllegalStateException.class, thrown.getCause());
        assertEquals("Cannot create, please use getInstance()", thrown.getCause().getMessage());
    }

    @Test
    @Order(4)
    void deserializationReturnsTheExistingInstance() throws Exception {
        Singleton original = Singleton.getInstance();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(bytes)) {
            out.writeObject(original);
        }

        Object copy;
        try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes.toByteArray()))) {
            copy = in.readObject();
        }

        assertSame(original, copy);
    }

    @Test
    @Order(5)
    void cloningReturnsTheExistingInstance() throws Exception {
        Singleton singleton = Singleton.getInstance();

        assertSame(singleton, singleton.clone());
    }

    @Test
    @Order(6)
    void mainShowsEveryAttackFailing() throws Exception {
        // create the instance first, so its one-off "Constructing ..." line is not part of the captured output
        String realHash = String.valueOf(Singleton.getInstance().hashCode());
        String output = Console.capture(() -> Singleton.main(new String[0]));
        List<String> lines = List.of(output.split("\n"));

        assertEquals(List.of(
                "Object : s1, Hashcode : " + realHash,
                "Object : s2, Hashcode : " + realHash,
                "Reflection blocked : Cannot create, please use getInstance()",
                ".. read resolve ..!",
                "Object : singleton, Hashcode : " + realHash,
                "Object : singletonClone, Hashcode : " + realHash), lines);
    }
}
