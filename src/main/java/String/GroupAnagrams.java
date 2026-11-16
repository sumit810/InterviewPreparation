package String;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class GroupAnagrams {

    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
    }

    static List<List<String>> groupAnagrams(String[] strs) {
        if(strs == null){
            throw new IllegalArgumentException("strs must not be null");
        }
        Map<String, List<String>> map = new HashMap<>();
        for(String s: strs){
            if(s == null){
                throw new IllegalArgumentException("strs must not contain null");
            }
            char[] characters = s.toCharArray();
            Arrays.sort(characters);
            String sortedString = new String(characters);
            if(!map.containsKey(sortedString)){
                map.put(sortedString, new ArrayList<>());
            }
            map.get(sortedString).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
