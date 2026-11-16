package org.preparation.designPattern;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import lombok.Getter;
import lombok.ToString;

// Lombok generates the getters and toString.
// The builder itself is written by hand on purpose: this file is the Builder pattern exercise.
@Getter
@ToString
class User{
    private final String firstName;
    private final double salary;
    private final String lastName;
    private final String address;

    private User(UserBuilder builder){
        this.firstName = builder.firstName;
        this.salary = builder.salary;
        this.lastName = builder.lastName;
        this.address = builder.address;
    }

    public static class UserBuilder{
        private String firstName;
        private double salary;
        private String lastName;
        private String address;

        public UserBuilder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public UserBuilder salary(double salary){
            this.salary = salary;
            return this;
        }

        public UserBuilder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public UserBuilder address(String address){
            this.address = address;
            return this;
        }

        /** Every field is optional; the only rule is that a salary cannot be negative. */
        public User build(){
            // "!(salary >= 0)" rather than "salary < 0" so that NaN is rejected too
            if(!(salary >= 0)){
                throw new IllegalArgumentException("salary must not be negative but was " + salary);
            }
            return new User(this);
        }
    }
}


@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class BuilderDemo {
    public static void main(String... s){
        User u1 = new User.UserBuilder()
                .firstName("Sumit")
                .lastName("Gupta")
                .salary(3242879)
                .build();
        System.out.println(u1);
    }
}
