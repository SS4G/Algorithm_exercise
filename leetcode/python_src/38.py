class Solution(object):
    def countAndSay(self, n):
        """
        :type n: int
        :rtype: str
        """
        tmp_res="1"
        chap_list=[]
        cnt_list =[]
        for i in range(n):
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
        
s=Solution()
print(s.countAndSay(5))
print(s.zip0("11112222111"))
print(s.zip0("111122221"))
print(s.zip0("1"))
print(s.zip0("11"))
    
    