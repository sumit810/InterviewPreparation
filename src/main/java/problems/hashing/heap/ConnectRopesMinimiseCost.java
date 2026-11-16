package problems.hashing.heap;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.PriorityQueue;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class ConnectRopesMinimiseCost {

    /** Lengths and the total are held as long: joining ropes can exceed the int range. */
    public static long connectRopesMinimiseCost(int[] nums){
        if(nums == null){
            throw new IllegalArgumentException("nums must not be null");
        }
        long totalCost = 0;

        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i] < 0){
                throw new IllegalArgumentException("rope length must not be negative but was " + nums[i]);
            }
            minHeap.offer((long) nums[i]);
        }

        while(minHeap.size() >= 2){
            long first = minHeap.poll();
            long second = minHeap.poll();
            totalCost = totalCost + first + second;
            minHeap.offer(first+second);
        }
        return totalCost;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2 ,3,4 , 5};
        System.out.println(connectRopesMinimiseCost(nums));
    }
}
