# leetcode 38
## Question
#### Count and Say
The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.

## Answer
说白了还是游程编码 只不过是将游程编码的结果转换为一个对应的字符串来输出而已
很简单 直接上代码 beat 70%

##### code
```Python
class Solution(object):
    def countAndSay(self, n):
        """
        :type n: int
        :rtype: str
        """
        tmp_res="1"
        chap_list=[]
        cnt_list =[]
        for i in range(n-1):
            # compress the tmp res string
            k=self.zip0(tmp_res)
            #obtain the result after compress
            chap_list=k[0]
            cnt_list =k[1]
            length=len(chap_list)
            tmp_res=""
            
            #merge the two list into a string
            for k in range(length):
                chap_cnt=str(cnt_list[k])
                tmp_res+=chap_cnt+chap_list[k]
            #print("running i",i+1,tmp_res)
            
        return tmp_res      
            
    def zip0(self,str0):
        last_chap='*'
        cnt=0
        chap_list=[]
        cnt_list=[]
        res_chap =[]
        res_cnt  =[]
        for k in str0:
            if last_chap!=k:
                chap_list.append(last_chap)
                cnt_list.append(cnt)
                last_chap=k
                cnt=1
            else :
                cnt+=1
        chap_list.append(last_chap)
        cnt_list.append(cnt)
        res_chap[:]=chap_list[1:]
        res_cnt [:]= cnt_list[1:]
        return [res_chap,res_cnt]
```
