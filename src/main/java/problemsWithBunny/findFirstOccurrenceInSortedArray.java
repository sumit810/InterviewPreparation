package problemsWithBunny;

public class findFirstOccurrenceInSortedArray {

    private static int findFirstOccurrenceInSortedArray(int[] nums, int target){
        int low = 0;
        int high = nums.length- 1;

        while (low <= high){
            int mid = (low + high)/2;
            if((nums[mid] == target) && nums[mid-1] != target){
                return mid;
            } else if((nums[mid] == target) && nums[mid - 1] == target){
                high = mid - 1;
            } else if (nums[mid] <= target) {
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {2, 5, 6, 6 , 6, 6 , 8, 9, 9, 9};
        int target = 6;
        System.out.println(findFirstOccurrenceInSortedArray(nums, target));
    }
}
