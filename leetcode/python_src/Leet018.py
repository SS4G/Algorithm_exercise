class Solution(object):
    def fourSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[List[int]]
        """
        dictPartRes = {}
        for i in range(len(nums)):
            for j in range(i+1, len(nums)):
                if nums[i] + nums[j] not in dictPartRes:
                    dictPartRes[nums[i]+nums[j]] = [(i, j)]
                else:
                    dictPartRes[nums[i]+nums[j]].append((i, j))
        res = []
        for v in dictPartRes:
            if target-v in dictPartRes and v >= target-v:
                for a in dictPartRes[v]:
                    for b in dictPartRes[target-v]:
                        if len(set([a[0], a[1], b[0], b[1]])) == 4:  # assure four different number
                            res.append([nums[a[0]], nums[a[1]], nums[b[0]], nums[b[1]]])
        for j in res:
            j.sort()
        res = set([(i[0], i[1], i[2], i[3])for i in res])
        finRes = []
        for k in res:
            finRes.append(list(k))
        return finRes

if __name__ == "__main__":
    s = Solution()
    arr = [1, 0, -1, 0, -2, 2, -1, -1, 1, 1, 1, -1]
    print(s.fourSum(arr, 0))