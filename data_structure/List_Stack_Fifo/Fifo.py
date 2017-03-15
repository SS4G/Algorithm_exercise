# coding=utf-8
# Author: SS4G
# Date 2017/03/09
# all test case passed


class Fifo:
    """
    环形fifo
    rear  指向一个有内容的空间
    front 指向一个空的空间
    """
    def __init__(self, size0):
        size = size0+1
        self.fifo_size = size
        self.get_fifo_available_space = size
        self.rear = 0  # 指向一个有内容的空间
        self.front = 0  # 指向一个空的空间
        self.fifo = [None]*size

    def in_queue(self, element):
        assert not ((self.front == self.fifo_size-1) and (self.rear == 0)), "ERROR: fifo already full!"  # 入队前检查是否满队
        assert not (self.front == self.rear-1), "ERROR: fifo already full!"  # 入队前检查是否满队

        self.fifo[self.front] = element
        self.front += 1
        if self.front == self.fifo_size:
            self.front = 0
        self.get_fifo_available_space -= 1

    def out_queue(self):
        assert not (self.rear == self.front), "ERROR: fifo is already empty"  # 出队之前查看是否空队
        val = self.fifo[self.rear]
        self.rear += 1
        if self.rear == self.fifo_size:
            self.rear = 0
        self.get_fifo_available_space += 1
        return val

    def get_fifo_available(self):
        return self.get_fifo_available_space-1

# Test
if __name__ == "__main__":
    f = Fifo(5)
    f.in_queue(1)
    f.in_queue(2)
    f.in_queue(3)
    f.in_queue(4)
    f.in_queue(5)
    print(f.get_fifo_available())
    print(f.out_queue(), f.get_fifo_available())
    print(f.out_queue(), f.get_fifo_available())
    print(f.out_queue(), f.get_fifo_available())
    print(f.out_queue(), f.get_fifo_available())
    print(f.out_queue(), f.get_fifo_available())
