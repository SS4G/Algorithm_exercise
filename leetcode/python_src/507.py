class Solution(object):
    def checkPerfectNumber(self, num):
        """
        :type num: int
        :rtype: bool
        """
        if num <=0:
            return False

        E = 0.0000001
        sums = 1
        sq_double = num**0.5
        complte_sqrt_flag = False
        if abs(sq_double - int(sq_double)) < E:
            complte_sqrt_flag = True
            sq = (int)(sq_double)
        elif abs(sq_double-(int(sq_double)+1)) < E:
            sq = (int)(sq_double)+1
            complte_sqrt_flag = True
        else:
            sq = (int)(sq_double)+1
        for i in range(2, sq):
            if (num % i == 0):
                sums += i
                sums += num / i

        sums += sq if complte_sqrt_flag else 0
        return True if sums == num else False
s = Solution()
print(s.checkPerfectNumber(28))