package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by BUPT_SS4G on 2017/10/16.
 */
class Leet042x2{
    public int trap(int[] height) {
        int area = 0;

        if (height.length < 2)
            return 0;

        int leftPtr = 0;
        int lastLeftLevelPtr = 0;
        int lastLeftLevel = height[0];
        int leftCounted = 0;

        int rightPtr = height.length - 1;
        int lastRightLevelPtr = height.length - 1;
        int lastRightLevel = height[height.length - 1];
        int rightCounted = height.length - 1;

        while (leftPtr < height.length) {
            while (leftPtr < height.length && height[leftPtr] <= lastLeftLevel) {
                if (height[leftPtr] == lastLeftLevel) {
                    area += calcAreaInterval(height, lastLeftLevel, lastLeftLevelPtr, leftPtr);
                    leftCounted = leftPtr;
                    lastLeftLevelPtr = leftPtr;
                }
                leftPtr++;
            }

            if (leftPtr < height.length) {
                area += calcAreaInterval(height, lastLeftLevel, lastLeftLevelPtr, leftPtr);
                leftCounted = leftPtr;
                lastLeftLevel = height[leftPtr];
                lastLeftLevelPtr = leftPtr;
            }
            //System.out.println("leftPtr:" + lastLeftLevelPtr + ":" + leftPtr + ":" + leftCounted);
        }

        while (rightCounted > leftCounted && rightPtr >= 0) {
            while (rightPtr >= 0 && height[rightPtr] <= lastRightLevel) {
                if (height[rightPtr] == lastLeftLevel) {
                    area += calcAreaInterval(height, lastRightLevel, rightPtr, lastRightLevelPtr);
                    rightCounted = rightPtr;
                    lastRightLevelPtr = rightPtr;
                }
                //System.out.println("JJ");
                rightPtr--;
            }
            //System.out.println("rightPtr:" + lastRightLevelPtr + ":" + rightPtr + ":" + rightCounted);
            if (rightPtr >= 0) {
                area += calcAreaInterval(height, lastRightLevel, rightPtr, lastRightLevelPtr);
                rightCounted = rightPtr;
                lastRightLevel = height[rightPtr];
                lastRightLevelPtr = rightPtr;
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
        arr = new int[]{1, 2, 3, 2, 1, 3, 2, 1, 3, 2, 1, 3, 2, 1};
        //              0  1  2  3  4  5  6  7  8  9  10 11 12 13
        //System.out.println(leet.getSegment(arr, 1));
        arr = new int[]{2, 0, 2};
        //arr = new int[]{4, 9, 4, 5, 3, 2};
        //arr = new int[]{4, 2, 3};
        System.out.println(leet.trap(arr));
    }
}
