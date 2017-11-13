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

        if len(s0) == len(s1):
            return 0
        else:
            if len(s0) > len(s1):
                return -self.cmpNum(s0, s0[minLen:]+s1)
            elif len(s1) > len(s0):
                return self.cmpNum(s1, s1[minLen:] + s0)
            return 0

if __name__ == "__main__":
    s = Solution()
    # print(s.cmpNum("335", "33"))
    # print(s.cmpNum("332", "33"))
    print(s.cmpNum("2220", "22"))
    # li = [4704,6306,9385,7536,3462,4798,5422,5529,8070,6241,9094,7846,663,6221,216,6758,8353,3650,3836,8183,3516,5909,6744,1548,5712,2281,3664,7100,6698,7321,4980,8937,3163,5784,3298,9890,1090,7605,1380,1147,1495,3699,9448,5208,9456,3846,3567,6856,2000,3575,7205,2697,5972,7471,1763,1143,1417,6038,2313,6554,9026,8107,9827,7982,9685,3905,8939,1048,282,7423,6327,2970,4453,5460,3399,9533,914,3932,192,3084,6806,273,4283,2060,5682,2,2362,4812,7032,810,2465,6511,213,2362,3021,2745,3636,6265,1518,8398]
    li = [121, 12]
    print(s.largestNumber(li))