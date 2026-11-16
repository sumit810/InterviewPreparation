package functionalInterface;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class Demo {

    static final Greeting HELLO_WORLD = () -> {
        System.out.println("Hello World");
    };

    public static void main(String[] args) {
        Greeting g1 = HELLO_WORLD;
        g1.greet();
    }
}
