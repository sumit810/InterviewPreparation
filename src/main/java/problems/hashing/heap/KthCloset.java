package problems.hashing.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KthCloset {

    public static List<Integer> findKClosestNumbers(int[] nums , int k , int x){
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> {
            int distanceA = Math.abs(a- x);
            int distanceB = Math.abs(b- x);
            return distanceB - distanceA;
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
        return result;
    }


    public static void main(String[] args) {
        int[] nums = {5, 6, 7, 8, 9};
        int k = 3;
        int x = 7;
        System.out.println(findKClosestNumbers(nums, k, x));
    }
}
