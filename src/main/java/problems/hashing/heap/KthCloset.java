package problems.hashing.heap;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class KthCloset {

    public static List<Integer> findKClosestNumbers(int[] nums , int k , int x){
        if(nums == null){
            throw new IllegalArgumentException("nums must not be null");
        }
        if(k < 0){
            throw new IllegalArgumentException("k must not be negative but was " + k);
        }
        // farthest from x at the head; at equal distance the larger value is at the head,
        // so when the heap overflows the smaller value is the one that is kept.
        // Distances are computed as long: a - x can be wider than the int range.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> {
            long distanceA = Math.abs((long) a - x);
            long distanceB = Math.abs((long) b - x);
            int byDistance = Long.compare(distanceB, distanceA);
            return byDistance != 0 ? byDistance : Integer.compare(b, a);
        });


        for(int i = 0 ; i < nums.length; i++){
            maxHeap.offer(nums[i]);

            if(maxHeap.size() > k){
                maxHeap.poll();
            }
        }
        List<Integer> result = new ArrayList<>();
        while(!maxHeap.isEmpty()){
            result.add(maxHeap.poll());
        }
        Collections.sort(result);
        return result;
    }


    public static void main(String[] args) {
        int[] nums = {5, 6, 7, 8, 9};
        int k = 3;
        int x = 7;
        System.out.println(findKClosestNumbers(nums, k, x));
    }
}
