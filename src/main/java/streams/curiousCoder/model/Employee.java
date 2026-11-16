package streams.curiousCoder.model;

import lombok.Data;

// The fields are final, so @Data generates a constructor taking all three, getters, equals, hashCode
// and toString, and no setters.
@Data
public class Employee {

    private final String name;
    private final String department;
    private final int salary;
}
