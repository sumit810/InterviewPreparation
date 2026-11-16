package String;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class PermutationSolution {
    public static void main(String[] args) {

        String str = "abca";
        for(String permutation: findAllPermutations("", str)){
            System.out.println(permutation);
        }
    }

    /**
     * Returns the distinct orderings of the characters of str, each one prefixed with result.
     * Repeated characters do not produce repeated entries: "aab" gives 3 results.
     */
    static List<String> findAllPermutations(String result, String str) {
        if(result == null || str == null){
            throw new IllegalArgumentException("strings must not be null");
        }
        List<String> permutations = new ArrayList<>();
        collect(result, str, permutations);
        return permutations;
    }

    private static void collect(String result, String str, List<String> permutations) {
        if(str.isEmpty()){
            permutations.add(result);
            return;
        }

        Set<Character> set = new HashSet<>();
        for(int i = 0 ; i < str.length(); i++){
            char ch = str.charAt(i);
            if(set.contains(ch)){
                continue;
            }
            set.add(ch);
            String remaining = str.substring(0,i) + str.substring(i+1);
            collect(result + str.charAt(i) , remaining, permutations);
        }
    }
}
