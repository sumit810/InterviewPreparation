package functionalInterface;

@FunctionalInterface
interface Sum{
    int add(int a, int b);
}

public class SumDemo {
    public static void main(String[] args) {
        Sum result = (a,b) -> {
            return a + b;
        };
        System.out.println(result.add(10, 20));
    }
}
