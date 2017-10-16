package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import java.util.Arrays;
import java.util.*;

/**
 * Created by BUPT_SS4G on 2017/10/16.
 */
class Leet473x {
    public boolean makesquare(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 4 != 0)
            return false;
        Map<Integer, Integer> orderMap = new HashMap<>();
        for (int i : nums) {
            if (!orderMap.containsKey(i))
                orderMap.put(i, 0);
            orderMap.put(i, orderMap.get(i) + 1);
        }
        //System.out.println(sum);
        //System.out.println(orderMap);

        return place(orderMap, 0, sum >> 2, 0, 0);
    }

    public boolean place(Map<Integer, Integer> orderMap, int lastSideLen, int targetSideLen, int curSideLen, int sideNum) {
        //System.out.println("tar="+targetSideLen+" cur="+curSideLen+" sides="+sideNum);
        //System.out.println("cur=" + curSideLen);
        //System.out.println("sides=" + sideNum);
        for (int i : orderMap.keySet()) {
            if (i >= lastSideLen && orderMap.get(i) > 0) {
                //System.out.println("tar=" + targetSideLen + " cur=" + curSideLen + " sides=" + sideNum + " toPlace=" + i);
                //System.out.println("to place=" + i);
                if (curSideLen + i > targetSideLen) {
                    continue;
                }
                else if (curSideLen + i == targetSideLen) {
                    if (sideNum + 1 == 4)
                        return true;
                    orderMap.put(i, orderMap.get(i) - 1);
                    if (place(orderMap, 0, targetSideLen,0, sideNum + 1)) {
                        return true;
                    }
                    //System.out.println("place back=" + i);
                    orderMap.put(i, orderMap.get(i) + 1);// place back
                }
                else {
                    orderMap.put(i, orderMap.get(i) - 1);
                    if (place(orderMap, i, targetSideLen, curSideLen + i, sideNum)) {
                        return true;
                    }
                    //System.out.println("place back=" + i);
                    orderMap.put(i, orderMap.get(i) + 1);// place back
                }
            }
        }
        return false;
    }
}

public class Leet473x_t {
    public static void main(String[] args) {
        Leet473x leet = new Leet473x();
        int[] nums = {1,1,2,2,2};
        //assert leet.makesquare(nums);
        nums = new int[]{3,3,3,3,4};
        //assert !leet.makesquare(nums);
        nums = new int[]{5,5,5,5,4,4,4,4,3,3,3,3};
        nums = new int[]{2,2,2,3,4,4,4,5,6};
        nums = new int[]{2,2,2,2,2,6};
        nums = new int[]{16,8,8,8,5,1,16,3,11,1,11,12,20,6,6};
        assert leet.makesquare(nums);
    }
}
