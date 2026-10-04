package problems.hashing;

/*
* Count Occurrences of Anagram
Problem

Find how many anagrams of pattern exist in text.

Input:
text = "forxxorfxdofr"
pattern = "for"

Output: 3
* */

import java.util.HashMap;
import java.util.Map;

public class CountOccurrencesAnagram {

    private static int countAnagrams(String text, String pattern) {
        Map<Character, Integer> patternFreq = new HashMap<>();
        Map<Character, Integer> windowFreq  = new HashMap<>();

        for(char ch: pattern.toCharArray()){
            patternFreq.put(ch, patternFreq.getOrDefault(ch, 0 ) + 1);
        }

        int start = 0;
        int count = 0;
        int k = pattern.length();

        for(int end = 0 ; end < text.length() ; end++){
            char rightChar = text.charAt(end);
            windowFreq.put(rightChar, windowFreq.getOrDefault(rightChar, 0 ) + 1);

            if(end - start + 1 == k){
                if(windowFreq.equals(patternFreq)){
                    count++;
                }
                char leftChar = text.charAt(start);
                windowFreq.put(leftChar, windowFreq.get(leftChar) - 1);

                if(windowFreq.get(leftChar) == 0){
                    windowFreq.remove(leftChar);
                }
                start++;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        //System.out.println(countAnagrams("forxxorfxdofr" , "for"));
        System.out.println(countAnagramsNew("forxxorfxdofr" , "for"));

    }

    private static int countAnagramsNew(String textString, String pattern) {

        Map<Character, Integer> patternFrequency = new HashMap<>();
        Map<Character, Integer> windowFrequency = new HashMap<>();

        int i = 0;
        int j = 0;
        int count = 0;
        int k = pattern.length();

        for (char ch : pattern.toCharArray()) {
            patternFrequency.put(ch, patternFrequency.getOrDefault(ch, 0) + 1);
        }

        while(j < textString.length()){
            // do calculations for j
            char rightChar = textString.charAt(j);
            windowFrequency.put(rightChar, windowFrequency.getOrDefault(rightChar, 0 ) + 1);

            int windowSize = j - i + 1;

            //case 1: window size less than k
            if(windowSize < k){
                j++;
            }

            //case 2: if window size is equal to k
            else if(windowSize == k){
                if(windowFrequency.equals(patternFrequency)){
                    count++;
                }

                // remove calculation of i
                char leftChar = textString.charAt(i);
                windowFrequency.put(leftChar, windowFrequency.get(leftChar) - 1);
                if(windowFrequency.get(leftChar) == 0){
                    windowFrequency.remove(leftChar);
                }
                i++;
                j++;
            }
        }
        return count;
    }
}
