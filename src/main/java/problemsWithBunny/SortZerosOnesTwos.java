package problemsWithBunny;

import java.util.Arrays;

public class SortZerosOnesTwos {

    public static int[] sortZerosOnesTwos(int[] nums){
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
        int start = 0;
        int middle = 0;
        int end = nums.length - 1;

        while(middle <= end){
            switch (nums[middle]) {
                case 0:
                    swap(nums, start, middle);
                    start++;
                    middle++;
                    break;
                case 1:
                    middle++;
                    break;
                case 2:
                    swap(nums, middle, end);
                    end--;
                    break;
            }
        }
        return nums;
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
