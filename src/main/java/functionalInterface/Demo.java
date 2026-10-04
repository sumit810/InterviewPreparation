package functionalInterface;

public class Demo {
    public static void main(String[] args) {
        Greeting g1 = () -> {
            System.out.println("Hello World");
        };
        g1.greet();
    }
}
