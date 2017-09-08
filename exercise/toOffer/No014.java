package AlgorithmTraining.exercise.toOffer;
class Solution14 {
    public double Power(double base, int exponent) {
        boolean negExpFlag = false;
        double finalValue;
        if (exponent == 0) {
            return 1;
        }
        else {
            negExpFlag = exponent < 0;
            exponent = negExpFlag ? -exponent : exponent;
            int halfExp = exponent >> 1;
            double halfExpValue = Power(base, halfExp);
            if ((exponent & 0x01) != 0) {
                finalValue = halfExpValue * halfExpValue * base;
            }
            else {
                finalValue = halfExpValue * halfExpValue;
            }
        }
        return negExpFlag ? 1 / finalValue : finalValue;            
    }
}

public class No014 {
    public static void main() {
        
    }
} 