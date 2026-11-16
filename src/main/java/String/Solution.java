package String;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Comparator;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class Solution {
    public static void main(String[] args) {
        String s = "Java is Amazing";
        System.out.println(sortWords(s));
    }

    /** Returns the words of str ordered by length; words of equal length keep their original order. */
    static String sortWords(String str) {
        if(str == null){
            throw new IllegalArgumentException("str must not be null");
        }
        // strip first: split would otherwise give an empty first word for leading whitespace
        String[] words = str.strip().split("\\s+");
        Arrays.sort(words, Comparator.comparingInt(String::length));

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < words.length; i++){
            if(i> 0){
                sb.append(" ");
            }
            sb.append(words[i]);
        }
        return sb.toString();
    }
}
