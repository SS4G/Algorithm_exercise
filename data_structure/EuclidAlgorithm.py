# coding=utf-8
class EuclidAlgorithm:
    """
    欧几里得算法 求最大公约数
    """
    def __init__(self):
        pass

    def Euclid0(self, m, n):
        """
        传说中的辗转相除法
        m n 迭代的结果符合两条规则
        r=m%n
        1.r 不小于mn的最大公约数
        2.r 肯定小于 max(m,n) 的1/2 一直在变小
        所以两个条件的焦点 就是最大公约数 至于是怎样收敛到 最大公约数的可以不必关心
        :return:   max common divisor of m,n
        law:
            if M>=N
            then M%N<M/2
        """
        if m < n:  # assure m>=n
            m, n = n, m
        if m % n == 0:
            return n
        else:
            return self.Euclid0(m, m % n)

    def Euclid1(self, m, n):
        """
        更相减损术
        :param m:
        :param n:
        :return: max common divisor of m,n
        """
        if m < n:  # assure m>=n
            m, n = n, m
        elif m == n:
            return m
        if m - n == 0:
            return n
        else:
            return self.Euclid1(n, m-n)  # 选取较小的两者接着迭代

# Test
if __name__ == "__main__":
    t = EuclidAlgorithm()
    # test case format (x, y ,max common divisor of x,y)
    test_case = [
        (15, 5, 5),
        (20, 25, 5),
        (19, 17, 1),
        (100, 101, 1),
        (43, 35, 1),
        (33, 44, 11),
    ]
    for case in test_case:
        assert t.Euclid0(case[0], case[1]) == case[2], "result wrong"
    for case in test_case:
        assert t.Euclid1(case[0], case[1]) == case[2], "result wrong"
    print("test process terminate successfully!")
