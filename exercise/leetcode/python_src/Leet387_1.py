class Solution(object):
    def firstUniqChar(self, s):
        """
        :type s: str
        :rtype: int
        """
        list_s=list(s)
        for k in list_s:
            if list_s.count(k)==1:
                return list_s.index(k)
        return -1
        
s=Solution()
print(s.firstUniqChar("leetcode"))
print(s.firstUniqChar("loveleetcode"))
print(s.firstUniqChar(""))
print(s.firstUniqChar("aabnfdanfbd"))