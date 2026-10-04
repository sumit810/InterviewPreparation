package problems.hashing.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthSortedArray {

    public static void KthSortedArray(int[] nums , int k){
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int index = 0;

        for(int i = 0 ; i < nums.length; i++){
            minHeap.offer(nums[i]);

            if(minHeap.size() > k){
                nums[index] = minHeap.poll();
                index++;
            }
        }

        while(!minHeap.isEmpty()){
            nums[index] = minHeap.poll();
            index++;
        }
    }


    public static void main(String[] args) {
        int[] nums = {6, 5, 3, 2, 8, 10, 9};
        int k = 3;
        KthSortedArray(nums, k);
        System.out.println(Arrays.toString(nums));
    }
}
