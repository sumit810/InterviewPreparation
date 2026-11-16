package streams.curiousCoder.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class EmployeeTest {

    @Test
    void gettersReturnConstructorArguments() {
        Employee employee = new Employee("Sumit", "Marketing", 52000);

        assertEquals("Sumit", employee.getName());
        assertEquals("Marketing", employee.getDepartment());
        assertEquals(52000, employee.getSalary());
    }

    @Test
    void toStringShowsEveryField() {
        assertEquals("Employee(name=Sumit, department=Marketing, salary=52000)",
                new Employee("Sumit", "Marketing", 52000).toString());
    }

    @Test
    void employeesWithTheSameFieldsAreEqualAndShareAHashCode() {
        Employee first = new Employee("Sumit", "Marketing", 52000);
        Employee second = new Employee("Sumit", "Marketing", 52000);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void employeesDifferingInAnyOneFieldAreNotEqual() {
        Employee employee = new Employee("Sumit", "Marketing", 52000);

        assertNotEquals(employee, new Employee("Other", "Marketing", 52000));
        assertNotEquals(employee, new Employee("Sumit", "IT", 52000));
        assertNotEquals(employee, new Employee("Sumit", "Marketing", 52001));
        assertNotEquals(employee, null);
    }

    @Test
    void thereAreNoSettersBecauseTheFieldsAreFinal() {
        assertTrue(Arrays.stream(Employee.class.getDeclaredMethods())
                .noneMatch(method -> method.getName().startsWith("set")));
    }
}
