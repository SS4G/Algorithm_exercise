# leetcode 242
## Question
#### Valid Anagram
Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.
## Answer
思路就是排序后 比较连个排序后的字符串是否完全相等 就这样

##### code
```Python
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
```

