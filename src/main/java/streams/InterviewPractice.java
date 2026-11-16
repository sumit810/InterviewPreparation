package streams;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class InterviewPractice {

    public static void main(String[] args) {

        /*1. Given a word, find the occurrence of each character
        i/p: String s ="Mississippi"
        o/p: {p=2, s=4, i=4, M=1}
         */
        String s = "Mississippi";
        System.out.println("Characters:: " +characterCounts(s));

        /* Given an array find the sum of unique elements
        i/p: int[] arr = {1,6, 7, 8, 1,1 ,8,8,7}
        o/p: 22
         */
        int[] arr = {1,6, 7, 8, 1,1 ,8,8,7};
        System.out.println("Sum of distinct numbers in an array: " +sumOfDistinct(arr));

        /* Given a string, find the first non-repeated character
        i/p: String s = "Hello World"
        o/p: H
         */
        String st ="Hello World";
        System.out.println("First Non-repeating character: " +firstNonRepeatedCharacter(st));

        /* Given a string, find the first repeated character
        i/p: String s = "Hello World"
        o/p: l
         */
        System.out.println("First Repeating Character:" +firstRepeatedCharacter(st));

        /* Given a list of strings, create a list that contains only integers
        i/p: String[] s= {"abc" , "def" ,"123", "652", "xyz"}
        o/p: [123, 652]
         */
        String[] s1 = {"abc" , "def" ,"123", "652", "xyz"};
        System.out.println(integersOnly(s1));

        /* Find the product of the first two elements from an array
        i/p: int[] arr = {12,5,6,9,2,4}
        o/p: 60
         */
        int[] arr1 = {12, 5 , 6, 9 , 2, 4};
        System.out.println(productOfFirstTwo(arr1));

    }

    static Map<Character, Long> characterCounts(String s) {
        return s.chars().mapToObj(ch -> (char) ch).collect(Collectors.groupingBy(x -> x, Collectors.counting()));
    }

    static int sumOfDistinct(int[] arr) {
        return Arrays.stream(arr).distinct().sum();
    }

    /** Returns null when every character repeats (or the string is empty). */
    static Character firstNonRepeatedCharacter(String st) {
        //we have a string, first we are converting string to Intstream
        return st.chars().mapToObj(ch -> (char) ch).collect(Collectors.groupingBy(x -> x, LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream().filter(entry -> entry.getValue() == 1)
                .map(map -> map.getKey())
                .findFirst()
                .orElse(null);
    }

    /** Returns the repeated character that appears earliest in the string, or null when nothing repeats. */
    static Character firstRepeatedCharacter(String st) {
        return st.chars().mapToObj(ch -> (char) ch).collect(Collectors.groupingBy(x->x, LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream().filter(entry -> entry.getValue() > 1)
                .map(x-> x.getKey())
                .findFirst()
                .orElse(null);
    }

    /** Keeps the strings made only of the digits 0-9 whose value fits in an int, in input order. */
    static List<Integer> integersOnly(String[] strings) {
        return Arrays.stream(strings).filter(x -> x.matches("[0-9]+")).filter(InterviewPractice::fitsInInt).map(Integer::valueOf).collect(Collectors.toList());
    }

    // The digits-only regex alone is not enough: "99999999999" matches it and then overflows Integer.valueOf.
    private static boolean fitsInInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /** Returns 1 for an empty array and the element itself for a single-element array. Long, so it cannot overflow. */
    static long productOfFirstTwo(int[] arr) {
        return Arrays.stream(arr).limit(2).asLongStream().reduce(1, (a,b) -> a*b);
    }
}
