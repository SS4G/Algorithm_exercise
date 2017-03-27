## 492.  Construct the Rectangle
For a web developer, it is very important to know how to design a web page's size. So, given a specific rectangular web page’s area, your job by now is to design a rectangular web page, whose length L and width W satisfy the following requirements:

```
1. The area of the rectangular web page you designed must equal to the given target area.

2. The width W should not be larger than the length L, which means L >= W.

3. The difference between length L and width W should be as small as possible.
You need to output the length L and the width W of the web page you designed in sequence.
Example:
Input: 4
Output: [2, 2]
```

Explanation: The target area is 4, and all the possible ways to construct it are [1,4], [2,2], [4,1]. 
But according to requirement 2, [1,4] is illegal; according to requirement 3,  [4,1] is not optimal compared to [2,2]. So the length L is 2, and the width W is 2.
Note:
The given area won't exceed 10,000,000 and is a positive integer
The web page's width and length you designed must be positive integers.
#### tips
使用sqrt函数进行初步基准的确定 然后首先考虑开方后的结果是面积的平方根的情况。
但是需要考虑一下双精度数进行运算的时候可能存在摄入误差
如 sqrt(9)=2.999 sqrt(9)=3.00001
 如果不是开放根 就从当前的结果开始以步进为1 jinxi进行尝试
##### mycode
```
public class Solution {
        public int[] constructRectangle(int area) {
        int [] res=new int[2];
        double sqrt_length=Math.sqrt((double)area);
        int width=(int)sqrt_length;
        if(width==0){
            width=1;
        }
        if(area%width==0 && area/width==width){
            res[0]=width;
            res[1]=width;
            return res;
        }
        else if(area%(width+1)==0 && area/(width+1)==(width+1)){
            res[0]=width+1;
            res[1]=width+1;
            return res;
        }
        else{
            while(area%width!=0){
                   width--;
            }
            res[1]=width;
            res[0]=area/width;
            return res;
        }
    }
}
```
