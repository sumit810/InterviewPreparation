package problems.hashing.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KthLargestElements {

    public static List<Integer> KthLargestElements(int[] nums, int k){
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        List<Integer> result = new ArrayList<>();

        for(int i = 0 ; i < nums.length; i++){
            minHeap.offer(nums[i]);

            if(minHeap.size() > k){
                minHeap.poll();
            }
        }
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }
        return result;
    }


    public static void main(String[] args) {
        int[] nums = {19, 23, 13, 98, 34, 23, 9, 23, 76, 23};
        int k = 4;
        System.out.println("Top "+k+"th numbers: " +KthLargestElements(nums, k));
    }
}
