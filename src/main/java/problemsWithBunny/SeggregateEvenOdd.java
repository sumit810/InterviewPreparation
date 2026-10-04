package problemsWithBunny;

import java.util.Arrays;

public class SeggregateEvenOdd {

    public static int[] seggregateEvenOdd(int[] nums){
        int left = 0;
        int right = nums.length - 1;
        while(left < right){
            while (nums[left]%2 == 0 && left < right){
                left++;
            }

            while (nums[right]%2 ==1 && left < right){
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
