package org.preparation;

import lombok.Data;

// The field is final, so @Data generates the constructor, the getter, equals, hashCode and toString,
// and no setter.
@Data
public final class Employee {
    private final int id;
}
