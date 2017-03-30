class Solution(object):
    def reverseBits(self, n):
        """
        :type n: int
        :rtype: int
        """
        
        bin_list=list(bin(n))
        bin_len=len(bin_list)-2
        tobe_reverse=bin_len
        .extend