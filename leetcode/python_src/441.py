#class Solution(object):
#    def arrangeCoins(self, n):
#        """
#        type n: int
#        rtype: int
#        """
#        level=0
#        while True:
#            level+=1
#            n-=level
#            if n<0:
#                break
#        level-=1
#        return level
#
class Solution(object):
    def arrangeCoins(self, n):
        """
        :type n: int
        :rtype: int
        """
        root=(-1+(1+8*n)**0.5)/2
        root_int=int(root)
        if abs(root-(root_int+1))<0.00000001:
            return root_int+1
        else:
            return root_int

s=Solution()
print(s.arrangeCoins(10))