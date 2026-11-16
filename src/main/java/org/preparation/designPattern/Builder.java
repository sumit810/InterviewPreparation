package org.preparation.designPattern;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

// Lombok generates the plain getters and toString. getDob() and getDoj() are written by hand below
// because they return copies; Lombok does not generate a getter that already exists.
// The builder itself is written by hand on purpose: this file is the Builder pattern exercise.
@Getter
@ToString
class Employee {
    //required field
    private final String firstName;
    private final double salary;

    //optional field
    private final String lastName;
    private final int age;
    private final String designation;
    private final String address;
    private final Date dob;
    private final Date doj;
    private final String email;
    private final String phone;

    private Employee(EmployeeBuilder builder) {
        this.firstName = builder.firstName;
        this.salary = builder.salary;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.designation = builder.designation;
        this.address = builder.address;
        // safe to share: the builder copied these when they were set, never hands them out and never changes them
        this.dob = builder.dob;
        this.doj = builder.doj;
        this.email = builder.email;
        this.phone = builder.phone;
    }

    // java.util.Date is mutable, so it is never shared with the caller in either direction
    private static Date copyOf(Date date) {
        return date == null ? null : new Date(date.getTime());
    }

    public Date getDob() {
        return copyOf(dob);
    }

    public Date getDoj() {
        return copyOf(doj);
    }


    public static class EmployeeBuilder {

        private String firstName;
        private double salary;

        //optional field
        private String lastName;
        private int age;
        private String designation;
        private String address;
        private Date dob;
        private Date doj;
        private String email;
        private String phone;

        public EmployeeBuilder(String firstName, double salary) {
            this.firstName = firstName;
            this.salary = salary;
        }

        public EmployeeBuilder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public EmployeeBuilder salary(double salary){
            this.salary = salary;
            return this;
        }

        public EmployeeBuilder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public EmployeeBuilder age(int age){
            this.age = age;
            return this;
         }

         public EmployeeBuilder designation(String designation){
            this.designation = designation;
            return this;
         }

         public EmployeeBuilder address(String address){
            this.address = address;
            return this;
         }

         public EmployeeBuilder dob(Date dob){
            this.dob = copyOf(dob);
            return this;
         }

         public EmployeeBuilder doj(Date doj){
            this.doj = copyOf(doj);
            return this;
         }

         public EmployeeBuilder email(String email){
            this.email = email;
            return this;
         }

         public EmployeeBuilder phone(String phone){
            this.phone = phone;
            return this;
         }

         /** Checked here rather than in the setters, so the values can be set in any order and corrected before building. */
         public Employee build(){
            if(firstName == null || firstName.isBlank()){
                throw new IllegalArgumentException("firstName is required");
            }
            // "!(salary >= 0)" rather than "salary < 0" so that NaN is rejected too
            if(!(salary >= 0)){
                throw new IllegalArgumentException("salary must not be negative but was " + salary);
            }
            if(age < 0){
                throw new IllegalArgumentException("age must not be negative but was " + age);
            }
            return new Employee(this);
         }

    }
}

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class Builder {
    public static void main(String[] args) {

        Employee e1 = new Employee.EmployeeBuilder("Sumit", 10000)
                .lastName("Gupta")
                .age(37)
                .address("Bangalore")
                .build();
        System.out.println(e1);
    }
}
