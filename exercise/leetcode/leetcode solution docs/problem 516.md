## 516. Longest Palindromic Subsequence

Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

Example 1:
Input:


```
"bbbab"
```
Output:
4
One possible longest palindromic subsequence is "bbbb".
Example 2:
Input:


```
"cbbd"
```
Output:

```
2
```

One possible longest palindromic subsequence is "bb".

#### TIPS
这个子序列问题和LIS问题类似 都是要使用动态规划的方法来实现，所以要点就是要找到如何划分这个问题
假设我们制定一个区间[i,j] 用二维数组 dp[i][j]来记录 这个区间中最长的回文子序列 当有 s[i] == s[j] 时 dp[i][j] = dp[i+1][j-1] + 2 
当s[i] != s[j] 时 dp[i][j] = max(dp[i+1][j], dp[i][j-1])
有了这个状态转移方程即可

#### mycode

这个方法Python 会TLE
```
public class Solution {
    public int longestPalindromeSubseq(String s) {
        if (s.length() == 0)
            return 0;
        int[][] dpRec = new int[s.length() + 1][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dpRec[i][i] = 1;
        }
        for (int i0 = s.length()-1; i0 >=0; i0--) {
            for (int j = i0 + 1; j < s.length(); j++) {
                if (s.charAt(i0) == s.charAt(j))
                    dpRec[i0][j] = dpRec[i0 + 1][j - 1] + 2;
                else
                    dpRec[i0][j] = Math.max(dpRec[i0 + 1][j], dpRec[i0][j - 1]);
            }
        }
        return dpRec[0][s.length() - 1];
    }
}
```
