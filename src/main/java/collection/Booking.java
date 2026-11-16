package collection;

import lombok.AllArgsConstructor;
import lombok.Data;

// @Data generates the getters, setters, equals, hashCode and toString
@Data
@AllArgsConstructor
public class Booking {

    private String bookingId;
    private String userId;
    private Long amount;
}
