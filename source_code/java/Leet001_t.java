package AlgorithmTraining.exercise.leetcode2nd;
import java.util.*;
/**
 * Created by BUPT_SS4G on 2017/7/14.
 *
 */
class Leet001_2 {
    //use hash map
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        HashMap <Integer, Integer> indiceMap = new HashMap<>(30000);
        for (int i = 0; i < nums.length; i++) {
            if (indiceMap.containsKey(target - nums[i])) {
                res[0] = indiceMap.get(target - nums[i]);
                res[1] = i;
                return res;
            }
            else
                indiceMap.put(nums[i], i);
        }
        return new int[2];
    }
}
class Leet001 {
    public int[] twoSum(int[] nums, int target) {
        ArrayList<Integer[]> arr = new ArrayList<Integer[]>(30000);
        for (int i = 0; i < nums.length; i++) {
            Integer[] element = {nums[i], i};
            arr.add(element);
        }
        Comparator<Integer[]> cmp = new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                 if (o1[0] > o2[0])
                     return 1;
                 else if (o1[0] < o2[0])
                     return -1;
                 else
                     return 0;
            }
        };
        Collections.sort(arr, cmp);
        System.out.println(arr.get(0)[0]);
        System.out.println(arr.get(1)[0]);
        System.out.println(arr.get(2)[0]);

        int lo = 0;
        int hi = nums.length - 1;
        int[] indice = new int[2];
        while (lo < hi) {
            if (arr.get(lo)[0] + arr.get(hi)[0] > target) {
                hi --;
            }
            else if (arr.get(lo)[0] + arr.get(hi)[0] < target) {
                lo ++;
            }
            else {
                indice[0] = arr.get(lo)[1];
                indice[1] = arr.get(hi)[1];
                break;
            }
        }
        return indice;
    }
}
public class Leet001_t {
    public static void main(String[] args) {
        Leet001_2 s = new Leet001_2();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] nums0 = {3, 2, 4};
        int target0 = 6;
        int[] res = s.twoSum(nums0, target0);
        System.out.println(res[0] + " " + res[1]);
    }
}
