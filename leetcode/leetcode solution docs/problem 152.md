## 152. Maximum Product Subarray

Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.

#### tips
这个需要考虑 序列中有0 序列中有0的情况下， 最大子序列只能是被0分隔的各个序列以及 0 所以重点就是计算没有0的分段序列中的最大乘积

如果非零序列中不包含负数 或者负数的个数为偶数 那么 最大值就是整个序列的乘积 然后就要讨论包含奇数个负数的情况 
奇数个负数的情况下 一个数列的最大乘积只能为以下两种情况
以左边第一个负数为分界把整个的序列分成两部分 以两部分乘积最大值作为这种情况的最大积 同理 以右边第一个负数作为分界。。。

任何情况下 第二第三个负数不可能作为分界 可以举个例子就明白了 这样的情况肯定不是最大乘积

所以分情况讨论以后 吧这两种情况的值以及0 最后再求最大值即可

#### mycode

```
class Solution(object):
    def maxProduct(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        lasti = -1
        curMaxProduct = -0xffffffff
        zeroFlag = False
        for i in range(len(nums)):
            if nums[i] == 0:
                zeroFlag = True
                curMaxProduct = max(curMaxProduct, self.maxProductNoneZero(nums, lasti + 1, i))
                lasti = i
                #print(curMaxProduct)
            elif i == len(nums) - 1:
                curMaxProduct = max(curMaxProduct, self.maxProductNoneZero(nums, lasti + 1, i + 1))
        return curMaxProduct if zeroFlag is False else max(0, curMaxProduct)

    def maxProductNoneZero(self, nums, lo, hi):
        hi0 = hi - 1
        lo0 = lo
        if hi - lo == 1:
            return nums[lo]
        elif hi - lo == 0:
            return 0
        product = [1, ] * (hi - lo)
        for i in range(len(product)):
            if i == 0:
                product[i] = nums[lo]
            else:
                product[i] = product[i - 1] * nums[lo + i]
        #print(product)
        #print(product)
        if product[-1] > 0:
            return product[-1]

        while nums[hi0] > 0:
            hi0 -= 1
        while nums[lo0] > 0:
            lo0 += 1

        neg1 = lo0 - lo
        neg0 = hi0 - lo
        #print(neg0, neg1)
        # situation0
        if neg0 > 0:
            situationProduct0 = max(product[neg0 - 1], product[-1]//product[neg0])
        else:
            #print("KK")
            situationProduct0 = product[-1]//product[neg0]

        if neg1 < hi - 1:
            situationProduct1 = max(product[neg1 - 1], product[-1]//product[neg1])
        else:
            situationProduct1 = product[-1]//product[neg1]

        return max(situationProduct0, situationProduct1)
```

