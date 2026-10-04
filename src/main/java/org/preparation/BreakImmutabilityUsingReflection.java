package org.preparation;

import java.lang.reflect.Field;

public class BreakImmutabilityUsingReflection {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        Employee employee = new Employee(10);
        System.out.println("Employee Id: " +employee.getId());
        Field field = Employee.class.getDeclaredField("id");
        field.setAccessible(true);   //breaks encapsulation
        field.set(employee, 99);
        System.out.println(employee.getId());
    }
}
