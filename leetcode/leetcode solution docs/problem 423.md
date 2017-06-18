## 423. Reconstruct Original Digits from English Add to List

Given a non-empty string containing an out-of-order English representation of digits 0-9, output the digits in ascending order.

- Note:
- Input contains only lowercase English letters.
- Input is guaranteed to be valid and can be transformed to its original digits. That means invalid inputs such as "abc" or "zerone" are not permitted.
- Input length is less than 50,000.  

Example 1:

```
Input: "owoztneoer"
```


Output: "012"
Example 2:

```
Input: "fviefuro"
```
Output: "45"

#### tips
如果只是单纯的使用字典记录字母的个数 从 one 开始逐个拼凑 会导致 最终所有字母不能全部用完 而拼凑出错误的结果的情况 
所以 要抓特征
保证背刺抽取的数字的字符是这个数字所特有的

这就要求按照一定顺序来抽取
比如 z是 zero特有的 那么刚开始有多少个z 就有多少个zero 同理 后边的w 是two特有的 。。
这样可以逐步消除掉 one 可能是 其他单词拼凑出来的可能
按照下列顺序抽取 结果就是唯一的

```
# z - 0
# w - 2
# x - 6
# g - 8
# u - 4
# o - 1
# r - 3
# f - 5
# s - 7
# n - 9
```

#### mycode

```Python
class Solution(object):
    def originalDigits(self, s):
        """
        :type s: str
        :rtype: str
        """
        chDict = {}
        for c in set(list("onetwothreefourfivesixseveneightninezero")):
            chDict[c] = 0

        for c in s:
            chDict[c] += 1

        numL = ["zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", ]

        # z - 0
        # w - 2
        # x - 6
        # g - 8
        # u - 4
        # o - 1
        # r - 3
        # f - 5
        # s - 7
        # n - 9
        fingerPrint = [None, ]*10
        for i in range(10):
            chDicttmp = {}
            for c in numL[i]:
                if c not in chDicttmp:
                    chDicttmp[c] = 1
                else:
                    chDicttmp[c] += 1
            fingerPrint[i] = chDicttmp
        res = []
        i = 0
        order = [0, 2, 6, 8, 4, 1, 3, 5, 7, 9]
        while i < 10:
            breakFlag = False
            for c in fingerPrint[order[i]]:
                if chDict[c] >= fingerPrint[order[i]][c]:
                    continue
                else:
                    breakFlag = True
                    break
            if not breakFlag:
                res.append(order[i])
                for c in fingerPrint[order[i]]:
                    chDict[c] -= fingerPrint[order[i]][c]
            else:
                i += 1
        res.sort()
        return "".join([str(i) for i in res])

if __name__ == "__main__":
    s = Solution()
    chars = "onetwothreefourfivesixseveneightninezero"
    print(s.originalDigits(chars))
```
