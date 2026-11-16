package heap;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class TopKthLargestElements {

    public static List<Integer> topKthLargestElements(int[] nums, int k) {
        if(nums == null){
            throw new IllegalArgumentException("nums must not be null");
        }
        if(k < 0){
            throw new IllegalArgumentException("k must not be negative but was " + k);
        }
        PriorityQueue<Integer> minheap = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            minheap.offer(nums[i]);
            if (minheap.size() > k) {
                minheap.poll();
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!minheap.isEmpty()) {
            result.add(minheap.poll());
        }
        return result;
}



    public static void main(String[] args) {
        int nums[] = { 10, 10 ,10 ,34 , 76 , 23 ,1, 34 ,23 ,9};
        int k = 3;
        System.out.println(topKthLargestElements(nums, k));
    }
}
