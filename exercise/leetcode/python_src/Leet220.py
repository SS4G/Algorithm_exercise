import collections

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

if __name__ == "__main__":
    s = Solution()
    nums = [1, 2, 3, 4, 5, 6]
    print(s.containsNearbyAlmostDuplicate(nums, 1, 2))






