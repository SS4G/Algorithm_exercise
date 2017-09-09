## 526. Beautiful Arrangement
Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by these N numbers successfully if one of the following is true for the ith position (1 ≤ i ≤ N) in this array:

The number at the ith position is divisible by i.
i is divisible by the number at the ith position.
Now given N, how many beautiful arrangements can you construct?

Example 1:

```
Input: 2
Output: 2
```

Explanation: 

- The first beautiful arrangement is [1, 2]:
- 
- Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
- 
- Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
- 
- The second beautiful arrangement is [2, 1]:
- 
- Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
- 
- Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
- Note:
- N is a positive integer and will not exceed 15.


#### tips
经典的回溯算法， 使用递归来完成回溯 比自己写一个循环要方便的多

#### mycode
```Python
class Solution(object):
    def __init__(self):
        self.resCnt = 0

    def countArrangement(self, N):
        """
        :type N: int
        :rtype: int
        """
        self.resCnt = 0
        restNum = [True, ]*(N+1)
        restNum[0] = False  # 0 is always Fasle
        self.arrange(restNum, 1, N)
        return self.resCnt

    def arrange(self, restNum, startIndex, N):
        """

        :param restNum:
        :param startIndex: 1~N rather than 0~N-1
        :param N:
        :return:
        """
        for i in range(1, N+1):
            if restNum[i] is True:
                if (i % startIndex == 0) or (startIndex % i == 0):
                    if startIndex == N:
                        self.resCnt += 1
                    else:
                        restNum[i] = False
                        self.arrange(restNum, startIndex+1, N)
                        restNum[i] = True
```
