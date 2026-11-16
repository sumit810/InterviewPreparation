package streams.curiousCoder.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import streams.curiousCoder.model.Employee;

class EmployeeUtilTest {

    @Test
    void returnsTheNineSampleEmployeesInOrder() {
        List<Employee> employees = EmployeeUtil.getEmployeesList();

        assertEquals(List.of("Abhishek", "Ankit", "Rahul", "Tina", "Esha", "Naman", "Sachit", "Push", "Sumit"),
                employees.stream().map(Employee::getName).collect(Collectors.toList()));
        assertEquals(List.of("IT", "IT", "HR", "HR", "Finance", "Finance", "IT", "Marketing", "Marketing"),
                employees.stream().map(Employee::getDepartment).collect(Collectors.toList()));
        assertEquals(List.of(50000, 70000, 40000, 45000, 60000, 55000, 80000, 50000, 52000),
                employees.stream().map(Employee::getSalary).collect(Collectors.toList()));
    }

    @Test
    void everyCallBuildsFreshObjectsSoCallersCannotAffectEachOther() {
        List<Employee> first = EmployeeUtil.getEmployeesList();
        List<Employee> second = EmployeeUtil.getEmployeesList();

        assertNotSame(first, second);
        first.set(0, new Employee("Changed", "None", 1));

        assertEquals("Abhishek", second.get(0).getName());
        assertEquals("Abhishek", EmployeeUtil.getEmployeesList().get(0).getName());
        assertTrue(first.get(1) != second.get(1));
    }
}
