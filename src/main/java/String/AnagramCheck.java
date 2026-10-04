package String;

import java.util.HashMap;
import java.util.Map;

public class AnagramCheck {
    public static void main(String[] args) {

        String s1 = "anagram";
        String s2 = "nagaram";
        //System.out.println(isAnagram(s1,s2));
        System.out.println(isAnagramUsingFrequencyCount(s1,s2));
    }

    private static boolean isAnagramUsingFrequencyCount(String s1, String s2) {
        int[] frequencyCount = new int[26];
        for(int i = 0 ; i < s1.length();i++) {
            frequencyCount[s1.charAt(i) - 'a']++;
            frequencyCount[s2.charAt(i) - 'a']--;
        }
           for(int c: frequencyCount){
               if(c != 0) {
                   return false;
               }
           }
        return true;
    }

    private static boolean isAnagram(String s1, String s2) {
        if(s1.length() != s2.length()){
            return false;
        }

        Map<Character, Integer> s1map = new HashMap<>();
        for(int i = 0 ; i < s1.length(); i++){
            char ch = s1.charAt(i);
            s1map.put(ch, s1map.getOrDefault(ch, 0 ) + 1);
        }

        Map<Character, Integer> s2map = new HashMap<>();
        for(int i = 0 ; i < s2.length(); i++){
            char ch = s2.charAt(i);
            s2map.put(ch, s2map.getOrDefault(ch, 0 ) + 1);
        }
        return s1map.equals(s2map);
    }
}
