## 357. Count Numbers with Unique Digits Add to List

Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.


```
Example:
Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding [11,22,33,44,55,66,77,88,99])
```


#### tips
根据排列组合算出一个数字表来 每个数字对应 x位数的包含不同数字的和
如 数表的第二项81 表示在两位数中 uniqueNumber的数量是81个 
然后需要多少加起来即可

这里尤其需要注意的是 n=0的特殊情况

#### mycode
```Java
public class Solution {
 public static int countNumbersWithUniqueDigits(int n) {
        int[] possible = {10,81,648,4536,27216,136080,544320,1632960,3265920,3265920,};
        int sum = 0;
        if (n>10)
            n=10;
        if (n==0)
            return 1;
        for (int i = 0; i < n; i++)
            sum += possible[i];
        return sum;
    }
}
```
