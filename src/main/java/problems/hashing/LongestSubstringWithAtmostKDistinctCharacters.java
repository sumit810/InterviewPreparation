package problems.hashing;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/*
* Longest Substring With At Most K Distinct Characters

This matches your preferred sliding-window style:
map.size() < k
map.size() == k
map.size() > k
*
* */

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class LongestSubstringWithAtmostKDistinctCharacters {

    static int longestSubstringWithAtmostKDistinctCharacters(String str, int k) {

        int i = 0;
        int j = 0;
        int maxLength = 0;
        int windowSize = 0;
        Map<Character, Integer> map = new HashMap<>();

        if(k < 0){
            throw new IllegalArgumentException("k must not be negative but was " + k);
        }
        if(str == null || str.length() == 0 || k == 0){
            return 0;
        }

        while(j < str.length()){
            //step 1: calculations
            char rightChar = str.charAt(j);
            map.put(rightChar, map.getOrDefault(rightChar, 0 ) + 1);
            windowSize = j - i + 1;

            //case 1: distinct characters less than k
            if(map.size() <= k){
                maxLength = Math.max(maxLength, windowSize);
                j++;
            } else {
                while(map.size() > k){
                    char leftChar = str.charAt(i);
                    map.put(leftChar, map.get(leftChar) - 1);
                    if(map.get(leftChar) == 0){
                        map.remove(leftChar);
                    }
                    i++;
                }
                //after shrinking, window becomes valid again
                maxLength = Math.max(maxLength, j-i+1);
                j++;
            }
        }
        return maxLength;

    }

    public static void main(String[] args) {
        System.out.println(longestSubstringWithAtmostKDistinctCharacters("eceba", 2));
    }
}
