## 274. H-Index
Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as the h-index.

#### tips
H 因子 就是找到排序了的序列中的一个数 arr[i] 使得序列中>= arr[i] 的值 为h 有点表述不清 具体去查一下就好
![image](https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/H-index-en.svg/450px-H-index-en.svg.png)
满足条件的很多 我们要求找到最大的那个 即上图中的交点

#### mycode

```
class Solution(object):
    def hIndex(self, citations):
        """
        :type citations: List[int]
        :rtype: int
        """
        citations.sort(reverse=True)
        h = 0
        for i in range(len(citations)):
            if citations[i] >= i + 1:
                h += 1
            else:
                break
        return h
```
