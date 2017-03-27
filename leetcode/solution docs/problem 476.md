## 476. Number Complement

Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.


```
Note:
The given integer is guaranteed to fit within the range of a 32-bit signed integer.
You could assume no leading zero bit in the integer’s binary representation.
Example 1:
Input: 5
Output: 2
Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
Example 2:
Input: 1
Output: 0
Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
```
##### my_code (beats 80%) 

```
class Solution(object):
    def findComplement(self, num):
        """
        :type num: int
        :rtype: int
        """
        if num==0:
            return 1

        num=~num
        num&=0x0ffffffff
        bit_mask=0x080000000
        sums=0
        state=0# 0=all 1 begin

        while bit_mask!=0:
            if (bit_mask&num) and state==0:
                bit_mask >>=1
            elif (not bit_mask&num) and state==0:
                state=1
            elif state==1:
                sums+= bit_mask if bit_mask&num else 0
                bit_mask>>=1
        return sums
```
