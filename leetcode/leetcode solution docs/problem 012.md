## 12. Integer to Roman

Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.

#### tips
搞清楚规律 造一个数字映射表就好了  
[百度百科罗马数字](http://baike.baidu.com/link?url=JwARX-6e6X9UuLM8q0KIt7qFQ-VWfrFC-_Vb_N8Ydzl8BIOIGyhAB1M4TiEb6Vbc_HnurdaQK0w_IpEccKnA22RCTBY40JxMc8lCoXtPdiHhoZNBZDJQeR9Ig6zFItL7)

#### mycode

```
class Solution(object):
    def intToRoman(self, num):
        """
        :type num: int
        :rtype: str
        """
        dictC = dict([("M", 1000), ("D", 500), ("C", 100), ("L", 50), ("X", 10), ("V", 5), ("I", 1)])
        # 0~9的对应数表
        bit100 = ["", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"]
        bit10  = ["", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"]
        bit1   = ["", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"]
        bitNum = [0, ]*4
        for i in range(1, 5):
            bitNum[i-1] = num % 10
            num //= 10
        bitNum.reverse()
        res = []
        for i in range(4):
            if i == 0:
                res.append("M"*bitNum[i])
            elif i == 1:
                res.append(bit100[bitNum[i]])
            elif i == 2:
                res.append(bit10[bitNum[i]])
            elif i == 3:
                res.append(bit1[bitNum[i]])
        return "".join(res)
```
