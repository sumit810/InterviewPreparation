package collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class BookingTest {

    @Test
    void constructorStoresAllFields() {
        Booking booking = new Booking("B1", "U1", 100L);

        assertEquals("B1", booking.getBookingId());
        assertEquals("U1", booking.getUserId());
        assertEquals(100L, booking.getAmount());
    }

    @Test
    void settersReplaceValues() {
        Booking booking = new Booking("B1", "U1", 100L);

        booking.setBookingId("B2");
        booking.setUserId("U2");
        booking.setAmount(250L);

        assertEquals("B2", booking.getBookingId());
        assertEquals("U2", booking.getUserId());
        assertEquals(250L, booking.getAmount());
    }

    @Test
    void fieldsMayBeNull() {
        Booking booking = new Booking(null, null, null);

        assertNull(booking.getBookingId());
        assertNull(booking.getUserId());
        assertNull(booking.getAmount());
    }

    @Test
    void bookingsWithTheSameFieldsAreEqualAndShareAHashCode() {
        Booking first = new Booking("B1", "U1", 100L);
        Booking second = new Booking("B1", "U1", 100L);

        assertEquals(first, second);
        assertEquals(second, first);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void bookingIsEqualToItself() {
        Booking booking = new Booking("B1", "U1", 100L);

        assertEquals(booking, booking);
    }

    @Test
    void bookingsDifferingInAnyOneFieldAreNotEqual() {
        Booking booking = new Booking("B1", "U1", 100L);

        assertNotEquals(booking, new Booking("B2", "U1", 100L));
        assertNotEquals(booking, new Booking("B1", "U2", 100L));
        assertNotEquals(booking, new Booking("B1", "U1", 101L));
    }

    @Test
    void bookingIsNotEqualToNullOrAnotherType() {
        Booking booking = new Booking("B1", "U1", 100L);

        assertNotEquals(booking, null);
        assertNotEquals(booking, "B1");
    }

    @Test
    void equalsAndHashCodeTolerateNullFields() {
        Booking first = new Booking(null, null, null);
        Booking second = new Booking(null, null, null);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
        assertNotEquals(first, new Booking("B1", null, null));
    }

    @Test
    void equalBookingsCollapseInAHashSet() {
        Set<Booking> bookings = new HashSet<>();
        bookings.add(new Booking("B1", "U1", 100L));
        bookings.add(new Booking("B1", "U1", 100L));
        bookings.add(new Booking("B2", "U1", 100L));

        assertEquals(2, bookings.size());
    }

    @Test
    void toStringShowsEveryField() {
        assertEquals("Booking(bookingId=B1, userId=U1, amount=100)", new Booking("B1", "U1", 100L).toString());
    }
}
