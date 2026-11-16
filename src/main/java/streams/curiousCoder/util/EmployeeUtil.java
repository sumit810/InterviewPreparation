package streams.curiousCoder.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import streams.curiousCoder.model.Employee;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class EmployeeUtil {

    public static List<Employee> getEmployeesList() {
        return Arrays.asList(
                new Employee("Abhishek", "IT", 50000),
                new Employee("Ankit", "IT", 70000),
                new Employee("Rahul", "HR", 40000),
                new Employee("Tina", "HR", 45000),
                new Employee("Esha", "Finance", 60000),
                new Employee("Naman", "Finance", 55000),
                new Employee("Sachit", "IT", 80000),
                new Employee("Push", "Marketing", 50000),
                new Employee("Sumit", "Marketing", 52000)
        );
    }
}