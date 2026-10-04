package collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SetDemo {
    public static void main(String[] args) {

        String str = "zxyzxyz";
        Arrays.sort(str.toCharArray());
        Set<String> set = new HashSet<>();
        set.add(str);

        System.out.println(set);
    }
}
