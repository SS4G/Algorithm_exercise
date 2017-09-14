package AlgorithmTraining.exercise.toOffer;
class Solution10 {
    public int JumpFloor(int target) {
        int n = target + 1;
        //below is fabobnacci
        if (n == 0)
            return 0;
		else if (n == 1)
            return 1;
        else {
            int a = 0;
            int b = 1;
            for (int i = 1; i < n; i++) {
                int c = a + b;
                a = b;
                b = c;
            }
            return b;
        }
    }
}

public class No010 {
    public static void main(String[] args) {
        
    }
}