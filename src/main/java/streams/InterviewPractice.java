package streams;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InterviewPractice {

    public static void main(String[] args) {

        /*1. Given a word, find the occurrence of each character
        i/p: String s ="Mississippi"
        o/p: {p=2, s=4, i=4, M=1}
         */
        String s = "Mississippi";
        Map<Character, Long> count = s.chars().mapToObj(ch -> (char) ch).collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        System.out.println("Characters:: " +count);

        /* Given an array find the sum of unique elements
        i/p: int[] arr = {1,6, 7, 8, 1,1 ,8,8,7}
        o/p: 22
         */
        int[] arr = {1,6, 7, 8, 1,1 ,8,8,7};
        int sum = Arrays.stream(arr).distinct().sum();
        System.out.println("Sum of distinct numbers in an array: " +sum);

        /* Given a string, find the first non-repeated character
        i/p: String s = "Hello World"
        o/p: H
         */
        String st ="Hello World";
        //we have a string, first we are converting string to Intstream
        Character result = st.chars().mapToObj(ch -> (char) ch).collect(Collectors.groupingBy(x -> x, LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream().filter(entry -> entry.getValue() == 1)
                .map(map -> map.getKey())
                .findFirst()
                .orElse(null);
        System.out.println("First Non-repeating character: " +result);

        /* Given a string, find the first repeated character
        i/p: String s = "Hello World"
        o/p: l
         */
        Character answer = st.chars().mapToObj(ch -> (char) ch).collect(Collectors.groupingBy(x->x, LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream().filter(entry -> entry.getValue() > 1)
                .map(x-> x.getKey())
                .findFirst()
                .orElse(null);
        System.out.println("First Repeating Character:" +answer);

        /* Given a list of strings, create a list that contains only integers
        i/p: String[] s= {"abc" , "def" ,"123", "652", "xyz"}
        o/p: [123, 652]
         */
        String[] s1 = {"abc" , "def" ,"123", "652", "xyz"};
        List<Integer> ans = Arrays.stream(s1).filter(x -> x.matches("[0-9]+")).map(Integer::valueOf).collect(Collectors.toList());
        System.out.println(ans);

        /* Find the product of the first two elements from an array
        i/p: int[] arr = {12,5,6,9,2,4}
        o/p: 60
         */
        int[] arr1 = {12, 5 , 6, 9 , 2, 4};
        int a1 = Arrays.stream(arr1).boxed().collect(Collectors.toList())
                .stream().limit(2).reduce(1, (a,b) -> a*b);
        System.out.println(a1);

    }
}
