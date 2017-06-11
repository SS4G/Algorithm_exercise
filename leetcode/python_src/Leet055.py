class Solution(object):
    def canJump(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        possiblity = [True if nums[i] != 0 else False for i in range(len(nums))]
        return self.canJumpAtState(nums, 0, possiblity)

    def canJumpAtState(self, nums, startPos, possiblity):
        if startPos == len(nums) - 1:
            return True
        elif startPos > len(nums):
            # print("BB")
            return False
        elif possiblity[startPos]:
            res = False
            tmpRes = False
            for i in range(1, nums[startPos]+1):
                # print("start Pos", startPos, "i", i, "max", nums[startPos])
                tmpRes = self.canJumpAtState(nums, startPos+i, possiblity)
                res = res or tmpRes
                if tmpRes:
                    # print("OO")
                    break
            if res is False:
                possiblity[startPos] = False  # it's impossible to jump to target from this point
            return res
        else:
            # print("CC")
            return False

if __name__ == "__main__":
    s = Solution()
    nums0 = [2, 3, 1, 1, 4]
    nums1 = [3, 2, 1, 0, 4]
    nums1 = [0, ]
    nums1 = [1, 2]
    print(s.canJump(nums1))