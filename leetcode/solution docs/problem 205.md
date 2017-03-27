# leetcode 205
## Question
#### Isomorphic Strings

Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

For example,

```
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.
```


Note:
You may assume both s and t have the same length.

## Answer
使用Python的字典来完成映射的检查
本题中 映射是一对一的
即 a->b  a作为字典的键 b作为字典的值
如果有 a->b1 a->b2 或者 a1->b a2->b 的映射存在则判定为False
剩下的比较简单 直接看代码吧
##### code
```
class Solution(object):
    def isIsomorphic(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """
        length = len(s)
        zip_list=zip(s,t)
        map_dict={}
        for a,b in zip_list:#get a key-val 
            if a not in map_dict:#check if a is new key
                if b not in map_dict.values():#new key-val  
                    map_dict[a]=b
                else:# a not exist but b has existed ,a1->b a2->b situation occur!
                    return False
            else:
                if map_dict[a]!=b:# a->b1 a->b2 situation occured ,error
                    return False
                else:
                    continue
        return True
```
