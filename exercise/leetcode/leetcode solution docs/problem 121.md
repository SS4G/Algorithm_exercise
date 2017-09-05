# leetcode 121
## Question
#### Best Time to Buy and Sell Stock
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Example 1:
Input: [7, 1, 5, 3, 6, 4]
Output: 5

max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
Example 2:
Input: [7, 6, 4, 3, 1]
Output: 0

In this case, no transaction is done, i.e. max profit = 0.
## Answer
说白了就是寻找列表中 两个元素之间的最大差值 （大值必须在小值后）
可以直接用类似于冒泡排序的双重循环实现
但是leetcode要求对代码由最基本的优化，一般的最暴力的SB方法是通不过的所以对于列表开始很长的部分单调递减，末尾的部分单调递减的部分需要首先跳过，在进行双重排序时直接对这两段序列跳过 可以节省大量不必要的时间开销

如 
[5,4,3,2,4,5,6,7,8,9,8,7,6,5,4,3,2,1]中
只需要检索 索引[4,9] 闭区间内的部分序列 其余部分直接在进行双重循环之前跳过



##### code
###### PS： Python的分片操作是左闭右开的 不要误以为是全闭区间
```Python
class Solution(object):
    def find_start_min_end_max(self,list_x):
        start_min=2147483647
        start_index=0
        
        end_max=0
        end_index=-1
        list_length=len(list_x) 
        while start_index<list_length :
            if start_min>=list_x[start_index]:
                start_min=list_x[start_index]
                start_index+=1
            else:
                break
                
        if  start_index>=list_length:#single minimize
            start_index=list_length
        
        end_index=list_length-1   
        while end_index>=0:
            if end_max<=list_x[end_index]:
                end_max=list_x[end_index]
                end_index-=1
            else:
                break
            
        if  end_index<0:#single minimize
            end_index=0
            
        return [start_index-1,end_index+1]
        
    def maxProfit(self, prices):
        """
        :type prices: List[int]
        :rtype: int
        """
        i_index=0
        max_profile=0
        s_e_index=self.find_start_min_end_max(prices)
        start_i=s_e_index[0] 
        end_i  =s_e_index[1]
        #print("start_i=",start_i,"end_i=",end_i)
        if start_i>=end_i:
            return 0       
        
        i_index=start_i
        for i in prices[start_i:end_i+1] :
            for j in prices[i_index+1:end_i+1]:
                present_profile=j-i
                if present_profile>max_profile :
                    max_profile=present_profile
            i_index+=1        
        return max_profile
```
