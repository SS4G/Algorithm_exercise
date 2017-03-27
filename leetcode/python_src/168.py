class Solution(object):
    def convertToTitle(self, n):
        """
        :type n: int
        :rtype: str
        """
        n-=1
        start_point=0# include start_point 段起始段标
        end_point=26 # include end_point 段终结段标
        width=1
        pows=676    #26*26
        while n>=end_point:#判断n所属的段
            end_point=end_point+pows
            pows*=26
            width+=1
        start_point=end_point-int(pows//26)  
        return self.range_0_convert(n-start_point,width)#在所属的段内进行进制转换                 
        
    
    def range_0_convert(self,x,width):
        """
        标准的进制转换函数
        由于需要用字符表示结果，所以需要用width来
        指定结果的宽度 保证高位在数值上为0时用字符A来填充
        """
        chap_dict0="ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        list_s=[]
        print(x)
        for i in range(width):
            list_s.append(chap_dict0[x%26])
            x//=26
            x=int(x)
        list_s.reverse()
        return "".join(list_s)

    
s=Solution()
print(s.convertToTitle(2))
print(s.convertToTitle(1))
print(s.convertToTitle(26))
print(s.convertToTitle(27))
print(s.convertToTitle(28))
print(s.convertToTitle(3456))
