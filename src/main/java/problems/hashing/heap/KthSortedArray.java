package problems.hashing.heap;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.PriorityQueue;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class KthSortedArray {

    public static void KthSortedArray(int[] nums , int k){
        if(nums == null){
            throw new IllegalArgumentException("nums must not be null");
        }
        if(k < 0){
            throw new IllegalArgumentException("k must not be negative but was " + k);
        }
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
