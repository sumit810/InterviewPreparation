package collection;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class SetDemo {
    public static void main(String[] args) {

        String str = "zxyzxyz";
        Set<String> set = new HashSet<>();
        set.add(sortChars(str));

        System.out.println(set);
    }

    /**
     * Returns a new string with the characters of str in ascending order.
     * Strings are immutable and toCharArray() returns a copy, so the sorted copy has to be turned back into a string.
     */
    static String sortChars(String str) {
        if(str == null){
            throw new IllegalArgumentException("str must not be null");
        }
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
