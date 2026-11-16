package functionalInterface;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class PreDefinedFunctionalInterfaces {

    // Predicate: takes a value, returns boolean
    static final Predicate<Integer> IS_EVEN = (num) -> {
        if(num%2 == 0){
            return true;
        }else{
            return false;
        }
    };

    // Function: takes a value, returns a value
    static final Function<String, Integer> LENGTH = (str) -> {
        return str.length();
    };

    // Consumer: takes a value, returns nothing
    static final Consumer<String> PRINT_NAME = (str) -> {
        System.out.println("my name is: " +str);
    };

    // Supplier: takes nothing, returns a value
    static final Supplier<String> FULL_NAME = () -> {
        String s1 = "sumit";
        String s2 = "gupta";
        return s1.concat(s2);
    };

    public static void main(String[] args) {

        Predicate<Integer> p1 = IS_EVEN;
        System.out.println(p1.test(20));
        System.out.println(p1.test(21));

        Function<String, Integer> result = LENGTH;
        System.out.println(result.apply("sumit gupta"));

        Consumer<String> c1 = PRINT_NAME;
        c1.accept("sumit gupta");

        Supplier<String> ans = FULL_NAME;
        System.out.println(ans.get());
    }
}
