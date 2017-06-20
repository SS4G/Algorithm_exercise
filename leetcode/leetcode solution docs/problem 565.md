## 565. Array Nesting My SubmissionsBack To Contest

A zero-indexed array A consisting of N different integers is given. The array contains all integers in the range [0, N - 1].

Sets S[K] for 0 <= K < N are defined as follows:

S[K] = { A[K], A[A[K]], A[A[A[K]]], ... }.

Sets S[K] are finite for each K and should NOT contain duplicates.

Write a function that given an array A consisting of N integers, return the size of the largest set S[K] for this array.

Example 1:

```
Input: A = [5,4,0,3,1,6,2]
```

Output: 4
Explanation: 

```
A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
```


One of the longest S[K]:
S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
##### Note:
- N is an integer within the range [1, 20,000].
- The elements of A are all distinct.
- Each element of array A is an integer within the range [0, N-1].


#### tips
这个题目的关键在于 长度为N的数组 里面的数字范围在 [1,N-1] 双闭区间 而且note 中表明 所有的数字都不相同， 这说明 把这个数组看左链表 没有任何一个节点会被两个指针所指 所以这个链表实际上只能是好几个简单地环 所以此题的意思就变为 求出这些单环中最大的环的长度
用一个标记数组就可以完成这个题目

#### mycode

```Python
class Solution(object):
    def arrayNesting(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        marked = [False, ]*len(nums)
        res = 0
        for i in range(len(nums)):
            if marked[i]:
                continue
            else:
                marked[i] = True
                nextI = nums[i]
                length = 1
                while nextI != i:
                    marked[nextI] = True
                    nextI = nums[nextI]
                    length += 1
                res = res if res > length else length
        return res
if __name__ == "__main__":
    s = Solution()
    A = [5, 4, 0, 3, 1, 6, 2]
    print(s.arrayNesting(A))
```
