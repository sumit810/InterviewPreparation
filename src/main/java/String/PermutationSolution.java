package String;

import java.util.HashSet;
import java.util.Set;

public class PermutationSolution {
    public static void main(String[] args) {

        String str = "abca";
        findAllPermutations(" ", str);
    }

    private static void findAllPermutations(String result, String str) {
        if(str.isEmpty()){
            System.out.println(result);
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
            findAllPermutations(result + str.charAt(i) , remaining);
        }
    }
}
