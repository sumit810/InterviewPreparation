package heap;

import java.util.*;

public class TopKthFrequentElements {

    public static List<Integer> topKthFrequentElements(int[] nums, int k) {
        Map<Integer, Integer> frequencymap = new HashMap<>();
        for(int i = 0 ; i < nums.length; i++){
            frequencymap.put(nums[i], frequencymap.getOrDefault(nums[i], 0 ) + 1);
        }

        PriorityQueue<Integer> minheap = new PriorityQueue<>((a, b) -> {
            return frequencymap.get(a) - frequencymap.get(b);
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
