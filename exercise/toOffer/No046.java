package AlgorithmTraining.exercise.toOffer;

/**
 * Created by G5501 on 2017/9/12.
 */
class Solution046 {
    public int Add(int num1,int num2) {
        int mask = 0x01;
        int curMask = 0;
        int res = 0;
        boolean carry = false;
        boolean thisSum = false;
        for (int i = 0; i < 32; i++) {
            curMask = mask << i;
            boolean num1ThisBit = (curMask & num1) != 0;
            boolean num2ThisBit = (curMask & num2) != 0;
            thisSum = xor(xor(carry, num1ThisBit), num2ThisBit);
            carry = ((!thisSum) && (carry || num1ThisBit || num2ThisBit)) || (thisSum && carry && num1ThisBit && num2ThisBit);
            //System.out.println("bit-" + i + ":" + thisSum + " c:" + carry);
            res |= (thisSum ? curMask : 0);
        }
        return res;
    }

    private boolean xor(boolean x1, boolean x2) {
        return x1 != x2;
    }
}

public class No046 {
    public static void main(String[] args) {
        Solution046 s = new Solution046();
        //assert s.Add(1, 2) == 3;
        //assert s.Add(1, 9) == 10;
        //assert s.Add(1, 99) == 100;
        //assert s.Add(1, 999) == 1000;
        //assert s.Add(111, 899) == 1010;
        System.out.println(s.Add(111, 899));
    }
}
