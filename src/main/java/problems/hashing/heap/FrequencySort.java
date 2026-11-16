package problems.hashing.heap;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class FrequencySort {

    public static List<Integer> frequencySort(int[] nums){
        if(nums == null){
            throw new IllegalArgumentException("nums must not be null");
        }
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int num: nums){
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> {
            if (freqMap.get(a).equals(freqMap.get(b))) {
                return Integer.compare(a, b);
            }
            return Integer.compare(freqMap.get(b), freqMap.get(a));
        });

        for(int num: freqMap.keySet()){
            heap.offer(num);
        }

        List<Integer> result = new ArrayList<>();
        while(!heap.isEmpty()){
            int num = heap.poll();
            int frequency = freqMap.get(num);
            for (int i = 0; i < frequency; i++) {
                result.add(num);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int nums[] = {1, 1, 1, 3, 2, 1, 3 ,2, 4};
        System.out.println(frequencySort(nums));
    }
}
