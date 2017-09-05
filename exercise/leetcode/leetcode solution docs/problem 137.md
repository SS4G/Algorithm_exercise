## 137. Single Number II Add to List

Given an array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

#### Note:
- Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
#### tips

#### tips
数每一位1的数量 
哪些位最后的值除以3余数是1 就表明这一位在单独的那个数字中 为1

#### mycode
```Python
class Solution(object):
    def singleNumber(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        bitCnt = [0, ]*32
        for i in nums:
            i &= 0xffffffff
            mask = 0x01
            for j in range(32):
                if mask & i:
                    bitCnt[j] += 1
                mask <<= 1
        res = 0
        for j in range(32):
            if bitCnt[j] % 3:
                res |= (1 << j)
        return res if (res & 0x80000000) == 0 else -((0xffffffff-res)+1)

if __name__ == "__main__":
    s = Solution()
    l = [-2,-2,1,1,-3,1,-3,-3,-4,-2]
    print(s.singleNumber(l))
```
