class Solution(object):
    def largestDivisibleSubset(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        if len(nums) <= 1:
            return len(nums)
        nums.sort()
        record = [1, ] * len(nums)
        indexRec = [[i, ] for i in range(len(nums))]

        record[0] = 1
        indexRec[0] = [0, ]
        for i in range(1, len(nums)):
            lastIndex = -1
            for j in range(i):
                if nums[i] % nums[j] == 0:
                    if record[j] + 1 > record[i]:
                        record[i] = record[j] + 1
                        lastIndex = j
            if lastIndex != -1:
                indexRec[i] = indexRec[lastIndex][:]
                indexRec[i].append(i)

        curMaxLen = 0
        curList = []
        for i in range(len(record)):
            if record[i] > curMaxLen:
                curMaxLen = record[i]
                curList = indexRec[i]
        return [nums[i] for i in curList]


if __name__ == "__main__":
    s = Solution()
    nums = [1, 2, 3, 4, 8, 9, 15, 30]
    k = s.largestDivisibleSubset(nums)
    print(k)
