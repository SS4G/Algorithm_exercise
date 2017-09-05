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
        
        
s=Solution()
print(s.readBinaryWatch(2))
