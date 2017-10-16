package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by BUPT_SS4G on 2017/10/16.
 */
class Leet042x2{
    public int trap(int[] height) {
        int area = 0;

        if (height.length < 2)
            return 0;

        int leftPtr = 1;
        int lastLeftLevelPtr = 0;
        int lastLeftLevel = height[0];

        int rightPtr = height.length - 2;
        int lastRightLevelPtr = height.length - 1;
        int lastRightLevel = height[height.length - 1];

        while (leftPtr < rightPtr) {
            while (leftPtr < height.length && height[leftPtr] < lastLeftLevel) {
                leftPtr++;
            }

            System.out.println("leftPtr:" + lastLeftLevelPtr + ":" + leftPtr);
            area += calcAreaInterval(height, lastLeftLevel, lastLeftLevelPtr, leftPtr);

            if (leftPtr < height.length - 1) {
                lastLeftLevel = height[leftPtr];
                lastLeftLevelPtr = leftPtr;
                leftPtr++;
            }

            if (leftPtr >= rightPtr)
                break;

            while (rightPtr >= 0 && height[rightPtr] < lastRightLevel) {
                rightPtr--;
            }

            System.out.println("rightPtr:" + lastRightLevelPtr + ":" + rightPtr);
            area += calcAreaInterval(height, lastRightLevel, rightPtr, lastRightLevelPtr);

            if (rightPtr > 0) {
                lastRightLevel = height[rightPtr];
                lastRightLevelPtr = rightPtr;
                rightPtr--;
            }
        }
        return area;
    }

    public int calcAreaInterval(int[] height, int level, int st, int ed) {
        int area = 0;
        for (int i = st; i < ed; i++) {
            area += (level - height[i]) > 0 ? (level - height[i]) : 0;
        }
        return area;
    }
}

public class Leet042x_t2 {
    public static void main(String[] args) {
        Leet042x2 leet = new Leet042x2();
        int[] arr = {0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0};
        arr = new int[]{2, 0, 2};
        //               0  1   2   3   4   5   6   7   8   9   10  11
        //System.out.println(leet.getSegment(arr, 1));
        System.out.println(leet.trap(arr));
    }
}
