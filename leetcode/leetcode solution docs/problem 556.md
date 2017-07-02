## 556. Next Greater Element III
Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.

Example 1:

```
Input: 12
```
Output: 21
Example 2:

```
Input: 21
```
Output: -1

#### tips
找到第一个从尾部开始的第一个降序点 降序点第一个右边的字符换到左边 
然后对这个新字符右边的数字从小到大排序即可 降低高权重位的影响

#### mycode

```
class Solution(object):
    def nextGreaterElement(self, n):
        """
        :type n: int
        :rtype: int
        """
        numl = [int(i) for i in list(str(n))]
        j = len(numl) - 1
        flag = False
        while j > 0:
            if numl[j - 1] < numl[j]:
                flag = True
                break
            j -= 1

        if not flag:
            return -1
        else:
            # firstBitIndex = j - 1
            arr = list(set(numl[j - 1:]))
            arr.sort()
            i0 = arr.index(numl[j - 1])
            newFirstBit = arr[i0 + 1]
            flag2 = False
            newRes = []
            for i in numl[j - 1:]:
                if flag2:
                    newRes.append(i)
                else:
                    if i != newFirstBit:
                        newRes.append(i)
                    else:
                        flag2 = True
            newRes.sort()
            finalRes = numl[:j - 1] + [newFirstBit, ] + newRes
            finalVal = int("".join([str(i) for i in finalRes]))
            if finalVal > 0x7fffffff:
                return -1
            else:
                return finalVal
```
