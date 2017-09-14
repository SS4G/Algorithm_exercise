package AlgorithmTraining.exercise.toOffer;

/**
 * Created by G5501 on 2017/9/12.
 */
class Solution047 {
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        int i = 0;
        int val;
        int tmp;
        int nextVal;
        while (i < length) {
            if (numbers[i] != i) {
                nextVal = numbers[numbers[i]];
                if (numbers[nextVal] == nextVal) {
                    duplication[0] = nextVal;
                    return true;
                }
                numbers[numbers[i]] = numbers[i];
                numbers[i] = nextVal;
            }
            else {
                i++;
            }
        }
        return false;
    }
}
public class No047 {
    public static void main(String[] args) {

    }
}
