
## 400. Nth Digit  

Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...


```
Note:
n is positive and will fit within the range of a 32-bit signed integer (n < 231).

Example 1:

Input:
3

Output:
3
Example 2:

Input:
11

Output:
0

Explanation:
The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
```


##### mycode
```
class Solution(object):
    def findNthDigit(self, n):
        """
        :type n: int
        :rtype: int
        """
        tmp_n=n
        digits_sum=0
        bits=1
        delta=0
        while tmp_n>0:
            delta=(10**bits-10**(bits-1))*bits
            bits+=1
            tmp_n-=delta
            digits_sum+=delta
        digits_sum-=delta#reset
        bits-=1
        tmp_n+=delta

        res_num=(tmp_n//bits)+(10**(bits-1)-1)+(0 if tmp_n%bits==0 else 1)
        offset_in_res_num=tmp_n%bits
        if offset_in_res_num>=1:
            offset_in_res_num-=1
        else :
            offset_in_res_num=-1

        return int(str(res_num)[offset_in_res_num])
```
