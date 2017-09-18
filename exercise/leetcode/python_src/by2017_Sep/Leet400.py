class Solution(object):
    def findNthDigit(self, n):
        """
        :type n: int
        :rtype: int
        """
        tmp_n=n
        digits_sum=0
        bits=1
        delta=0
        while tmp_n>0:
            delta=(10**bits-10**(bits-1))*bits
            bits+=1
            tmp_n-=delta
            digits_sum+=delta
        digits_sum-=delta#reset
        bits-=1
        tmp_n+=delta

        res_num=(tmp_n//bits)+(10**(bits-1)-1)+(0 if tmp_n%bits==0 else 1)
        offset_in_res_num=tmp_n%bits
        if offset_in_res_num>=1:
            offset_in_res_num-=1
        else :
            offset_in_res_num=-1

        return int(str(res_num)[offset_in_res_num])
s=Solution()
print(s.findNthDigit(181))






