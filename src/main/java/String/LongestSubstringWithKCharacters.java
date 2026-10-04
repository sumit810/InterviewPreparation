package String;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithKCharacters {
    public static void main(String[] args) {

        String s = "ccaabbb";
        int k = 2;
        System.out.println(longestSubstringWithAtmostKCharacters(s, k));
    }

    private static int longestSubstringWithAtmostKCharacters(String s, int k) {
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
