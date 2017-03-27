class Solution(object):
    def list_cmp(self,a,b):
        lena=len(a)
        lenb=len(b)
        if lena!=lenb:
            return False
        else:# both a,b length is 0 is copnsidered
            index=0
            for i in a:
                if i!=b[index]:
                    return False 
                index+=1
        return True
    def isAnagram(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """
        list_s=list(s)
        list_t=list(t)
        list_s.sort()
        list_t.sort()
        return True if self.list_cmp(list_s,list_t) else False
        

        
s=Solution()
print(s.isAnagram("anagram","nagaram"))
print(s.isAnagram("rat","car"))


#print(b==s)
#print(s.list_cmp(['a','b','c'],['a','b','c']))
#print(s.list_cmp(['a','b','c'],['a','b']))
#print(s.list_cmp(['a','b','c'],['a','b','d']))
#print(s.list_cmp([],[]))
        