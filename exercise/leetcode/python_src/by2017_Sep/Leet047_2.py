class Solution(object):
    def permuteUnique(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        nums.sort()
        dicts = {}
        for i in nums:
            if i not in dicts:
                dicts[i] = 1
            else:
                dicts[i] += 1
        res = []
        stack = []
        self.permuteUniqueRecurive(nums, 0, len(nums), dicts, stack, res)
        return res

    def permuteUniqueRecurive(self, nums, start, end, dicts, stack, res):
        if end - start == 1:
            for j in filter(lambda key: dicts[key] > 0, dicts.keys()):
                stack.append(j)
            res.append(stack[:])
            stack.pop()
        else:
            for i in set(filter(lambda key: dicts[key] > 0, dicts.keys())):
                stack.append(i)
                dicts[i] -= 1
                self.permuteUniqueRecurive(nums, start + 1, end, dicts, stack, res)
                stack.pop()
                dicts[i] += 1

if __name__ == "__main__":
    s = Solution()
    arrk = [1, 1, 3]
    print(s.permuteUnique(arrk))
