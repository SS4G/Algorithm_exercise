class NumArray(object):
    def __init__(self, nums):
        """
        :type nums: List[int]
        """
        if len(nums) == 0:
            self.zero = True

        self.nums = nums
        self.summary = [0, ] * len(nums)
        self.summary[0] = nums[0]
        for i in range(1, len(nums)):
            self.summary[i] = self.summary[i - 1] + nums[i]
        self.updateDict = {}

    def update(self, i, val):
        """
        :type i: int
        :type val: int
        :rtype: void
        """
        if self.zero:
            return

        if len(self.nums) == 1:
            self.nums[0] = val
            return
        self.updateDict[i] = val - self.nums[i]

    def sumRange(self, i, j):
        """
        :type i: int
        :type j: int
        :rtype: int
        """
        if self.zero:
            return 0

        if len(self.nums) == 1:
            return self.nums[0]

        updateVal = 0
        for k in self.updateDict:
            if i <= k <= j:
                updateVal += self.updateDict[k]
        if i > 0:
            return self.summary[j] - self.summary[i - 1] + updateVal
        else:
            return self.summary[j] + updateVal

        # Your NumArray object will be instantiated and called as such:
        # obj = NumArray(nums)
        # obj.update(i,val)
        # param_2 = obj.sumRange(i,j)

if __name__ == "__main__":
    nums = [1, 2, 3, 4, 5, 6, 7, 8]
    s = NumArray(nums)
    print(s.sumRange(0, 2))
