package AlgorithmTraining.exercise.toOffer;
class Solution13 {
    public int NumberOf1(int n) {
        int cnt = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 0x00000001) != 0)
                cnt++;
            n >>>= 1;
        }
        return cnt;
    }
}
public class No013 { 
    public static void main(String[] args) {
        Solution13 s = new Solution13();
        assert s.NumberOf1(-1) == 32;
        assert s.NumberOf1(1) == 1;
        assert s.NumberOf1(0x7fffffff) == 31;
        assert s.NumberOf1(0x80000000) == 1;
    }
}