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

        
        
s=Solution()
prices=[7, 1, 5, 3, 6, 4]
print(s.maxProfit(prices))
prices=[7, 6, 4, 3, 1]
print(s.maxProfit(prices))