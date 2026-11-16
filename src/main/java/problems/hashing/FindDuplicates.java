package problems.hashing;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
*
Given an array, find all elements which occur more than once.

Input:  [1, 2, 2, 3, 4, 4, 5]
Output: [2, 4]
* */

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class FindDuplicates {

    public static List<Integer> findDuplicates(int[] nums){
        if(nums == null){
            throw new IllegalArgumentException("nums must not be null");
        }

        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int num: nums){
            freqMap.put(num, freqMap.getOrDefault(num, 0 ) + 1);
        }

        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry: freqMap.entrySet()){
            if(entry.getValue() > 1){
                result.add(entry.getKey());
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 4, 4, 5};
        System.out.println("Duplicate Elements in a list : " +findDuplicates(nums));

    }
}
