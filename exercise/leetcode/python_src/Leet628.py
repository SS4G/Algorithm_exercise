class Solution(object):
    def maximumProduct(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        nums.sort()
        productsMax = [(-0xffffffff, -1, -1), ]
        productsMin = [(0x7fffffff, -1, -1), ]
        for i in range(len(nums)):
            for j in range(i + 1, len(nums)):
                tmp = (nums[i] * nums[j], i, j)
                if tmp[0] > productsMax[0][0]:
                    productsMax = [tmp, ]
                elif tmp[0] == productsMax[0][0]:
                    productsMax.append(tmp)
                if tmp[0] < productsMin[0][0]:
                    productsMin = [tmp, ]
                elif tmp[0] == productsMin[0][0]:
                    productsMin.append(tmp)
        #print(productsMin)
        candidates = []
        for i in productsMax:
            if i[0] >= 0:
                k = len(nums) - 1
                while k == i[1] or k == i[2]:
                    k -= 1
                candidates.append(i[0] * nums[k])

        for i in productsMin:
            if i[0] <= 0:
                k = 0
                while k == i[1] or k == i[2]:
                    k += 1
                candidates.append(i[0] * nums[k])
        return max(candidates)

if __name__ == "__main__":
    s = Solution()
    nums = [-7, 10, -2, 1, 3, 3, 4]
    print(s.maximumProduct(nums))