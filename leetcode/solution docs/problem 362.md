#leetcode 397
## Question
#### Rotate Function
Given an array of integers A and let n to be its length.

Assume Bk to be an array obtained by rotating the array A k positions clock-wise, we define a "rotation function" F on A as follow:

```
F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].

Calculate the maximum value of F(0), F(1), ..., F(n-1).
```

Note:
n is guaranteed to be less than 105.

Example:

```
A = [4, 3, 2, 6]

F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
```

So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.
## Answer
暴力破解的方法李璐上是可以的但由于长发次数太多 会导致TLE 或者因为矩阵过大而MLE
需要找规律,根据F(k-1) 可以算出F(k)
F(k)=F(k-1)+sum(A)-length_A*A[-k]
##### code
```
class Solution(object):
    def maxRotateFunction(self, A):
        """
        :type A: List[int]
        :rtype: int
        """
        len_a=len(A)
        if len_a==0:
            return 0
        print(len_a)
        
        #len_a>=1
        all_sum=sum(A)
        
        
        sums=[0,]*len_a
        sums[0]=0
        for i in range(len_a):
            sums[0]+=i*A[i]
        
        for k in range(1,len_a):
            sums[k]=sums[k-1]+all_sum-len_a*A[-k]
        return max(sums)
```
