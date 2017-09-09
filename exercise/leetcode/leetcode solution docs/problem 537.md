## 537. Complex Number Multiplication Add to List

Given two strings representing two complex numbers.

You need to return a string representing their multiplication. Note i2 = -1 according to the definition.

Example 1:

```
Input: "1+1i", "1+1i"
Output: "0+2i"
```

Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
Example 2:

```
Input: "1+-1i", "1+-1i"
Output: "0+-2i"
```

Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
Note:

The input strings will not have extra blank.
The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. And the output should be also in this form.

#### tips
就是个简单的字符串转数字 偷了懒 用了python的int函数
#### mycode
```Python
class Solution(object):
    def complexNumberMultiply(self, a, b):
        """
        :type a: str
        :type b: str
        :rtype: str
        """
        ac = self.get_real_and_img(a)
        bc = self.get_real_and_img(b)
        cc_r = ac[0]*bc[0]-ac[1]*bc[1]
        cc_i = ac[1]*bc[0]+ac[0]*bc[1]
        return str(cc_r)+"+"+str(cc_i)+"i"

    def get_real_and_img(self, complex_str):
        digits = "0123456789"
        real_neg_flag = False
        index = 0
        start_index = 0
        if complex_str[0] == "-":
            real_neg_flag = True
            index = 1
            start_index = 1
        if complex_str[0] == "+":
            real_neg_flag = False
            index = 1
            start_index = 1
        else:
            pass

        while complex_str[index] in digits:
            index += 1
        real = int(complex_str[start_index:index])
        real = -real if real_neg_flag else real



        index+=1
        start_index = index
        img_neg_flag = False
        if complex_str[index] == "-":
            img_neg_flag = True
            index += 1
            start_index = index
        if complex_str[0] == "+":
            img_neg_flag = False
            index += 1
            start_index = index
        else:
            pass

        while complex_str[index] in digits:
            index += 1
        img = int(complex_str[start_index:index])
        img = -img if img_neg_flag else img
        return (real,img)
```
