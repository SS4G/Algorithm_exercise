# Below is the interface for Iterator, which is already defined for you.


class Iterator(object):
    def __init__(self, nums):
        """
        Initializes an iterator object to the beginning of a list.
        :type nums: List[int]
        """
        self.iters = nums

    def hasNext(self):
        """
        Returns true if the iteration has more elements.
        :rtype: bool
        """
        return len(self.iters) > 0

    def next(self):
        """
        Returns the next element in the iteration.
        :rtype: int
        """
        return self.iters.pop()


class PeekingIterator(object):
    def __init__(self, iterator):
        """
        Initialize your data structure here.
        :type iterator: Iterator
        """
        self.length = 0
        self.storage = [None, ] * 1000000
        self.peekPtr = 0

        while iterator.hasNext():
            if self.length < 1000000:
                self.storage[self.length] = iterator.next()
                self.length += 1
            else:
                self.storage.append(iterator.next())
                self.length += 1
        #print(self.storage[:self.length])

    def peek(self):
        """
        Returns the next element in the iteration without advancing the iterator.
        :rtype: int
        """
        if self.length > 0:
            peekElement = self.storage[self.peekPtr]
            return peekElement
        else:
            return None

    def next(self):
        """
        :rtype: int
        """
        if self.length > 0:
            peekElement = self.storage[self.peekPtr]
            self.peekPtr += 1
            self.length -= 1
            return peekElement
        else:
            return None

    def hasNext(self):
        """
        :rtype: bool
        """
        return self.length > 0


        # Your PeekingIterator object will be instantiated and called as such:
        # iter = PeekingIterator(Iterator(nums))
        # while iter.hasNext():
        #     val = iter.peek()   # Get the next element but not advance the iterator.
        #     iter.next()         # Should return the same value as [val].

if __name__ == "__main__":
    it = Iterator([1, 2, 3, 4, 5])
    s = PeekingIterator(it)
    cnt = 0
    while s.hasNext():
        print("cnt = ", cnt)
        print("p", s.peek())
        print("n", s.next())
        cnt += 1