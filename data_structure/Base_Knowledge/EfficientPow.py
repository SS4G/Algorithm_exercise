# coding=utf-8
class EfficientPow:
    """
    高效的求的幂运算的方法 折半相乘
    """
    def __init__(self):
        pass

    def is_even(self, n):
        return False if n & 0x00000001 else True

    def EPow(self, x, n):
        """
        Complexity:O(log(n))
        :param x:
        :param n:
        :return:x^n
        """
        if n == 0:
            return 1
        elif n == 1:
            return x
        elif self.is_even(n):
            return self.EPow(x*x, n >> 1)
        else:
            return self.EPow(x*x, n >> 1)*x

# Test
if __name__ == "__main__":
    t = EfficientPow()
    assert t.EPow(2, 0) == 1
    assert t.EPow(2, 1) == 2
    assert t.EPow(2, 10) == 1024
    assert t.EPow(2, 11) == 2048
    print("test process terminated successfully!")
