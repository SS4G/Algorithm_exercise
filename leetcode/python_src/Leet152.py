class Solution(object):
    def maxProduct(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        lasti = -1
        curMaxProduct = -0xffffffff
        zeroFlag = False
        for i in range(len(nums)):
            if nums[i] == 0:
                zeroFlag = True
                curMaxProduct = max(curMaxProduct, self.maxProductNoneZero(nums, lasti + 1, i))
                lasti = i
                #print(curMaxProduct)
            elif i == len(nums) - 1:
                curMaxProduct = max(curMaxProduct, self.maxProductNoneZero(nums, lasti + 1, i + 1))
        return curMaxProduct if zeroFlag is False else max(0, curMaxProduct)

    def maxProductNoneZero(self, nums, lo, hi):
        hi0 = hi - 1
        lo0 = lo
        if hi - lo == 1:
            return nums[lo]
        elif hi - lo == 0:
            return 0
        product = [1, ] * (hi - lo)
        for i in range(len(product)):
            if i == 0:
                product[i] = nums[lo]
            else:
                product[i] = product[i - 1] * nums[lo + i]
        #print(product)
        #print(product)
        if product[-1] > 0:
            return product[-1]

        while nums[hi0] > 0:
            hi0 -= 1
        while nums[lo0] > 0:
            lo0 += 1

        neg1 = lo0 - lo
        neg0 = hi0 - lo
        #print(neg0, neg1)
        # situation0
        if neg0 > 0:
            situationProduct0 = max(product[neg0 - 1], product[-1]//product[neg0])
        else:
            #print("KK")
            situationProduct0 = product[-1]//product[neg0]

        if neg1 < hi - 1:
            situationProduct1 = max(product[neg1 - 1], product[-1]//product[neg1])
        else:
            situationProduct1 = product[-1]//product[neg1]

        return max(situationProduct0, situationProduct1)

if __name__ == "__main__":
    s = Solution()
    nums = [1, -2, 3, 0, 4, -5, 6]
    nums = [0]
    print(s.maxProduct(nums))


