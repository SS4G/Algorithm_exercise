# leetcode 202
## Question
#### Happy Number
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 19 is a happy number

12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1


## Answer
本题的大概思路是迭代，并将每次求得的平方和放入到一个列表中用来记录迭代的历史。由于本题的迭代过程具有马尔科夫性，即后面的结果只会与上次迭代的结果有关。所以当迭代结果列表中出现相同的值时说明之后的运行状态是一样的 可以认为循环出现了 除此之外便是Happy num

本身涉及到两个可能会影响效率的主要瓶颈：
（1）由于需要将一个多位的十进制整数转化为各位的平方和
在转化过程中如果使用除法将导致效率很低 320ms
将除法转化为通过str（）方法和字典的查找来实现同样的分隔各位的功能效率提升至96ms

（2）另一个瓶颈是计算平方和的时候的，一开始使用乘法来算平方，后来由于只算0~9的平方，所以改用查表的方法，使效率提升到了68ms （beat 35%）

###### 结果如下：
Submit   | Time	         | Question	        | Status |Run Time	    |Language
---------|---------------|------------------|--------|--------------|---
12 hours | 33 minutes ago|	Happy Number	|Accepted|68 ms       	|python
12 hours | 36 minutes ago|	Happy Number	|Accepted|96 ms        	|python
12 hours | 43 minutes ago|	Happy Number	|Accepted|320 ms       	|python

#### 结论：软件乘除法使效率低下 尽量避免

### 代码

```
class Solution(object):
    def isHappy(self, n):
        """
        :type n: int
        :rtype: bool
        """
        appear_list=[]
        tmp_square_add=0
        false_flag=0
        tmp_square_add=n
        pows_result=[0,1,4,9,16,25,36,49,64,81]
        while tmp_square_add!=1:

            tmp_digits_list=self.todigits(tmp_square_add)
            tmp_square_add=0
            for p in tmp_digits_list:
                tmp_square_add+=pows_result[p] #use list check to reduce the run time 
            if tmp_square_add not in appear_list:
                appear_list.append(tmp_square_add)#add appeared 
            else :
                false_flag=1
                break
                            
        if false_flag :
            return False
        else :
            return True
        
    def todigits(self,num):
        """
        type num non-negtive-integer                
        """
        tring_num=str(num)
        digits_num=len(string_num)
        digits_list=[None]*digits_num
        digit_dict={'0':0,'1':1,'2':2,'3':3,'4':4,'5':5,'6':6,'7':7,'8':8,'9':9}
        i=0
        for k in string_num:
            digits_list[i]=digit_dict[k]
            i+=1
        return digits_list
```
