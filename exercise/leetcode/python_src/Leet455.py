class Solution(object):
    def findContentChildren(self, g, s):
        """
        :type g: List[int]
        :type s: List[int]
        :rtype: int
        """
        g.sort()
        g.reverse()
        s.sort()
        s.reverse()

        max_val=0
        st_index=0
        length=len(s)
        for greed_child in g:
            if st_index>=length:
                break
            if s[st_index]>=greed_child:
                max_val+=1
                st_index+=1
                continue
        return max_val

s=Solution()
cookies=[3,]
kids=[1,2,3]
print(s.findContentChildren(kids,cookies))





