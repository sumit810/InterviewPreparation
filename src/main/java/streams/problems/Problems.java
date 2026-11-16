package streams.problems;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class Problems {
    public static void main(String[] args) {

        List<Integer> nums1 = List.of(5, 3, 1, 3, 2, 5, 4);
        System.out.println(distinctSorted(nums1));

        List<Integer> nums2 = List.of(1, 2, 3, 4, 5, 6);
        System.out.println("Squares of even number: \n" +squaresOfEven(nums2));


    }

    //Find all unique numbers and sort them in ascending order
    static List<Integer> distinctSorted(List<Integer> nums) {
        return nums.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    //Find square of only even numbers.
    static List<Integer> squaresOfEven(List<Integer> nums) {
        return nums.stream()
                .filter(x ->x%2 ==0)
                .map(x->x*x)
                .collect(Collectors.toList());
    }
}
