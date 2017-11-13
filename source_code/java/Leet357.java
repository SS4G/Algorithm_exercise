package AlgorithmTraining.exercise.leetcode.java_src;

/**
 * Created by BUPT_SS4G on 2017/5/10.
 */
public class Leet357 {
    public static int countNumbersWithUniqueDigits(int n) {
        int[] possible = {10,81,648,4536,27216,136080,544320,1632960,3265920,3265920,};
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += possible[i];
        return sum;
    }
    public static void main(String[] args) {
        System.out.println(countNumbersWithUniqueDigits(2));
    }
}
