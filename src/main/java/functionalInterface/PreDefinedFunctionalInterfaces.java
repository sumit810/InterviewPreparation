package functionalInterface;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class PreDefinedFunctionalInterfaces {
    public static void main(String[] args) {

        Predicate<Integer> p1 = (num) -> {
          if(num%2 == 0){
              return true;
          }else{
              return false;
          }
        };
        System.out.println(p1.test(20));
        System.out.println(p1.test(21));

        Function<String, Integer> result = (str) -> {
            return str.length();
        };
        System.out.println(result.apply("sumit gupta"));

        Consumer<String> c1 = (str) -> {
            System.out.println("my name is: " +str);
        };
        c1.accept("sumit gupta");

        Supplier<String> ans = () -> {
            String s1 = "sumit";
            String s2 = "gupta";
            return s1.concat(s2);
        };
        System.out.println(ans.get());
    }
}
