class Solution(object):
    def convert(self, s, numRows):
        """
        :type s: str
        :type numRows: int
        :rtype: str
        """
        # 1 perido=n+n-2
        # 1 line index%perido==0
        # 2 line index%perido==1 or perido-1
        # 3 line index%perido==2 or perido-2
        #...
        # last line index%perido==row-1
        perido=2*numRows-2
        s_len=len(s)
        if s_len==0:
            return ""
        if numRows==1:
            return s       
        
        
        rows=[None,]*(2*numRows-2)
        for z in range((2*numRows-2)):
            rows[z]=[]
        
        out_row=[None,]*numRows
        for z in range(numRows):
            rows[z]=[]            
       
        for k in range(perido):
            z=k             
            while z<s_len:
                rows[k].append(s[z])                
                z+=perido
            print(rows[k])
        
        for i in range(numRows):
            if i ==0 :
                out_row[0]=rows[0]                
            elif i==numRows-1:
                out_row[numRows-1]=rows[numRows-1]
            else:
                out_row[i]=[None,]*(len(rows[i])+len(rows[perido-i]))
                out_row[i][0::2]=rows[i][:]
                out_row[i][1::2]=rows[perido-i][:]
             
             
        sums=[]
        for i in out_row:
            sums+=i
            
        return "".join(sums)
s=Solution() 
print(s.convert("0123456789",4))
print(s.convert("0123456789",1))
print(s.convert("PAYPALISHIRING",3))
                