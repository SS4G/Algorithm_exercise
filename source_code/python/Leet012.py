class Solution(object):
    def intToRoman(self, num):
        """
        :type num: int
        :rtype: str
        """
        dictC = dict([("M", 1000), ("D", 500), ("C", 100), ("L", 50), ("X", 10), ("V", 5), ("I", 1)])
        bit100 = ["", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"]
        bit10  = ["", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"]
        bit1   = ["", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"]
        bitNum = [0, ]*4
        for i in range(1, 5):
            bitNum[i-1] = num % 10
            num //= 10
        bitNum.reverse()
        res = []
        for i in range(4):
            if i == 0:
                res.append("M"*bitNum[i])
            elif i == 1:
                res.append(bit100[bitNum[i]])
            elif i == 2:
                res.append(bit10[bitNum[i]])
            elif i == 3:
                res.append(bit1[bitNum[i]])
        return "".join(res)

if __name__ == "__main__":
    s = Solution()
    print(s.intToRoman(3999))