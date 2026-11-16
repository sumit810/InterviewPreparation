package org.preparation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import testsupport.Console;

/** Documents that private + final does not protect a field from reflection (true on JDK 21 for instance fields). */
class BreakImmutabilityUsingReflectionTest {

    @Test
    void reflectionOverwritesThePrivateFinalField() throws Exception {
        Employee employee = new Employee(10);

        BreakImmutabilityUsingReflection.overwriteId(employee, 99);

        assertEquals(99, employee.getId());
    }

    @Test
    void onlyTheTargetedObjectChanges() throws Exception {
        Employee changed = new Employee(1);
        Employee untouched = new Employee(1);

        BreakImmutabilityUsingReflection.overwriteId(changed, -7);

        assertEquals(-7, changed.getId());
        assertEquals(1, untouched.getId());
    }

    @Test
    void mainPrintsIdBeforeAndAfter() throws Exception {
        assertEquals("Employee Id: 10\n99",
                Console.capture(() -> BreakImmutabilityUsingReflection.main(new String[0])));
    }
}
