package heap;

import java.util.PriorityQueue;

public class KthLargest {

    public static int kthLargest(int[] arr, int k) {
        PriorityQueue<Integer> minheap = new PriorityQueue<>();
        for(int i = 0 ; i < arr.length ; i++){
            minheap.offer(arr[i]);
            if(minheap.size() > k){
                minheap.poll();
            }
        }
        return minheap.peek();
    }

    public static void main(String[] args) {
        int[] arr = {60 , 30 ,40 , 12 ,20 , 30};
        int k = 3;
        System.out.println(kthLargest(arr, k));
    }


}
