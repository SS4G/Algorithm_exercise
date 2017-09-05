## 504. Base 7 Add to List

Given an integer, return its base 7 string representation.


```
Example 1:
Input: 100
Output: "202"
Example 2:
Input: -7
Output: "-10"
Note: The input will be in range of [-1e7, 1e7].
```
#### tips
so easy 
#### mycode
```
class Solution:
    def __init__(self):
        self.base7_list=[1, 7, 49, 343, 2401, 16807, 117649 ,823543, 5764801, 40353607,]  # 预先算出7的乘方 以节省时间

    def convertToBase7(self, num):
        """
        :type num: int
        :rtype: str
        """
        neg_flag = False
        if num == 0:
            return "0"
        if num < 0:
            num = -num
            neg_flag = True
        i = 9
        res = []
        pre_fix_flag = True
        while i >= 0:
            bit = num//self.base7_list[i]
            num %= self.base7_list[i]
            i -= 1
            if bit != 0:
                pre_fix_flag = False
                res.append(str(bit))
            elif not pre_fix_flag:  # bit == 0
                res.append(str(bit))
        return "".join(res) if not neg_flag else "-"+"".join(res)
```
