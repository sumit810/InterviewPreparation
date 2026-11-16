package problems.hashing.heap;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.PriorityQueue;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class KthLargest {

    static int findKthLargest(int[] nums, int k) {
        if(nums == null){
            throw new IllegalArgumentException("nums must not be null");
        }
        if(k < 1 || k > nums.length){
            throw new IllegalArgumentException("k must be between 1 and " + nums.length + " but was " + k);
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for(int i = 0 ; i < nums.length; i++){
            minHeap.offer(nums[i]);

            if(minHeap.size() > k){
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] nums = {19, 23, 13, 98, 34, 23, 9, 23, 76, 23};
        int k = 2;
        System.out.println("Largest " +k+"nd number is : " +findKthLargest(nums, k));
    }
}
