package functionalInterface;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@FunctionalInterface
interface Sum{
    int add(int a, int b);
}

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class SumDemo {

    static final Sum ADD = (a,b) -> {
        return a + b;
    };

    public static void main(String[] args) {
        Sum result = ADD;
        System.out.println(result.add(10, 20));
    }
}
