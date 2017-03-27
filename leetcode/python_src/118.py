class Solution(object):
    def generate(self, numRows):
        """
        :type numRows: int
        :rtype: List[List[int]]
        """
        ll=[]
        
        for i in range(numRows):
            length=i+1
            if i==0:
                ll.append([1])
            elif i==1:
                last_l=[1,1]
                ll.append(last_l)                
            else:
                tmp_l=[1]*(i+1)
                for p in range(i-1):
                    tmp_l[p+1]=last_l[p]+last_l[p+1]
                ll.append(tmp_l)
                last_l=tmp_l
        return ll
                
s=Solution()
print(s.generate(5))
                        
                
                