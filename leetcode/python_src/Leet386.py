class Solution(object):
    def lexicalOrder(self, n):
        """
        :type n: int
        :rtype: List[int]
        """

        res = [1, ] * n
        for i in range(1, n):
            last = res[i - 1]
            if last * 10 <= n:
                res[i] = last * 10
            else:
                while True:
                    lastBit = last % 10
                    if lastBit < 9:
                        last += 1
                        res[i] = last
                    else:
                        while last % 10 == 9:
                            last //= 10
                        last += 1
                    if last <= n:
                        break
                    else:
                        last //= 10
                res[i] = last
        return res

if __name__ == "__main__":
    s = Solution()
    print(s.lexicalOrder(13))