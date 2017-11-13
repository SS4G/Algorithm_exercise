## 227. Basic Calculator II

Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:

```
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
```

Note: Do not use the eval built-in library function.

#### tips
创建一个堆栈用于存放 字符对象或者整数对象
为了保证一致性 我们创建一个CalcElement 里面包含一个布尔变量来指示这个对象对当前是 运算符还是操作数

然后使用一个堆栈来进行计算
堆栈计算的规则如下
- 如果堆栈为空 将当前的CalcElement压入
- 如果当前CalcElement为操作符 那么压入
- 如果当前CalcElement为 左括号 压入
- 如果当前CalcElement是数字 那么检查堆栈前面是什么 如果是左括号 那么压入 如果是 操作符 那么从堆栈中弹出操作符和前一个操作数计算他们的值 然后把这个值作为新的整数型的CalcElement 然后将这个新的CalcElement当做一个新的要压入堆栈的对象递归的重复上面的过程
- 如果当前要压入的是右括号 那么堆栈顶部的两个元素必然是 一个整数和一个左括号 弹出他们 把整数作为新的要压入的对象 递归的完成上面的过程
- 


#### ycode


```
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
```
