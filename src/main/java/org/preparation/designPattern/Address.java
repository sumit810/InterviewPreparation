package org.preparation.designPattern;

import lombok.Data;

// @Data generates the getters, setters, equals, hashCode and toString
@Data
public class Address {

    private String address1;
    private String address2;
    private String city;
    private String state;
    private String postalCode;
}
