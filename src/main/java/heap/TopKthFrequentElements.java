package heap;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class TopKthFrequentElements {

    public static List<Integer> topKthFrequentElements(int[] nums, int k) {
        if(nums == null){
            throw new IllegalArgumentException("nums must not be null");
        }
        if(k < 0){
            throw new IllegalArgumentException("k must not be negative but was " + k);
        }
        Map<Integer, Integer> frequencymap = new HashMap<>();
        for(int i = 0 ; i < nums.length; i++){
            frequencymap.put(nums[i], frequencymap.getOrDefault(nums[i], 0 ) + 1);
        }

        // least frequent at the head; among equally frequent values the larger one is at the head,
        // so when the heap overflows the smaller value is the one that is kept
        PriorityQueue<Integer> minheap = new PriorityQueue<>((a, b) -> {
            int byFrequency = Integer.compare(frequencymap.get(a), frequencymap.get(b));
            return byFrequency != 0 ? byFrequency : Integer.compare(b, a);
        });

        for(int num : frequencymap.keySet()){
            minheap.offer(num);
            if(minheap.size() > k){
                minheap.poll();
            }
        }

        List<Integer> result = new ArrayList<>();
        while(!minheap.isEmpty()){
            result.add(minheap.poll());
        }
        return result;
    }

    public static void main(String[] args) {

        int[] nums = { 1, 1, 1,1 ,2 ,3,4 , 3 , 2 ,2, 4, 4, 5  };
        int k = 3;
        System.out.println(topKthFrequentElements(nums, k));
    }


}
