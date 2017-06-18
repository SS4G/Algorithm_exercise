## 451. Sort Characters By Frequency Add to List

Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:


```
Input:
"tree"

Output:
"eert"
```


Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:


```
Input:
"cccaaa"

Output:
"cccaaa"
```


Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:


```
Input:
"Aabb"

Output:
"bbAa"
```


Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
#### tips
使用python的库函数sorted 以及字典集合等来完成这个任务
#### mycode
```Python
import operator
class Solution(object):
    def frequencySort(self, s):
        """
        :type s: str
        :rtype: str
        """
        alphaDict = {}
        for i in set(s):
            alphaDict[i] = [0, i]
        for j in s:
            alphaDict[j][0] += 1
        chint = alphaDict.values()
        sortedlist = sorted(chint, key=operator.itemgetter(0), reverse=True)
        listoutput = [None, ]*len(s)
        k = 0
        for i in sortedlist:
            for c in range(i[0]):
                listoutput[k] = i[1]
                k += 1
        return "".join(listoutput)
```
