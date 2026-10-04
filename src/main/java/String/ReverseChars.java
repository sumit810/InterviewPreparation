package String;

import java.util.Arrays;

public class ReverseChars {
    public static void main(String[] args) {

        char[] chars = {'s' , 'u', 'm', 'i' , 't'};

        int left = 0;
        int right = chars.length - 1;
        while(left < right){
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;

            left++;
            right--;
        }

        System.out.println("Reverse String " +Arrays.toString(chars));
    }
}
