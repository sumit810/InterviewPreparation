package String;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class Permutation{

    public static void main(String... s){

        for(String permutation: permutation("" , "xyz")){
            System.out.println(permutation);
        }
    }

    /**
     * Returns every ordering of the characters of str, each one prefixed with prefix.
     * Repeated characters produce repeated entries: "aab" gives 6 results, not 3.
     */
    public static List<String> permutation(String prefix, String str){
        if(prefix == null || str == null){
            throw new IllegalArgumentException("strings must not be null");
        }
        List<String> result = new ArrayList<>();
        collect(prefix, str, result);
        return result;
    }

    private static void collect(String prefix, String str, List<String> result){
        if(str.isEmpty()){
            result.add(prefix);
            return;
        }

        for(int i = 0 ; i < str.length(); i++){
            String newpref = prefix + str.charAt(i);
            String remaining = str.substring(0, i) + str.substring(i + 1);
            collect(newpref, remaining, result);
        }
    }

}
