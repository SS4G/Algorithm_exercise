class Solution:
    # @param {integer[]} nums
    # @return {string}
    def largestNumber(self, nums):
        nums = [str(j) for j in nums]
        for i in range(len(nums)):
            for j in range(i, len(nums)):
                if self.cmpNum(nums[i], nums[j]) < 0:
                    nums[i], nums[j] = nums[j], nums[i]
        res = "".join(nums)
        if res[0] == '0':
            res = "0"
        return res

    def cmpNum(self, s0, s1):
        minLen = min(len(s0), len(s1))
        for i in range(minLen):
            if s0[i] < s1[i]:
                return -1
            elif s0[i] > s1[i]:
                return 1
            else:
                pass

        if len(s0) == len(s1):
            return 0
        else:
            if len(s0) > len(s1):
                return 1 if ord(s0[minLen]) > ord(s1[0]) else -1
            elif len(s1) > len(s0):
                return -1 if ord(s1[minLen]) > ord(s0[0]) else 1

if __name__ == "__main__":
    s = Solution()
    # print(s.cmpNum("335", "33"))
    # print(s.cmpNum("332", "33"))
    # print(s.cmpNum("3", "30"))
    li = [0, 0]
    print(s.largestNumber(li))