package problems.hashing.heap;

import java.util.PriorityQueue;

public class ConnectRopesMinimiseCost {

    public static int connectRopesMinimiseCost(int[] nums){
        int totalCost = 0;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int i = 0 ; i < nums.length ; i++){
            minHeap.offer(nums[i]);
        }

        while(minHeap.size() >= 2){
            int first = minHeap.peek();
            minHeap.poll();
            int second = minHeap.peek();
            minHeap.poll();
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
