package problemsWithBunny;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class IsDuplicate {

    public static boolean isDuplicate(int[] nums){
        if(nums == null){
            throw new IllegalArgumentException("nums must not be null");
        }

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0 ; i < nums.length ; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0 ) + 1);
        }

        for(Integer num: map.keySet()){
            if(map.get(num) > 1){
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int[] nums = {5 ,7 ,2 ,1, 5 ,6 ,7 ,0};
        System.out.println(isDuplicate(nums));

    }
}
