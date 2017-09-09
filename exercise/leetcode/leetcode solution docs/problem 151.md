#### tips
使用移位运算 进行逐次比较 

举例：
100 / 7
turn1 : 7 14 28 56 超过100  
accumelate = 8  
turn : 修改上界为 100-56 = 44  

turn2 : 7 14 28 超过44  
accumelate = 8+4 = 12  
turn : 修改上界为 44 - 28 = 16  

turn3 : 7 14
accumelate = 12 + 2 = 14  
此时 余数已经小于除数7 所以 可以终止

##### ps
这里尤其要注意的是 Integer.Min_VALUE = 0x80000000 是个负值
Math.abs(Integer) 仍然是负值 因为 Math.abs() 的返回值是int 不能直接将最小的负值反过来 这样会溢出  
-Integer.MIN_VALUE == 0x8000000;  为真 这是虚拟机规定的作用 类似于CPU指令的定义



#### mycode
```Java 
//Beats 60%
public class Leet029 {
    public static int divide(int dividend, int divisor) {

        boolean posFlag = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);

        long dividend_l = (long)dividend > 0 ? (long)dividend: -(long)dividend;
        long divisor_l = (long)divisor > 0 ? (long)divisor: -(long)divisor;
        // System.out.println(dividend_l+" "+divisor_l);
        if (dividend_l == 0 || dividend_l < divisor_l )
            return 0;
        //Integer.MAX_VALUE;

        long turnResult = 1;
        long restValue = dividend_l;
        long accumValue = 0;
        long nowValue = divisor;
        while (restValue >= divisor_l) {
            turnResult = 1;
            nowValue = divisor_l;
            while (nowValue <= restValue)
            {
                nowValue <<= 1;
                turnResult <<= 1;
            }
            nowValue >>= 1;
            turnResult >>= 1;
            accumValue += turnResult;
            restValue -= nowValue;
        }

        if (accumValue > Integer.MAX_VALUE && !posFlag )
            return Integer.MIN_VALUE;
        else if (accumValue > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        return posFlag ? (int)accumValue:-(int)accumValue;
    }
    public static void main(String[] args) {
        System.out.println(divide(-2147483648,-1));
    }
}
```
