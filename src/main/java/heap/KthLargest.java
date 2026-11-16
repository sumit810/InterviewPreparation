package heap;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.PriorityQueue;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class KthLargest {

    public static int kthLargest(int[] arr, int k) {
        if(arr == null){
            throw new IllegalArgumentException("arr must not be null");
        }
        if(k < 1 || k > arr.length){
            throw new IllegalArgumentException("k must be between 1 and " + arr.length + " but was " + k);
        }
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
