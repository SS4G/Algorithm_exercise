class Solution(object):
    def rangeBitwiseAnd(self, m, n):
        """
        :type m: int
        :type n: int
        :rtype: int
        """
        if m > n:
            m, n = n, m
        m = m & 0xffffffff
        n = n & 0xffffffff
        k = m & n
        record = [True, ]*32
        for i in range(32):
            mask = 0x00000001 << i
            if mask & k == 0:
                record[i] = False
                # print(i)
                continue
            else:
                res0 = n & (~mask)  # try to clear
                if res0 >= m:
                    record[i] = False
                    continue
        res = 0
        for i in range(32):
            mask = 0x00000001 << i
            if record[i]:
                res |= mask
        return res

if __name__ == "__main__":
    s = Solution()
    print(s.rangeBitwiseAnd(5, 7))