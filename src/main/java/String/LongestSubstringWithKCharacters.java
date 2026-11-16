package String;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class LongestSubstringWithKCharacters {
    public static void main(String[] args) {

        String s = "ccaabbb";
        int k = 2;
        System.out.println(longestSubstringWithAtmostKCharacters(s, k));
    }

    static int longestSubstringWithAtmostKCharacters(String s, int k) {
        if(s == null){
            throw new IllegalArgumentException("s must not be null");
        }
        if(k < 0){
            throw new IllegalArgumentException("k must not be negative but was " + k);
        }
        Map<Character,Integer> map = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        for(int right = 0 ; right < s.length(); right++){
            char added = s.charAt(right);
            map.put(added, map.getOrDefault(added, 0 ) + 1);

            while(map.size() > k){
                char removed = s.charAt(left);
                map.put(removed,map.get(removed) - 1);

                if(map.get(removed) == 0){
                    map.remove(removed);
                }
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
}
