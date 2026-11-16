package String;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class ReverseChars {
    public static void main(String[] args) {

        char[] chars = {'s' , 'u', 'm', 'i' , 't'};

        System.out.println("Reverse String " +Arrays.toString(reverse(chars)));
    }

    /** Reverses chars in place and returns the same array. */
    static char[] reverse(char[] chars) {
        if(chars == null){
            throw new IllegalArgumentException("chars must not be null");
        }
        int left = 0;
        int right = chars.length - 1;
        while(left < right){
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;

            left++;
            right--;
        }
        return chars;
    }
}
