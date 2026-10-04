package heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class KthSmallest {

    public static int kthSmallest(int[] nums, int k){
        PriorityQueue<Integer> maxheap = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0 ; i < nums.length; i++){
            maxheap.offer(nums[i]);
            if(maxheap.size() > k){
                maxheap.poll();
            }
        }
        return maxheap.peek();
    }


    public static void main(String[] args) {
        int[] arr = {60 , 30 ,40 , 12 ,20 , 30};
        int k = 2;
        System.out.println(kthSmallest(arr, k));
    }
}
