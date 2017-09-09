package AlgorithmTraining.exercise.leetcode2nd;
import java.util.*;
import java.util.List;

/**
 * Created by 903 on 2017/7/22.

 */

class Leet015 {
    public List<List<Integer>> threeSum(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i: nums) {
            if (map.containsKey(i))
                map.put(i, map.get(i) + 1);
            else
                map.put(i, 1);
        }
        List<List<Integer>> result = new ArrayList<>(10000);
        if (map.containsKey(0) && (map.get(0) >= 3)) {
            addRes(result, 0, 0, 0);
        }

        for (Integer key: map.keySet()) {
            if (key != 0 && map.get(key) >= 2) {
                if (map.containsKey(-(2 * key))) {
                    addRes(result, key, key, -(2 * key));
                }
            }
        }

        ArrayList<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); i ++) {
            Integer restVal = -keys.get(i);
            int leftIdx = i + 1;
            int rightIdx = keys.size() - 1;
            while (leftIdx < rightIdx) {
                Integer leftValue = keys.get(leftIdx);
                Integer rightValue = keys.get(rightIdx);
                if (leftValue + rightValue < restVal) {
                    leftIdx ++;
                }
                else if (leftValue + rightValue > restVal) {
                    rightIdx --;
                }
                else {
                    addRes(result, keys.get(i), keys.get(leftIdx), keys.get(rightIdx));
                    leftIdx ++;
                    rightIdx --;
                }
            }
        }
        return result;
    }

    private void addRes(List<List<Integer>> list, int i0, int i1, int i2) {
        Integer[] arr = {i0, i1, i2};
        List<Integer> l = Arrays.asList(arr);
        list.add(l);
    }
}

public class Leet015_t {
    public static void main(String[] args) {
        Leet015 lt = new Leet015();
        int[] nums = {0, 0, 0, 1, 1, -1, 2, 3, -5, 7, 4, 1, 2, 3, -2};
        nums = new int[]{-1,0,1,2,-1,-4};
        List<List<Integer>> result = lt.threeSum(nums);
        for (List<Integer> li: result) {
            System.out.println(li);
        }
    }
}
