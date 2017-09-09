class Solution(object):
    def integerReplacement(self, n):
        """
        :type n: int
        :rtype: int
        """
        n &= 0xffffffff
        cnt = 0

        while n != 0x01:
            if n & 0x01 != 0:  # odd
                if n == 0x03:
                    n -= 1
                elif (n & 0x02) != 0:  # pre bit is 1
                    n += 1
                else:
                    n -= 1
            else:
                n >>= 1
            cnt += 1
        return cnt