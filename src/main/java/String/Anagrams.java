package String;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class Anagrams {
    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println(Arrays.asList(findAnagrams(s, p)));
    }


    public static List<Integer> findAnagrams(String s, String p) {
        if(s == null || p == null){
            throw new IllegalArgumentException("strings must not be null");
        }
        List<Integer> result = new ArrayList<>();
        // an empty pattern has no anagrams to find
        if(p.isEmpty() || p.length() > s.length()){
            return result;
        }

        Map<Character,Integer> pmap = new HashMap<>();
        for(char ch: p.toCharArray()){
            pmap.put(ch, pmap.getOrDefault(ch, 0 ) + 1);
        }

        //slide a window over s
        Map<Character, Integer> windowMap = new HashMap<>();
        int left = 0;
        for(int right = 0 ; right < s.length() ; right++){
            char ch = s.charAt(right);
            windowMap.put(ch, windowMap.getOrDefault(ch, 0 ) + 1);
            // window is too big, remove the left character
            if(right - left + 1 > p.length()){
                char leftChar = s.charAt(left);
                windowMap.put(leftChar, windowMap.get(leftChar) - 1);
                if(windowMap.get(leftChar) == 0){
                    windowMap.remove(leftChar);
                }
                left++;
            }
            // window is exactly p size
            if (right - left + 1 == p.length() && windowMap.equals(pmap)) {
                result.add(left);
            }
        }
        return result;
    }
}
