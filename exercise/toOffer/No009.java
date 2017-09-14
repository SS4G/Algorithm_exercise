package AlgorithmTraining.exercise.toOffer;

class Solution09 {
    public int Fibonacci(int n) {
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

public class No009 {
    public static void main(String[] args) {
        Solution09 s = new Solution09();
        System.out.println(s.Fibonacci(1));
        System.out.println(s.Fibonacci(2));
        System.out.println(s.Fibonacci(3));
        System.out.println(s.Fibonacci(4));
        System.out.println(s.Fibonacci(5));
    }
}