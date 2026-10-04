package String;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public static void main(String[] args) {
        String s = "Java is Amazing";
        sortWords(s);
    }

    private static void sortWords(String str) {
        String[] words = str.split("\\s+");
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < words.length; i++){
            if(i> 0){
                sb.append(" ");
            }
            sb.append(words[i]);
        }
        System.out.println(sb);
    }
}
