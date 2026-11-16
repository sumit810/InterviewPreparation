package org.preparation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/** Checks the things that make org.preparation.Employee immutable through its public API. */
class EmployeeTest {

    @ParameterizedTest
    @ValueSource(ints = {10, 0, -1, Integer.MAX_VALUE, Integer.MIN_VALUE})
    void getIdReturnsTheConstructorArgument(int id) {
        assertEquals(id, new Employee(id).getId());
    }

    @Test
    void classIsFinalSoNoSubclassCanAddMutableState() {
        assertTrue(Modifier.isFinal(Employee.class.getModifiers()));
    }

    @Test
    void everyFieldIsPrivateAndFinal() {
        for (Field field : Employee.class.getDeclaredFields()) {
            if (field.isSynthetic()) {
                continue;
            }
            assertTrue(Modifier.isPrivate(field.getModifiers()), field.getName() + " should be private");
            assertTrue(Modifier.isFinal(field.getModifiers()), field.getName() + " should be final");
        }
    }

    @Test
    void thereAreNoSetters() {
        assertTrue(Arrays.stream(Employee.class.getDeclaredMethods())
                .filter(method -> !method.isSynthetic())
                .map(Method::getName)
                .noneMatch(name -> name.startsWith("set")));
    }

    @Test
    void employeesWithTheSameIdAreEqual() {
        assertEquals(new Employee(7), new Employee(7));
        assertEquals(new Employee(7).hashCode(), new Employee(7).hashCode());
        org.junit.jupiter.api.Assertions.assertNotEquals(new Employee(7), new Employee(8));
        assertEquals("Employee(id=7)", new Employee(7).toString());
    }
}
