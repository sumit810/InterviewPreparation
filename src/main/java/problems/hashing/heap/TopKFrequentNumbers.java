package problems.hashing.heap;

import java.util.*;

public class TopKFrequentNumbers {

    public static List<Integer> findTopKFrequentNumbers(int[] nums, int k){
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int num: nums){
            freqMap.put(num, freqMap.getOrDefault(num, 0 ) + 1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>((a,b) -> {
            return freqMap.get(a) - freqMap.get(b);
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
