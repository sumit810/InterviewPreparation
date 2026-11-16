package problemsWithBunny;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class SeggregateEvenOdd {

    public static int[] seggregateEvenOdd(int[] nums){
        if(nums == null){
            throw new IllegalArgumentException("nums must not be null");
        }
        int left = 0;
        int right = nums.length - 1;
        while(left < right){
            while (nums[left]%2 == 0 && left < right){
                left++;
            }

            // not "== 1": the remainder of a negative odd number is -1
            while (nums[right]%2 != 0 && left < right){
                right--;
            }

            if(left < right){
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }

        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {12, 34 , 45, 9 , 8 , 90 , 3};
        System.out.println(Arrays.toString(seggregateEvenOdd(nums)));
    }
}
