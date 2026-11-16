package org.preparation.designPattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class AddressTest {

    @Test
    void newAddressHasNoValues() {
        Address address = new Address();

        assertNull(address.getAddress1());
        assertNull(address.getAddress2());
        assertNull(address.getCity());
        assertNull(address.getState());
        assertNull(address.getPostalCode());
    }

    @Test
    void settersStoreValuesIndependently() {
        Address address = new Address();

        address.setAddress1("12 MG Road");
        address.setAddress2("Floor 3");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setPostalCode("560001");

        assertEquals("12 MG Road", address.getAddress1());
        assertEquals("Floor 3", address.getAddress2());
        assertEquals("Bangalore", address.getCity());
        assertEquals("Karnataka", address.getState());
        assertEquals("560001", address.getPostalCode());
    }

    @Test
    void toStringShowsEveryField() {
        Address address = new Address();
        address.setAddress1("12 MG Road");
        address.setCity("Bangalore");
        address.setPostalCode("560001");

        assertEquals("Address(address1=12 MG Road, address2=null, city=Bangalore, state=null, postalCode=560001)",
                address.toString());
    }

    @Test
    void addressesWithTheSameValuesAreEqual() {
        Address first = new Address();
        first.setCity("Bangalore");
        Address second = new Address();
        second.setCity("Bangalore");
        Address other = new Address();
        other.setCity("Delhi");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
        org.junit.jupiter.api.Assertions.assertNotEquals(first, other);
    }
}
