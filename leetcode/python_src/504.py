class Solution:
    def __init__(self):
        self.base7_list=[1, 7, 49, 343, 2401, 16807, 117649 ,823543, 5764801, 40353607,]

    def convertToBase7(self, num):
        """
        :type num: int
        :rtype: str
        """
        flag = False
        if num == 0:
            return "0"
        if num < 0:
            num = -num
            flag = True
        i = 9
        res = []
        pre_fix_flag = True
        while i >= 0:
            bit = num//self.base7_list[i]
            num %= self.base7_list[i]
            i-=1
            if pre_fix_flag and bit != 0:
                pre_fix_flag = not pre_fix_flag
                res.append(str(bit))
            else:
                res.append(str(bit))
        return "".join(res) if not flag else

if __name__ == "__main__":
    s = Solution()
    print(s.convertToBase7(-7))
