package problems.hashing;

/*
* Valid Anagram
Problem

Two strings are anagrams if both contain the same characters with the same frequency.

Input:  s = "listen", t = "silent"
Output: true
*
* */

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {

    public static boolean isAnagram(String str1, String str2){
        if(str1.length() != str2.length()){
            return false;
        }

        Map<Character, Integer> freqMap = new HashMap<>();
        for( char ch : str1.toCharArray()){
            freqMap.put(ch, freqMap.getOrDefault(ch, 0 ) + 1);
        }

        for(char ch: str2.toCharArray()){
            if(!freqMap.containsKey(ch)){
                return false;
            }

            freqMap.put(ch, freqMap.get(ch) - 1);
            if(freqMap.get(ch) == 0){
                freqMap.remove(ch);
            }
        }
        return freqMap.isEmpty();
    }


    public static void main(String[] args) {
        System.out.println(isAnagram("listen", "silent"));
        System.out.println(isAnagram("sumit", "timus"));
        System.out.println(isAnagram("sumita", "timuse"));

    }
}
