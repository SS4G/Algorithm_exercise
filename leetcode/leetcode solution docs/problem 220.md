## 220. Contains Duplicate III
hether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.

#### tips
这个题目也比较有意思
其实就是求在一个数组中是否存在这样的数
abs(num[i] - nums[j]) <= t
abs(i - j) <= k

这个利用桶的思想 即吧一个范围内的值都映射为一个值

首先 根据题目的限制 我们使用一个长度为k的窗口 在这个窗口内进行如下操作

这里利用的是 num[i] // t 然后用一个 map去跟踪这个桶
这个桶 

两个相差t以内的数字可能分在一个桶里面也可能分在相邻的桶里面
当一个数字被映射到一个桶中时 去map中看这个桶是否出现过 如果出现过 那么肯定是找到了 否则就去相邻的 两个桶中去找 相邻的桶中肯定只有一个元素 如果有两个早就返回了 如果相邻的桶中的元素与当前元素的差都大于t 就说明没找到 然后在这个桶中吧这个元素记录下来

因为要使用长度为k的窗口进行滚动所以 推荐使用 collections OrderDict

#### mycode

```
class Solution(object):
    def containsNearbyAlmostDuplicate(self, nums, k, t):
        if len(nums) == 0 or k <= 0:
            return False
        if t == 0:
            return len(nums) != len(set(nums))
        # preprocessor
        minOffset = min(nums)
        nums = [i - minOffset + 1 for i in nums]  # make all element as positive
        bucketDict = collections.OrderedDict()
        for i in range(len(nums)):
            bucket = nums[i] // t
            for b in (bucket, bucket - 1, bucket + 1):
                if b in bucketDict and abs(bucketDict[b] - nums[i]) <= t:
                    return True
            bucketDict[bucket] = nums[i]
            print(bucketDict)
            if len(bucketDict) > k:
                bucketDict.popitem(last=False)  # pop the first element
        return False
```
