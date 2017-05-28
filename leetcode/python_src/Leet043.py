class Solution(object):
    def multiply(self, num1, num2):
        """
        :type num1: str
        :type num2: str
        :rtype: str
        """
        num1L = list(num1)
        num2L = list(num2)
        oneRes = []
        for i in range(len(num1L)):
            bitres = self.multiply1Bit(num2L, num1L[len(num1L) - 1 - i])
            oneRes.append(self.mulTens(bitres, i))
        finalRes = self.calcSum(oneRes)
        if finalRes[0] == '0' and len(finalRes) > 1:
            finalRes = ['0', ]
        return "".join(finalRes)

    def calcSum(self, nums):
        res = []
        if len(nums) <= 1:
            return nums[0]
        else:
            tmpres = nums[0]
            for i in range(1, len(nums)):
                tmpres = self.calc2Sum(nums[i], tmpres)
            return tmpres

    def calc2Sum(self, nums1, nums2):
        res = []
        if len(nums1) < len(nums2):
            nums1, nums2 = nums2, nums1
        #  assure len(nums1) >= len(nums2)
        carry = 0
        for i in range(len(nums2)):
            tmpres = self.toVal(nums2[len(nums2) - 1 - i]) + self.toVal(nums1[len(nums1) - 1 - i])
            tmpres += carry
            carry = tmpres // 10
            res.append(str(tmpres%10))

        for i in range(len(nums2), len(nums1)):
            tmpres = self.toVal(nums1[len(nums1) - 1 - i]) + carry
            carry = tmpres//10
            res.append(str(tmpres%10))

        if carry != 0:
            res.append(str(carry))
        res.reverse()
        return res

    def multiply1Bit(self, num1L, digits):
        mulNum = self.toVal(digits)
        carry = 0
        res = []
        for i in range(len(num1L)):
            tmp = mulNum*self.toVal(num1L[len(num1L) - 1 - i])
            tmp += carry
            res.append(str(tmp % 10))
            carry = tmp//10
        if carry > 0:
            res.append(str(carry))
        res.reverse()
        return res

    def toVal(self, c):
        return ord(c) - ord('0')

    def mulTens(self, num1L, tenPow):
        res = num1L[:]
        for i in range(tenPow):
            res.append('0')
        return res

if __name__ == "__main__":
    s = Solution()
    # print(s.toVal('0'))
    # print(s.toVal('1'))
    # print("".join(s.mulTens(['1', '2', '3'], 5)))
    # print("".join(s.multiply1Bit(['9', '9', '9'], '9')))
    # print("".join(s.calc2Sum(['9', '9', '9'], ['1'])))
    # print("".join(s.calc2Sum(['1', '1', '1'], ['1', '1', '1', '1'])))#
    # li = ["123", "999", "1", "9999", "1"]
    # li = [list(j) for j in li]
    # print("".join(s.calcSum(li)))
    l = ["0", "0", ]
    print(s.multiply(l[0], l[1]))