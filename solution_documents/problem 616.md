## 616. Add Bold Tag in String

Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.

Example 1:
Input: 

```
s = "abcxyz123"
dict = ["abc","123"]
```

Output:

```
"<b>abc</b>xyz<b>123</b>"
```
Example 2:
Input: 

```
s = "aaabbcc"
dict = ["aaa","aab","bc"]
```

Output:

```
"<b>aaabbc</b>c"
```

Note:
- The given dict won't contain duplicates, and its length won't exceed 100.
- All the strings in input have length in range [1, 1000].

#### tips
基本思想就是使用find来 确定一些列要加粗的区间 这些区间中很可能有很多的是重叠的区间 所以需要用区间合并函数来进行合并

合并区间的方法时 
- 按照起始点对所有的区间进行排序 
- 然后用堆栈进行合并 将合并成功的区间重新推入堆栈 
- 用这个栈顶的区间去和其他的新的区间进行合并
- 每次区间合并后的终止点选在两个合并区间的终止点的最大值

需要注意到一些比较奇葩的testcase
比如 aaa 中搜索aa 应该搜索出两个重叠的区间 所以搜索完第一个aa后 应该将搜索的区域相对于以前加一 而不能直接加二 都这只能收到一个区间

#### mycode


```
import operator
class Solution:
    def addBoldTag(self, s, dict):
        """
        :type s: str
        :type dict: List[str]
        :rtype: str
        """
        if len(dict) == 0:
            return s
        intervals = []
        for pattern in dict:
            # print(s, pattern)
            sts = self.findAll(s, pattern)
            for st in sts:
                intervals.append([st, st+len(pattern)])
        intervals.sort(key=operator.itemgetter(0))
        # print(intervals)
        if len(intervals) == 0:
            return s
        mergedIntervals = self.mergeintervals(intervals)
        # print(mergedIntervals)
        res = []
        intervalPtr = 0
        for i in range(len(s)+1):
            if intervalPtr < len(mergedIntervals):
                # print(intervalPtr)
                if i == mergedIntervals[intervalPtr][0]:
                    res.append("<b>")
                if i == mergedIntervals[intervalPtr][1]:
                    res.append("</b>")
                    intervalPtr += 1
            if i < len(s):
                res.append(s[i])
        return "".join(res)

    def mergeintervals(self, intrevals):
        stack = [intrevals[0]]
        for interval in intrevals[1:]:
            top = stack[-1]
            if top[0] <= interval[0] <= top[1]:
                top[1] = max(top[1], interval[1])
            else:
                stack.append(interval)
        return stack

    def findAll(self, s, pattern):
        st = 0
        res = []
        while st <= (len(s) - len(pattern)):
            r = s.find(pattern, st, len(s))
            # vprint(r)
            if r != -1:
                res.append(r)
                # st = r+len(pattern)
                st = r + 1  # 检索出类似于 aaa 中 查找 aa的 最后的区间应该是两个重叠的区间
            else:
                break
        return res
```
