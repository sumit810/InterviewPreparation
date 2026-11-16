package org.preparation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class BreakImmutabilityUsingReflection {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        Employee employee = new Employee(10);
        System.out.println("Employee Id: " +employee.getId());
        overwriteId(employee, 99);
        System.out.println(employee.getId());
    }

    /** Changes the private final id of an "immutable" Employee. */
    static void overwriteId(Employee employee, int newId) throws NoSuchFieldException, IllegalAccessException {
        Field field = Employee.class.getDeclaredField("id");
        field.setAccessible(true);   //breaks encapsulation
        field.set(employee, newId);
    }
}
