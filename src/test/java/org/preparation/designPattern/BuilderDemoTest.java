package org.preparation.designPattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import testsupport.Console;

/** Tests the package-private User and its UserBuilder declared in BuilderDemo.java. */
class BuilderDemoTest {

    @Test
    void anEmptyBuilderGivesAUserWithDefaults() {
        User user = new User.UserBuilder().build();

        assertNull(user.getFirstName());
        assertNull(user.getLastName());
        assertNull(user.getAddress());
        assertEquals(0.0, user.getSalary());
    }

    @Test
    void everyFieldIsCarriedIntoTheUser() {
        User user = new User.UserBuilder()
                .firstName("Sumit")
                .lastName("Gupta")
                .salary(3242879)
                .address("Bangalore")
                .build();

        assertEquals("Sumit", user.getFirstName());
        assertEquals("Gupta", user.getLastName());
        assertEquals(3242879.0, user.getSalary());
        assertEquals("Bangalore", user.getAddress());
    }

    @Test
    void theLastValueSetForAFieldWins() {
        User user = new User.UserBuilder().firstName("A").firstName("B").build();

        assertEquals("B", user.getFirstName());
    }

    @Test
    void builderMethodsReturnTheSameBuilderForChaining() {
        User.UserBuilder builder = new User.UserBuilder();

        assertSame(builder, builder.firstName("S"));
        assertSame(builder, builder.salary(1));
        assertSame(builder, builder.lastName("G"));
        assertSame(builder, builder.address("a"));
    }

    @Test
    void aBuilderCanBeReusedAndLaterChangesDoNotAffectEarlierUsers() {
        User.UserBuilder builder = new User.UserBuilder().firstName("Sumit");
        User first = builder.build();

        User second = builder.firstName("Other").build();

        assertNotSame(first, second);
        assertEquals("Sumit", first.getFirstName());
        assertEquals("Other", second.getFirstName());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.01, -1000, Double.NEGATIVE_INFINITY, Double.NaN})
    void salaryMustBeANonNegativeNumber(double salary) {
        User.UserBuilder builder = new User.UserBuilder().salary(salary);

        assertThrows(IllegalArgumentException.class, builder::build);
    }

    @Test
    void toStringShowsEveryField() {
        assertEquals("User(firstName=Sumit, salary=5.0, lastName=Gupta, address=Bangalore)",
                new User.UserBuilder().firstName("Sumit").salary(5).lastName("Gupta").address("Bangalore")
                        .build().toString());
    }

    @Test
    void mainPrintsTheBuiltUser() throws Exception {
        assertEquals("User(firstName=Sumit, salary=3242879.0, lastName=Gupta, address=null)",
                Console.capture(() -> BuilderDemo.main()));
    }
}
