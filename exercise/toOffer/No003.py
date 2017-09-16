# -*- coding:utf-8 -*-
class Solution:
    # array 二维列表
    def Find(self, target, array):
        if array is None:
            return False
        yLength = len(array)
        if yLength <= 0:
            return False
        xLength = len(array[0])
        if xLength <= 0:
            return False

        xIdx = xLength - 1
        yIdx = 0
        while True:
            if array[yIdx][xIdx] == target:
                return True
            elif array[yIdx][xIdx] > target:
                xIdx -= 1
            else:
                yIdx += 1

            if xIdx < 0 or yIdx >= yLength:
                return False

if __name__ == "__main__":
    mat = [
        [1, 2, 8, 9],
        [2, 4, 9, 12],
        [4, 7, 10, 13],
        [6, 8, 11, 15]
           ]
    s = Solution()
    assert s.Find(10, mat)
    assert s.Find(7, mat)
    assert s.Find(6, mat)
    assert s.Find(1, mat)
    assert not s.Find(30, mat)
    assert not s.Find(3, mat)
