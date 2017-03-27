# leetcode 401
## Question
####  Binary Watch
A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).

Each LED represents a zero or one, with the least significant bit on the right.
![img](https://upload.wikimedia.org/wikipedia/commons/8/8b/Binary_clock_samui_moon.jpg)

For example, the above binary watch reads "3:25".

Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

Example:

```
Input: n = 1
Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
```

Note:
The order of output does not matter.
The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".

## Answer 
无非就是列出来 转换来转换去
注意小时一定要小于12
分钟一定要小于 60
不符合的结果都要剔除
##### code (beat 20%)

```
class Solution(object):
    def readBinaryWatch(self, num):
        """
        :type num: int
        :rtype: List[str]
        """
        dict_num1={}
        for i in range(11):
            dict_num1[i]=[]
        for i in range(1024):
            dict_num1[self.count_1(i)].append(i)        
        times=[]
 
        for i in dict_num1[num]:
            #print("%%",i)
            result=self.decoder(i)
            minute=result[1]
            hour  =result[0]
            #print(minute,hour)
            
            minute_int=self.binstr2int(minute)
            hour_int  =self.binstr2int(hour  )
            #print(minute_int,hour_int)
            if minute_int<60 and hour_int<12:
                if minute_int<10:
                    minute_str="0"+str(minute_int)
                else :
                    minute_str=str(minute_int)
                times.append(str(hour_int)+":"+minute_str)   
             
        return times     
            
            
    def count_1(self,x):
        sum=0
        for i in range(10):
            sum+=(1 if x&0x01 else 0)
            x>>=1
        return sum 
      
    def decoder(self,x):
        bin_str=bin(x)[2:]
        bin_str=(10-len(bin_str))*"0"+bin_str
        hour=bin_str[0:4]
        minute=bin_str[4:]
        return (hour,minute)
    
    def binstr2int(self,x):
        z=-1 
        lenx=len(x)
        res=0 
        weight=1
        while z>=-lenx:
            if x[z]=="1":
                res+=weight
            weight*=2 
            z-=1            
        return res
```
