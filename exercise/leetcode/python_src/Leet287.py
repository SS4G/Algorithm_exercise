class Solution(object):
    def findDuplicate(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        fast = nums[nums[0]]
        slow = nums[0]
        while fast != slow:
            fast = nums[nums[fast]]
            slow = nums[slow]

        fast = 0
        while fast != slow:
            fast = nums[fast]
            slow = nums[slow]
        return slow


if __name__ == "__main__":
    li = [1, 1]
    s =Solution()
    print(s.findDuplicate(li))