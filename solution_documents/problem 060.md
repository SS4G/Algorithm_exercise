## 060. Permutation Sequence 
DescriptionHintsSubmissionsSolutions

The set [1,2,3,…,n] contains a total of n! unique permutations.
By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3): 

```
"123"
"132"
"213"
"231"
"312"
"321"
```


Given n and k, return the kth permutation sequence.
Note: Given n will be between 1 and 9 inclusive.

#### tips
大意就是算出1~n的排列组合中的第k个 
所以 第一位 每一位数对应 (n-1)!个 第二位每位对应(n-2)!个 
所以，用一个整体为 0~ n!-1的区间 根据 每位对应的个数 然后去算就可以得到每一位上应该是什么数字
需要注意的是 用过的数字需要被标记掉 表明已使用 不可以再出现在序列中

#### mycode

```
class Solution(object):
    def getPermutation(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: str
        """
        fact = [0, ] * 10
        digits = [i for i in range(10)]
        marked = [False, ] * 10
        for i in range(10):
            if i == 0 or i == 1:
                fact[i] = 1
            else:
                fact[i] = fact[i - 1] * i
        i = 1
        totalOffset = 0
        res = []
        while i <= n:
            curFact = fact[n - i]
            factOffset = ((k - totalOffset - 1) // curFact)
            # print("i=", i, "curFact=", curFact, "factOffset=", factOffset)
            res.append(self.getOffset(digits, factOffset + 1, marked))
            totalOffset += factOffset * curFact
            i += 1
        return "".join([str(i) for i in res])

    def getOffset(self, arr, offsetPos, marked):
        i = 0
        j = 0
        while i <= offsetPos + 2:
            if not marked[j]:
                i += 1
                if i == offsetPos + 1:
                    marked[j] = True
                    #print("j=", j)
                    #print(marked)
                    return arr[j]
            j += 1

if __name__ == "__main__":
    s = Solution()
    print(s.getPermutation(9, 54494))
```
