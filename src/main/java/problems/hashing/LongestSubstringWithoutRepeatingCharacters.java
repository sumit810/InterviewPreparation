package problems.hashing;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

    public static int longestSubstringWithoutRepeatingCharacters(String str) {

        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int maxLength = 0;

        for(int end = 0; end < str.length() ; end++) {
            char rightChar = str.charAt(end);

            map.put(rightChar, map.getOrDefault(rightChar, 0) + 1);

            while (map.get(rightChar) > 1) {
                char leftChar = str.charAt(start);

                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }


    public static void main(String[] args) {
        System.out.println("Longest Substring without any characters repeating: " +longestSubstringWithoutRepeatingCharacters("abcabcbb"));
    }
}
