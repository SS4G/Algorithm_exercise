package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by G5501 on 2017/10/17.
 */
import AlgorithmTraining.G55Utils.Java.ArrayUtil;

import javax.xml.bind.Element;
import java.util.*;
class CalcElement2 {
    public boolean isChar;
    public int iVal;
    public char cVal;
    public int priority;
    CalcElement2(boolean isChar, int ival, char cval, int priority) {
        this.isChar = isChar;
        if (isChar) {
            this.cVal = cval;
            this.priority = priority;
        }
        else
            this.iVal = ival;
    }

    public String toString() {
        return isChar ? Character.toString(cVal) + ":p=" + priority : Integer.toString(iVal);
    }
}

class Leet241x {
    public List<Integer> diffWaysToCompute(String input) {
        String[] numbers = input.split("\\+|\\-|\\*|/");
        List<Integer> ints = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            ints.add(Integer.parseInt(numbers[i]));
        }

        List<Character> operators = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            if (isOperator(input.charAt(i))) {
                operators.add(input.charAt(i));
            }
        }
        List<Integer> result = new ArrayList<>();
        List<List<Integer>> priorities = markPriority(0, operators.size(), 0);
        for (List<Integer> priority : priorities) {
            List<CalcElement2> expressionSeq =  new ArrayList<>();
            for (int i = 0; i < ints.size(); i++) {
                expressionSeq.add(new CalcElement2(false, ints.get(i), ' ', -1));
                if (i < ints.size() - 1) {
                    expressionSeq.add(new CalcElement2(true, 0, operators.get(i), priority.get(i)));
                }
            }
            result.add(calcExpression(expressionSeq));
        }

        return result;
    }

    public List<List<Integer>> markPriority(int st, int ed, int basePriority) {
        List<List<Integer>> partRes = new ArrayList<>();
        int midVal = 0;
        if (ed - st == 1) {
            midVal = basePriority + 1;
            List<Integer> oneRes = new ArrayList<>();
            oneRes.add(midVal);
            partRes.add(oneRes);
            return partRes;
        }
        else if (ed - st < 1) {
            partRes.add(new ArrayList<>());
            return partRes;
        }
        else {
            for (int i = st; i < ed ; i++) {
                midVal = basePriority + 1;
                List<List<Integer>> leftParts = markPriority(st, i, basePriority + 1);
                List<List<Integer>> rightParts = markPriority(i + 1, ed, basePriority + 1);
                for (List<Integer> leftPart : leftParts) {
                    for (List<Integer> rightPart : rightParts) {
                        List<Integer> oneRes = new ArrayList<>();
                        oneRes.addAll(leftPart);
                        oneRes.add(midVal);
                        oneRes.addAll(rightPart);
                        partRes.add(oneRes);
                    }
                }
            }
            return partRes;
        }
    }

    public boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public int calcExpression(List<CalcElement2> data) {
        LinkedList<CalcElement2> stack = new LinkedList<>();
        int dataPtr = 0;
        while (dataPtr < data.size()) {
            if (dataPtr == 0) {
                stack.push(data.get(dataPtr));
                dataPtr++;
            }
            else {
                if (data.get(dataPtr).isChar) {
                    stack.push(data.get(dataPtr));
                }
                else if (dataPtr == data.size() - 1) {
                    CalcElement2 topElement = data.get(dataPtr);
                    while (stack.size() > 0) {
                        char op = stack.peek().cVal;
                        stack.pop();
                        int anotherIval = stack.pop().iVal;
                        topElement.iVal = getVal(anotherIval, topElement.iVal, op);
                    }
                    stack.push(topElement);
                }
                else {
                    CalcElement2 topElement = data.get(dataPtr);
                    while (stack.size() > 0 && stack.peek().priority > data.get(dataPtr + 1).priority) {
                        char op = stack.peek().cVal;
                        stack.pop();
                        int anotherIval = stack.pop().iVal;
                        topElement.iVal = getVal(anotherIval, topElement.iVal, op);
                    }
                    stack.push(topElement);
                }
                dataPtr++;
            }
        }
        return stack.pop().iVal;
    }

    int getVal(int a, int b, char op) {
        int result = 0;
        switch (op) {
            case '+': result = a + b; break;
            case '-': result = a - b; break;
            case '*': result = a * b; break;
            case '/': result = a / b; break;
            default: assert false : "WTF?";
        }
        return result;
    }

    /*
    public void calcTest() {
        List<Integer> priority = Arrays.asList(new Integer[]{4, 2, 1, 2, 3, 4});
        List<Integer> ints = Arrays.asList(new Integer[]{4, 1, 5, 3, 4, 2, 3,});
        List<Character> operators = Arrays.asList(new Character[]{'-', '+', '-', '*', '-', '+',});
        List<CalcElement2> expressionSeq =  new ArrayList<>();
        for (int i = 0; i < ints.size(); i++) {
            expressionSeq.add(new CalcElement2(false, ints.get(i), ' ', -1));
            if (i < ints.size() - 1) {
                expressionSeq.add(new CalcElement2(true, 0, operators.get(i), priority.get(i)));
            }
        }
        System.out.println(expressionSeq);
        System.out.println(calcExpression(expressionSeq));
    }*/
}

public class Leet241x_t {
    public static void main(String[] args) {
        Leet241x leet = new Leet241x();
        //leet.diffWaysToCompute("");
        List<List<Integer>> result = leet.markPriority(0, 3, 0);
        //System.out.println(result);
        //leet.calcTest();
        //System.out.println(leet.diffWaysToCompute("2-1-1"));
        System.out.println(leet.diffWaysToCompute("2*3-4*5"));

    }
}
