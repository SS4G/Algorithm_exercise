## 49. Group Anagrams

Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:


```
[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
```

Note: All inputs will be in lower-case.

#### tips
这个就是用一个排了序的元组来做索引 做一个字典 然后把有相同索引的字符串放在一起即可
这个主要是利用了 元组可哈希的特性 当然也可以排序后使用join生成一个字符串 作为可哈希的索引


#### mycode
```
import collections
class Solution(object):
    def groupAnagrams(self, strs):
        """
        :type strs: List[str]
        :rtype: List[List[str]]
        """

        angDict = collections.defaultdict(list)
        for s in strs:
            a = list(s)
            a.sort()
            angDict[tuple(a)].append(s)
        return [angDict[k] for k in angDict]
```
