package problems.hashing.heap;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class TopKFrequentNumbers {

    public static List<Integer> findTopKFrequentNumbers(int[] nums, int k){
        if(nums == null){
            throw new IllegalArgumentException("nums must not be null");
        }
        if(k < 0){
            throw new IllegalArgumentException("k must not be negative but was " + k);
        }
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int num: nums){
            freqMap.put(num, freqMap.getOrDefault(num, 0 ) + 1);
        }

        // least frequent at the head; among equally frequent values the larger one is at the head,
        // so when the heap overflows the smaller value is the one that is kept
        PriorityQueue<Integer> heap = new PriorityQueue<>((a,b) -> {
            int byFrequency = Integer.compare(freqMap.get(a), freqMap.get(b));
            return byFrequency != 0 ? byFrequency : Integer.compare(b, a);
        });

        for(int num: freqMap.keySet()){
            heap.offer(num);
            if(heap.size() > k){
                heap.poll();
            }
        }

        List<Integer> result = new ArrayList<>();
        while(!heap.isEmpty()){
            result.add(heap.poll());
        }

        return result;
    }


    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 3 ,2, 2, 4, 4};
        int k = 2;
        System.out.println(findTopKFrequentNumbers(nums, k));
    }
}
