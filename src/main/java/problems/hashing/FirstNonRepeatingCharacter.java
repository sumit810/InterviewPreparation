package problems.hashing;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/*
* First Non-Repeating Character
Problem

Find first character whose frequency is 1.

Input:  "aabbcdde"
Output: 'c'
* */

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class FirstNonRepeatingCharacter {

    public static Character findFirstNonRepeatingCharacter(String str){
        if(str == null){
            throw new IllegalArgumentException("str must not be null");
        }
        Map<Character, Integer> freqMap = new HashMap<>();

        for(char ch: str.toCharArray()){
            freqMap.put(ch, freqMap.getOrDefault(ch, 0 ) + 1);
        }

        for( char ch: str.toCharArray()){
            if(freqMap.get(ch) == 1){
                return ch;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("First Non-Repeating chanracter is : " +findFirstNonRepeatingCharacter("aabbcdde"));

    }
}
