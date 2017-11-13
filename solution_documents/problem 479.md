## 479. Largest Palindrome Product
Find the largest palindrome made from the product of two n-digit numbers.

Since the result could be very large, you should return the largest palindrome mod 1337.

Example:

```
Input: 2

Output: 987
```


Explanation: 99 x 91 = 9009, 9009 % 1337 = 987

Note:

The range of n is [1,8].


#### tips
使用的是试的方法 
如果是求两位数
那么首先要生成一个回文数字 
采用把99 翻过来拼在一起 成为9999 把98反过来拼在一起成为9889 。。。
这种靠两位数拼起来的回文数字 如果可以被一个两位数整除 那么另一个因数也一定是两位数

生成了回文数字后 就从最大的两位数99 98 开始试着去除 如果能够除整 就说明找到了结果

121 虽然是11*11 但因为是题目要求最大的 所以都能找的到
注意x*x>u x是试除的两位数 u是回文积
#### samplecode
```
public class Solution {
    public int largestPalindrome(int n) {
        if (n==1) return 9;
        int max=(int)Math.pow(10, n)-1;
        for (int v=max-1;v>max/10;v--) {
            long u=Long.valueOf(v+new StringBuilder().append(v).reverse().toString());
            for (long x=max;x*x>=u;x--)
                if (u%x==0)
                    return (int)(u%1337);
        }
        return 0;
    }
}
```
