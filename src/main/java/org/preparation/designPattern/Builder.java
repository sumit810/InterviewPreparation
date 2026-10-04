package org.preparation.designPattern;

import java.util.Date;

class Employee {
    //required field
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

    private Employee(EmployeeBuilder builder) {
        this.firstName = builder.firstName;
        this.salary = builder.salary;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.designation = builder.designation;
        this.address = builder.address;
        this.dob = builder.dob;
        this.doj = builder.doj;
        this.email = builder.email;
        this.phone = builder.phone;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", salary=" + salary +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", designation='" + designation + '\'' +
                ", address='" + address + '\'' +
                ", dob=" + dob +
                ", doj=" + doj +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
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
            this.dob = dob;
            return this;
         }

         public EmployeeBuilder doj(Date doj){
            this.doj = doj;
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

         public Employee build(){
            return new Employee(this);
         }

    }
}

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
