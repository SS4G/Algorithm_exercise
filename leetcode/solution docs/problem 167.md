# leetcode 167
## Question
#### Two Sum II - Input array is sorted
Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2

Subscribe to see which companies asked this question

PS：本题目为中等难度说明最一般的逐个相加的傻方法已经不适用

## Answer
比较傻得方法是 利用类似于冒泡排序的双重循环来实现

```C
for (i=0;i<length;i++)
    for(j=i+1;j<length;j++)
        if(numbers[i]+numbers[j]==targert)
        {
            return [i,j];
        }
```

但是根据第一次出错的测试用例，说明这个列表是已经升序排序的，题目考察如何优化代码
测试用例中会出现很长，但是重复性很大的数据如
[0,0,0,0,.....0,0,0,1,3]
如果按照一般方法，大部分的迭代时间都将消耗在前面大量的0上 做无用功
所以，对付这种数据的方法是使用游程编码

例如：5555557777733322221111111

行程编码为：（5，6）（7，5）（3，3）（2，4）（1，7）。可见，行程编码的位数远远少于原始字符串的位数。

然后在使用上述的双重循环逐个相加的方法。
由于游程编码后重复的数字被收缩为一个元素
本程序游程编码示例
原始数据 [1,1,1,1,1,2,2,2,3,3,3]
编码后
val_list   [1,2,3] 存储值
index_list [0,5,8] 存储每个值对应的首次出现的索引

所以对于 target=k*2 的情况需要单列出来进行特殊处理
否则 由于k在游程列表中只有一个 双重循环相加所用的k j永远不会相同 所以会出现找不到答案的情况
##### code
beats 60%
```
class Solution(object):
    def twoSum(self, numbers, target):
        """
        :type numbers: List[int]
        :type target: int
        :rtype: List[int]
        """
        k_index=0
        numbers_length=len(numbers)
        zip_length=0
        val_list=[]
        index_list=[]
        first_flag=0
        last_k=0
        # compress process 游程编码压缩
        for k in numbers:
            if k!=last_k or first_flag==0:
                val_list.append(k)
                index_list.append(k_index) 
            last_k=k
            first_flag=1
            k_index+=1
        zip_length=len(index_list)
        #print("val_list  ",val_list)
        #print("index_list",index_list)
        
        #开始检索符合题目要求的下标
        k_index=0
        j_index=0
        #print("zip_len",zip_length)
        for k in val_list:
            if (target>>1)==k:# special situation : target=k*2
                if index_list[k_index]==numbers_length-1:#k is the final value 
                                      #and this value appear only one time in numbers
                    return [None,None]#this situation won't appear if input array is correct
                elif k_index==zip_length-1:#k is the final value 
                    return [index_list[k_index]+1,index_list[k_index]+2]#but this value appear more than one time in numbers
                elif index_list[k_index]+1<index_list[k_index+1]+1:#k is not final value but k just appera only once in numbers
                    return [index_list[k_index]+1,index_list[k_index]+2]
                else :
                    continue
            else:
                j_index=k_index+1
                for j in val_list[k_index+1:]:
                    if k+j == target:
                        #print("k=",k,"j=",j)
                        #print("k_index=",k_index,"j_index",j_index)
                        #print("index_list[k_index]=",index_list[k_index],"index_list[j_index]=",index_list[j_index])
                        return [index_list[k_index]+1,index_list[j_index]+1]
                    j_index+=1
                
            k_index+=1                   
                
        return [None,None]
```
