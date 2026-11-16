package problemsWithBunny;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class findFirstOccurrenceInSortedArray {

    static int findFirstOccurrenceInSortedArray(int[] nums, int target){
        if(nums == null){
            throw new IllegalArgumentException("nums must not be null");
        }
        int low = 0;
        int high = nums.length- 1;
        int firstIndex = -1;

        while (low <= high){
            // low + (high - low) / 2 cannot overflow the way (low + high) / 2 can
            int mid = low + (high - low)/2;
            if(nums[mid] == target){
                // remember this match and keep looking to the left for an earlier one
                firstIndex = mid;
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return firstIndex;
    }

    public static void main(String[] args) {
        int[] nums = {2, 5, 6, 6 , 6, 6 , 8, 9, 9, 9};
        int target = 6;
        System.out.println(findFirstOccurrenceInSortedArray(nums, target));
    }
}
