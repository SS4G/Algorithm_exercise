## 284. Peeking Iterator
Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().

Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].

Call next() gets you 1, the first element in the list.

Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.

You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.

Follow up: How would you extend your design to be generic and work with all types, not just integer?

#### tips
系统设计题目 寻找极值 可以使用O(n)的方法去扫 也可以使用O(lgn)的方法去二分查找极值 这个方法需要划重点

#### mycode

```
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
```
