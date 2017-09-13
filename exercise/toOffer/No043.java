package AlgorithmTraining.exercise.toOffer;

/**
 * Created by G5501 on 2017/9/11.
 */
import java.util.*;
class Solution043 {
    public boolean isContinuous(int [] numbers) {
        Arrays.sort(numbers);
        int zeroCnt = 0;
        int j = 0;
        boolean nonZeroFlag = false;
        while (numbers[j] == 0) {
            zeroCnt++;
            j++;
        }
        for (int i = zeroCnt + 1; i < numbers.length; i++) {
            nonZeroFlag = true;
            int gap = numbers[i] - numbers[i - 1];
            if (gap != 1) {
                if (gap - 1 > zeroCnt) {
                    return false;
                }
                else {
                    zeroCnt -= (gap - 1);
                }
            }
        }
        return nonZeroFlag;
    }
}

public class No043 {
    public static void main(String[] args) {
        int[] numbers = {0,3,2,6,4};
        Solution043 s = new Solution043();
        System.out.println(s.isContinuous(numbers));
    }
}
