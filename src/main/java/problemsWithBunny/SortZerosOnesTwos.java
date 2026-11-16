package problemsWithBunny;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class SortZerosOnesTwos {

    public static int[] sortZerosOnesTwos(int[] nums){
        requireOnlyZerosOnesTwos(nums);
        int countZeros = 0;
        int countOnes = 0;
        int countTwos = 0;

        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i] == 0){
                countZeros++;
            } else if(nums[i] == 1){
                countOnes++;
            } else {
                countTwos++;
            }
        }

        int index = 0;
        for (int i = 0; i < countZeros; i++) {
            nums[index] = 0;
            index++;
        }

        for (int i = 0; i < countOnes; i++) {
            nums[index] = 1;
            index++;
        }

        for (int i = 0; i < countTwos; i++) {
            nums[index] = 2;
            index++;
        }
        return nums;
    }


    public static int[] sortZerosOnesTwosUisngDutchNationalFlag(int[] nums){
        requireOnlyZerosOnesTwos(nums);
        int start = 0;
        int middle = 0;
        int end = nums.length - 1;

        while(middle <= end){
            if(nums[middle] == 0){
                swap(nums, start, middle);
                start++;
                middle++;
            } else if(nums[middle] == 1){
                middle++;
            } else {
                swap(nums, middle, end);
                end--;
            }
        }
        return nums;
    }

    /** Checked before anything is moved, so a rejected array is left exactly as it was passed in. */
    private static void requireOnlyZerosOnesTwos(int[] nums) {
        if(nums == null){
            throw new IllegalArgumentException("nums must not be null");
        }
        for(int num: nums){
            if(num < 0 || num > 2){
                throw new IllegalArgumentException("nums may only contain 0, 1 and 2 but found " + num);
            }
        }
    }

    private static void swap(int[] nums, int positionx, int positiony) {
        int temp = nums[positionx];
        nums[positionx] = nums[positiony];
        nums[positiony] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0 , 1};
            int[] nums2 = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 1};
        System.out.println(Arrays.toString(sortZerosOnesTwos(nums)));
        System.out.println(Arrays.toString(sortZerosOnesTwosUisngDutchNationalFlag(nums2)));
    }
}
