package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import AlgorithmTraining.G55Utils.Java.LinkedListUtil;

/**
 * Created by G5501 on 2017/10/14.
 */
import java.util.*;
class CalcElement {
    public boolean isChar;
    public int iVal;
    public char cVal;
    CalcElement(boolean isChar, int ival, char cval) {
        this.isChar = isChar;
        if (isChar)
            this.cVal = cval;
        else
            this.iVal = ival;
    }

    public String toString() {
        return isChar ? Character.toString(cVal) : Integer.toString(iVal);
    }
}

class Leet224x {
    public int calculate(String s) {
        LinkedList<CalcElement> stack = new LinkedList<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c != ' ') {
                if (Character.isDigit(c)) { //in type is digit
                    int[] res = getNextInt(s, i);
                    int ival = res[0];
                    int nextIdx = res[1];
                    merge(stack, new CalcElement(false, ival, '#'));
                    i = nextIdx;
                }
                else {
                    merge(stack, new CalcElement(true, 0, c));
                    i++;
                }
            }
            else {
                i++;
            }
            //System.out.println(stack);
        }
        return stack.peek().iVal;
    }

    public void merge(LinkedList<CalcElement> stack, CalcElement nowElement) {
        //ArrayList<CalcElement> pr = new ArrayList<>(stack);
        //Collections.reverse(pr);
        //System.out.println(pr);
        if (nowElement.isChar) { //now is char
            if (stack.size() > 0) {
                CalcElement topElement = stack.peek();
                if (nowElement.cVal == '+' || nowElement.cVal == '-') {
                    if (topElement.isChar && (topElement.cVal == '+' || topElement.cVal == '-')) {
                        stack.pop();
                        nowElement.cVal = topElement.cVal == nowElement.cVal ? '+' : '-';
                        merge(stack,nowElement);
                    }
                    else if(!topElement.isChar || (topElement.isChar && topElement.cVal == '(')) {
                        stack.push(nowElement);
                    }
                    else {
                        assert false: "cant, reach";
                    }
                }
                else if (nowElement.cVal == '(') {
                    stack.push(nowElement);
                }
                else { // ')'
                    nowElement = stack.pop();
                    if (!(nowElement.isChar && nowElement.cVal == '('))
                        stack.pop(); // 'pop ('
                    merge(stack, nowElement);
                }
            }
            else {
                stack.push(nowElement);
            }
        }
        else { // now is digit
            if (stack.size() > 0) {
                CalcElement topElement = stack.peek();
                if (topElement.isChar && (topElement.cVal == '+' || topElement.cVal == '-')) {
                    stack.pop();
                    nowElement.iVal = topElement.cVal == '-' ? -nowElement.iVal : nowElement.iVal;
                    merge(stack, nowElement);
                }
                else if (topElement.isChar && topElement.cVal == '(') {
                    stack.push(nowElement);
                }
                else if (!topElement.isChar) {
                    stack.pop();
                    nowElement.iVal = nowElement.iVal + topElement.iVal;
                    merge(stack, nowElement);
                }
                else {
                    assert false: "cant, reach";
                }
            }
            else {
                stack.push(nowElement);
            }
        }
    }

    int[] getNextInt(String s, int offset) {
        int end = offset;
        while (end < s.length() && Character.isDigit(s.charAt(end))) {
            end++;
        }
        int[] res = new int[2];
        res[0] = Integer.parseInt(s.substring(offset, end));
        res[1] = end;
        return res;
    }
}

public class Leet224x_t {
    public static void main(String[] args) {
        String s0 = "1 + 1";
        String s1 = " 2-1 + 2 + 231 - 207 ";
        String s2 = "(1+(4+5+2)-3)+(6+8)";
        String s3 = "(1+++--+(4+5+2)-+--3)+(6+8)";

        Leet224x leet = new Leet224x();
        //assert leet.calculate(s0) == 2;
        //assert leet.calculate(s1) == 27;
        assert leet.calculate(s2) == 23;
        assert leet.calculate(s3) == 23;
    }
}
