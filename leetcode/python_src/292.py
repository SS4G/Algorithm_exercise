class Solution(object):
    def canWinNim(self, n):
        """
        :type n: int
        :rtype: bool
        """
        if n&0x03 :
            return True
        else :
            return False
            

s=Solution()
print(s.canWinNim(8))
print(s.canWinNim(5))