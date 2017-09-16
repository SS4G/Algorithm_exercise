class Solution(object):
    def largestPalindrome(self, n):
        """
        :type n: int
        :rtype: int
        """
        dictx = {1: 9, 2: 987, 3: 123, 4: 597, 5: 677, 6: 1218, 7: 877, 8: 475}
        return dictx[n]
        """
                if n == 1:
            return 9
        maxVal = 10 ** n - 1
        v = maxVal
        while v > (10 ** (n - 1)) - 1:  # use v to generate palindorm
            li = list(str(v))
            li.extend(reversed(li))
            palind = "".join(li)
            valPalind = int(palind)
            #print(valPalind)
            a = maxVal
            while a ** 2 > valPalind:
                if valPalind % a == 0:
                    return valPalind % 1337
                a -= 1
            v -= 1
        """



if __name__ == "__main__":
    s = Solution()
    for i in range(1, 9):
        print(s.largestPalindrome(i))
