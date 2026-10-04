package streams.problems;

import java.util.List;
import java.util.stream.Collectors;

public class Problems {
    public static void main(String[] args) {

        List<Integer> nums1 = List.of(5, 3, 1, 3, 2, 5, 4);
        //Find all unique numbers and sort them in ascending order
        List<Integer> ans1 = nums1.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(ans1);

        List<Integer> nums2 = List.of(1, 2, 3, 4, 5, 6);
        //Find square of only even numbers.
        List<Integer> ans2 = nums2.stream()
                .filter(x ->x%2 ==0)
                .map(x->x*x)
                .collect(Collectors.toList());
        System.out.println("Squares of even number: \n" +ans2);


    }
}
