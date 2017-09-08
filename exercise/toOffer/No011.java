package AlgorithmTraining.exercise.toOffer;

class Solution11 {
    public int JumpFloorII(int target) {
        int[] sums = new int[target + 1];
        for (int i = 0; i <= target; i++) {
            sums[i] = 0;
        }
        for (int i = 1; i <= target; i++) {
            calcSum(sums, i);
        }
        return sums[target];
    }    
    
    private void calcSum(int[] arr, int end) {
        int sum = 0;
        for (int i = 0; i < end; i++) {
            sum += arr[i];
        }
        arr[end] = sum + 1;
    }
}
public class No011 {
    public static void main(String[] args) {
        Solution11 s = new Solution11();
        for (int i = 0; i < 5; i++) {
            System.out.println(s.JumpFloorII(i));
        }
    }
}