## 402. Remove K Digits

Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:


```
Input: num = "1432219", k = 3
Output: "1219
```
"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

```
Input: num = "10200", k = 1
Output: "200"
```

Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:


```
Input: num = "10", k = 2
Output: "0"
```

Explanation: Remove all the digits from the number and it is left with nothing which is 0.

#### tips
这个题目的重点在于用什么样的策略来删除数字， 假设只删除一个数字， 
假设五位数是 abcde

```
删除a 相比原来减少了 a*90*10^3 + a*90*10^2 + a*10^2
删除b 相比原来减少   a*90*10^3 + b*90*10^2 + b*10^2 
删除c 相比原来减少   a*90*10^3 + b*90*10^2 + c*10^2
....
```
有上面可以看出规律 如果 a>b 那么 删除a 更划算 如果 a<b 就要考察 b 和 c 的关系 如果 c大 删除c划算 反之 删除b划算  所以删除策略就是 删除从头开始的一个数字x x之前的都比x小 x的后一个也比他小 所以 按照这个策略可以达到目的

删除k个数字 就是将这个过程重复 k轮 （这个可以证明）

要完成这个工作 可以使用堆栈来完成 这样可以以O(n)的复杂度完成这个工作    

#### mycode

```
class Solution(object):
    def removeKdigits(self, num, k):
        """
        :type num: str
        :type k: int
        :rtype: str
        """
        mark = [True, ]*len(num)
        zeroCnt = 0
        for i in num:
            if i == '0':
                zeroCnt += 1
        if k >= (len(num) - zeroCnt):  # 剥除次数大于非零字符的总数 全剥 完了 就只能是0了
            return "0"

        stack = []
        abortCnt = 0
        endFlag = False
        stack.append(num[0])
        checkedCnt = 1
        for i in num[1:]:
            if ord(i) < ord(stack[-1]):
                while len(stack) > 0 and ord(i) < ord(stack[-1]):
                    stack.pop()
                    abortCnt += 1
                    if abortCnt == k:
                        endFlag = True
                        break
                if endFlag:  # 已经删除了足够多的数字
                    break
                else:
                    stack.append(i)
            else:
                stack.append(i)
            checkedCnt += 1
            if endFlag:
                break

        if endFlag:
            for i in range(checkedCnt, len(num)):
                stack.append(num[i])
        else:
            for i in range(abortCnt, k):
                stack.pop()

        # strip leading zero
        j = 0
        while stack[j] == '0':
            j += 1
            
        return "".join(stack[j:])
```
