package collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import testsupport.Console;

class BookingEventsTest {

    /** Totals: U2 = 300, U1 = 250, U3 = 250, U4 = 220. */
    private static List<Booking> sample() {
        return List.of(
                new Booking("B1", "U1", 100L),
                new Booking("B2", "U2", 200L),
                new Booking("B3", "U3", 250L),
                new Booking("B4", "U1", 150L),
                new Booking("B5", "U4", 120L),
                new Booking("B6", "U2", 100L),
                new Booking("B7", "U4", 100L));
    }

    @ParameterizedTest(name = "k = {0} -> {1}")
    @CsvSource(delimiter = '|', textBlock = """
            0 |
            1 | U2
            2 | U2,U1
            3 | U2,U1,U3
            4 | U2,U1,U3,U4
            9 | U2,U1,U3,U4
            """)
    void returnsTopUsersHighestTotalFirst(int k, String expected) {
        List<String> expectedUsers = expected == null ? List.of() : Arrays.asList(expected.split(","));

        assertEquals(expectedUsers, BookingEvents.topKUsers(sample(), k));
    }

    @Test
    void sumsAllBookingsOfAUser() {
        List<Booking> bookings = List.of(
                new Booking("B1", "small", 10L),
                new Booking("B2", "big", 100L),
                new Booking("B3", "small", 10L),
                new Booking("B4", "small", 90L));

        assertEquals(List.of("small", "big"), BookingEvents.topKUsers(bookings, 2));
    }

    @Test
    void equalTotalsAreOrderedByUserIdWhateverTheInputOrder() {
        List<Booking> bookings = List.of(
                new Booking("B1", "C", 50L),
                new Booking("B2", "A", 50L),
                new Booking("B3", "B", 50L));

        assertEquals(List.of("A", "B", "C"), BookingEvents.topKUsers(bookings, 3));
    }

    @Test
    void equalTotalsAtTheCutOffKeepTheSmallerUserId() {
        List<Booking> bookings = List.of(
                new Booking("B1", "C", 50L),
                new Booking("B2", "A", 50L),
                new Booking("B3", "B", 50L));

        assertEquals(List.of("A", "B"), BookingEvents.topKUsers(bookings, 2));
        assertEquals(List.of("A"), BookingEvents.topKUsers(bookings, 1));
    }

    @Test
    void emptyListHasNoTopUsers() {
        assertEquals(List.of(), BookingEvents.topKUsers(List.of(), 3));
    }

    @Test
    void singleBooking() {
        assertEquals(List.of("U1"), BookingEvents.topKUsers(List.of(new Booking("B1", "U1", 5L)), 1));
    }

    @Test
    void negativeAmountsLowerTheTotal() {
        List<Booking> bookings = List.of(
                new Booking("B1", "refunded", 100L),
                new Booking("B2", "refunded", -90L),
                new Booking("B3", "steady", 20L));

        assertEquals(List.of("steady", "refunded"), BookingEvents.topKUsers(bookings, 2));
    }

    @Test
    void doesNotModifyTheInputList() {
        List<Booking> bookings = new ArrayList<>(sample());
        List<Booking> before = new ArrayList<>(bookings);

        BookingEvents.topKUsers(bookings, 2);

        assertEquals(before, bookings);
    }

    @Test
    void rejectsNullList() {
        assertThrows(IllegalArgumentException.class, () -> BookingEvents.topKUsers(null, 1));
    }

    @Test
    void rejectsNegativeK() {
        assertThrows(IllegalArgumentException.class, () -> BookingEvents.topKUsers(sample(), -1));
    }

    @Test
    void rejectsNullBooking() {
        List<Booking> bookings = Arrays.asList(new Booking("B1", "U1", 5L), null);

        assertThrows(IllegalArgumentException.class, () -> BookingEvents.topKUsers(bookings, 1));
    }

    @Test
    void rejectsBookingWithoutUserId() {
        List<Booking> bookings = List.of(new Booking("B1", null, 5L));

        assertThrows(IllegalArgumentException.class, () -> BookingEvents.topKUsers(bookings, 1));
    }

    @Test
    void rejectsBookingWithoutAmount() {
        List<Booking> bookings = List.of(new Booking("B1", "U1", null));

        assertThrows(IllegalArgumentException.class, () -> BookingEvents.topKUsers(bookings, 1));
    }

    @Test
    void mainPrintsTopThreeUsersHighestFirst() throws Exception {
        assertEquals("[U2, U1, U3]", Console.capture(() -> BookingEvents.main(new String[0])));
    }
}
