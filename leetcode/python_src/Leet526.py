class Solution(object):
    def __init__(self):
        self.resCnt = 0

    def countArrangement(self, N):
        """
        :type N: int
        :rtype: int
        """
        self.resCnt = 0
        restNum = [True, ]*(N+1)
        restNum[0] = False  # 0 is always Fasle
        self.arrange(restNum, 1, N)
        return self.resCnt

    def arrange(self, restNum, startIndex, N):
        """

        :param restNum:
        :param startIndex: 1~N rather than 0~N-1
        :param N:
        :return:
        """
        for i in range(1, N+1):
            if restNum[i] is True:
                if (i % startIndex == 0) or (startIndex % i == 0):
                    if startIndex == N:
                        self.resCnt += 1
                    else:
                        restNum[i] = False
                        self.arrange(restNum, startIndex+1, N)
                        restNum[i] = True

if __name__ == "__main__":
    s = Solution()
    assert s.countArrangement(2) == 2, "WA"
    assert s.countArrangement(3) == 3, "WA"
    assert s.countArrangement(15) == 24679, "WA"
