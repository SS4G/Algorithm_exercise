class Solution(object):
    def repeatedSubstringPattern(self, str):
        """
        :type str: strs
        :rtype: bool
        """
        length=len(str)
        str0=list(str)
        pattern_len=1
        pattern=""
        true_flag=True
        for pattern_len in range(1,length//2+1):
            if length%pattern_len!=0:
                continue
            else:
                pattern=str0[:pattern_len]
                #print(pattern)
                pattern_repeat_time=length//pattern_len
                k=0
                true_flag=True
                while k<pattern_repeat_time:
                    if pattern==str0[k*pattern_len:(k+1)*pattern_len]:
                        pass
                    else :
                        true_flag=False
                    k+=1
            if true_flag:
                return True
        return False
        
s=Solution()
print(s.repeatedSubstringPattern("adadad"))
