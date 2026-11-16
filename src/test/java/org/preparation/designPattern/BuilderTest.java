package org.preparation.designPattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import testsupport.Console;

/** Tests the package-private Employee and its EmployeeBuilder declared in Builder.java. */
class BuilderTest {

    @Test
    void requiredFieldsAloneBuildAnEmployeeWithDefaultsForTheRest() {
        Employee employee = new Employee.EmployeeBuilder("Sumit", 10000).build();

        assertEquals("Sumit", employee.getFirstName());
        assertEquals(10000.0, employee.getSalary());
        assertNull(employee.getLastName());
        assertEquals(0, employee.getAge());
        assertNull(employee.getDesignation());
        assertNull(employee.getAddress());
        assertNull(employee.getDob());
        assertNull(employee.getDoj());
        assertNull(employee.getEmail());
        assertNull(employee.getPhone());
    }

    @Test
    void everyOptionalFieldIsCarriedIntoTheEmployee() {
        Date dob = new Date(1_000L);
        Date doj = new Date(2_000L);

        Employee employee = new Employee.EmployeeBuilder("Sumit", 10000)
                .lastName("Gupta")
                .age(37)
                .designation("Engineer")
                .address("Bangalore")
                .dob(dob)
                .doj(doj)
                .email("sumit@example.com")
                .phone("12345")
                .build();

        assertEquals("Gupta", employee.getLastName());
        assertEquals(37, employee.getAge());
        assertEquals("Engineer", employee.getDesignation());
        assertEquals("Bangalore", employee.getAddress());
        assertEquals(new Date(1_000L), employee.getDob());
        assertEquals(new Date(2_000L), employee.getDoj());
        assertEquals("sumit@example.com", employee.getEmail());
        assertEquals("12345", employee.getPhone());
    }

    @Test
    void requiredFieldsCanBeReplacedBeforeBuilding() {
        Employee employee = new Employee.EmployeeBuilder("Wrong", 1)
                .firstName("Sumit")
                .salary(2)
                .build();

        assertEquals("Sumit", employee.getFirstName());
        assertEquals(2.0, employee.getSalary());
    }

    @Test
    void builderMethodsReturnTheSameBuilderForChaining() {
        Employee.EmployeeBuilder builder = new Employee.EmployeeBuilder("Sumit", 1);

        assertSame(builder, builder.firstName("S"));
        assertSame(builder, builder.salary(2));
        assertSame(builder, builder.lastName("G"));
        assertSame(builder, builder.age(1));
        assertSame(builder, builder.designation("d"));
        assertSame(builder, builder.address("a"));
        assertSame(builder, builder.dob(new Date()));
        assertSame(builder, builder.doj(new Date()));
        assertSame(builder, builder.email("e"));
        assertSame(builder, builder.phone("p"));
    }

    @Test
    void aBuilderCanBeReusedAndLaterChangesDoNotAffectEarlierEmployees() {
        Employee.EmployeeBuilder builder = new Employee.EmployeeBuilder("Sumit", 100).age(30);
        Employee first = builder.build();

        Employee second = builder.age(31).lastName("Gupta").build();

        assertNotSame(first, second);
        assertEquals(30, first.getAge());
        assertNull(first.getLastName());
        assertEquals(31, second.getAge());
        assertEquals("Gupta", second.getLastName());
    }

    @Test
    void changingTheCallersDateAfterItIsPassedInDoesNotChangeTheEmployee() {
        Date dob = new Date(1_000L);
        Date doj = new Date(2_000L);
        Employee.EmployeeBuilder builder = new Employee.EmployeeBuilder("Sumit", 100).dob(dob).doj(doj);

        dob.setTime(999_999L);
        Employee employee = builder.build();
        doj.setTime(999_999L);

        assertEquals(new Date(1_000L), employee.getDob());
        assertEquals(new Date(2_000L), employee.getDoj());
    }

    @Test
    void changingADateReturnedByAGetterDoesNotChangeTheEmployee() {
        Employee employee = new Employee.EmployeeBuilder("Sumit", 100)
                .dob(new Date(1_000L)).doj(new Date(2_000L)).build();

        employee.getDob().setTime(999_999L);
        employee.getDoj().setTime(999_999L);

        assertEquals(new Date(1_000L), employee.getDob());
        assertEquals(new Date(2_000L), employee.getDoj());
    }

    @Test
    void aDateCanBeClearedAgainWithNull() {
        Employee employee = new Employee.EmployeeBuilder("Sumit", 100).dob(new Date()).dob(null).build();

        assertNull(employee.getDob());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "   ", "\t"})
    void firstNameIsRequired(String firstName) {
        Employee.EmployeeBuilder builder = new Employee.EmployeeBuilder(firstName, 100);

        assertThrows(IllegalArgumentException.class, builder::build);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.01, -1000, Double.NEGATIVE_INFINITY, Double.NaN})
    void salaryMustBeANonNegativeNumber(double salary) {
        Employee.EmployeeBuilder builder = new Employee.EmployeeBuilder("Sumit", salary);

        assertThrows(IllegalArgumentException.class, builder::build);
    }

    @Test
    void zeroSalaryAndZeroAgeAreAllowed() {
        Employee employee = new Employee.EmployeeBuilder("Sumit", 0).age(0).build();

        assertEquals(0.0, employee.getSalary());
        assertEquals(0, employee.getAge());
    }

    @Test
    void ageMustNotBeNegative() {
        Employee.EmployeeBuilder builder = new Employee.EmployeeBuilder("Sumit", 100).age(-1);

        assertThrows(IllegalArgumentException.class, builder::build);
    }

    @Test
    void anInvalidBuilderCanBeCorrectedAndThenBuilt() {
        Employee.EmployeeBuilder builder = new Employee.EmployeeBuilder(null, -5);
        assertThrows(IllegalArgumentException.class, builder::build);

        Employee employee = builder.firstName("Sumit").salary(5).build();

        assertEquals("Sumit", employee.getFirstName());
    }

    @Test
    void toStringShowsEveryField() {
        Employee employee = new Employee.EmployeeBuilder("Sumit", 10000).lastName("Gupta").age(37)
                .address("Bangalore").build();

        assertEquals("Employee(firstName=Sumit, salary=10000.0, lastName=Gupta, age=37, designation=null, "
                + "address=Bangalore, dob=null, doj=null, email=null, phone=null)", employee.toString());
    }

    @Test
    void mainPrintsTheBuiltEmployee() throws Exception {
        assertEquals("Employee(firstName=Sumit, salary=10000.0, lastName=Gupta, age=37, designation=null, "
                        + "address=Bangalore, dob=null, doj=null, email=null, phone=null)",
                Console.capture(() -> Builder.main(new String[0])));
    }
}
