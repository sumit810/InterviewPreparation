package org.preparation.designPattern;

class User{
    private String firstName;
    private double salary;
    private String lastName;
    private String address;

    private User(UserBuilder builder){
        this.firstName = builder.firstName;
        this.salary = builder.salary;
        this.lastName = builder.lastName;
        this.address = builder.address;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", salary=" + salary +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                '}';
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

        public User build(){
            return new User(this);
        }
    }
}


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