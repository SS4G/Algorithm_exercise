class Solution(object):
    def findComplement(self, num):
        """
        :type num: int
        :rtype: int
        """
        if num==0:
            return 1

        num=~num
        num&=0x0ffffffff
        bit_mask=0x080000000
        sums=0
        state=0# 0=all 1 begin

        while bit_mask!=0:
            if (bit_mask&num) and state==0:
                bit_mask >>=1
            elif (not bit_mask&num) and state==0:
                state=1
            elif state==1:
                sums+= bit_mask if bit_mask&num else 0
                bit_mask>>=1
        return sums

if __name__ =="__main__":
    s=Solution()
    print(s.findComplement(2))

