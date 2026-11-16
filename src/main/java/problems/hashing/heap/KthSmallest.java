package problems.hashing.heap;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.PriorityQueue;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class KthSmallest {

    public static int findKthSmallest(int[] nums, int k){
        if(nums == null){
            throw new IllegalArgumentException("nums must not be null");
        }
        if(k < 1 || k > nums.length){
            throw new IllegalArgumentException("k must be between 1 and " + nums.length + " but was " + k);
        }
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
        System.out.println("Smallest " +k+"nd number is : " +findKthSmallest(nums, k));
    }
}
