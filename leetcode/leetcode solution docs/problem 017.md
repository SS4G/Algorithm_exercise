## 17. Letter Combinations of a Phone Number

Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

![image](http://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Telephone-keypad2.svg/200px-Telephone-keypad2.svg.png)


Input:Digit string "23"

```
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
```

Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.
#### tips
不断的根据上一次的结果的 来添加结果

比如上次的结果是 [a,b,c] [1,2] 组合起来 就是[a1, b1, c1, a2, b2, c2] 然后把这次的结果作为下一次的基准
#### mycode
```
class Solution(object):
    def letterCombinations(self, digits):
        """
        :type digits: str
        :rtype: List[str]
        """
        charList = [" ", "*", "abc", "def",  "ghi",  "jkl",  "mno",  "pqrs", "tuv",  "wxyz", ]  # 0 ~ 9
        res = []
        lastRes = [[]]
        for d in digits:
            newRes = []
            d = int(d)
            for c in charList[d]:
                for lr in lastRes:
                    l = []
                    l[:] = lr[:]
                    l.append(c)
                    newRes.append(l)
            lastRes = newRes
        res = lastRes
        return ["".join(item) for item in res]

if __name__ == "__main__":
    s = Solution()
    print(s.letterCombinations("234"))
```
