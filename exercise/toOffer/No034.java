package AlgorithmTraining.exercise.toOffer;

/**
 * Created by G5501 on 2017/9/10.
 */
class Solution034 {
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array.length <= 0)
            return 0;
        int curSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i : array) {
            curSum += i;
            maxSum = Math.max(maxSum, curSum);
            if (curSum < 0)
                curSum = 0;
        }
        return maxSum;
    }
}

public class No034 {
    public static void main(String[] args) {

    }
}
