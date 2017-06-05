class BackTracking:
    """
    一般的回溯算法都是从根部开始搜索
    相当于数一个数 但是是从高位 到低位开始计数 如下
    111
    110
    101
    100
    011...
    """
    #  倒着数 3位十进制 除去其中十位为 4,5,6的数字 使用堆栈实现
    def BuildInTest(self):
        stack = [None, ]*3
        res = []
        self.traceBackNum0(res, stack, 0, 3)
        for i in res:
            print(i)

    def traceBackNum0(self, res, stack, nowIndex, N):
        """递归式"""
        for i in range(10):
            if nowIndex == 1 and (i in (4, 5, 6, 7)):
                continue
            stack[nowIndex] = i
            if nowIndex == N-1:
                res.append(stack.copy())
            else:
                self.traceBackNum0(res, stack, nowIndex+1, N)

    def traceBackNum1(self, res, stack, nowIndex, N):
        """非递归 确实不太好搞"""
        for i in range(10):
            if nowIndex == 1 and (i in (4, 5, 6, 7)):
                continue
            stack[nowIndex] = i
            if nowIndex == N-1:
                res.append(stack.copy())
            else:
                self.traceBackNum0(res, stack, nowIndex+1, N)

if __name__ == "__main__":
    b = BackTracking()
    b.BuildInTest()