package problems.hashing.heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class KthSmallest {

    public static int findKthSmallest(int[] nums, int k){
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0 ; i < nums.length; i++){
            maxHeap.offer(nums[i]);

            if(maxHeap.size() > k){
                maxHeap.poll();
            }
        }
        return maxHeap.peek();
    }


    public static void main(String[] args) {
        int[] nums = {19, 23, 13, 98, 34, 23, 9, 23, 76, 23};
        int k = 2;
        System.out.println("Largest " +k+"nd number is : " +findKthSmallest(nums, k));
    }
}
