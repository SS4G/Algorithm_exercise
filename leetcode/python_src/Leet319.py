class Solution(object):
    def bulbSwitch(self, n):
        """
        :type n: int
        :rtype: int
        """
        return int(n**0.5)

if __name__ == "__main__":
    s = Solution()
    print(s.bulbSwitch(100))