## 604. Design Compressed String Iterator

Design and implement a data structure for a compressed string iterator. It should support the following operations: next and hasNext.

The given compressed string will be in the form of each letter followed by a positive integer representing the number of this letter existing in the original uncompressed string.

next() - if the original string still has uncompressed characters, return the next letter; Otherwise return a white space.
hasNext() - Judge whether there is any letter needs to be uncompressed.

Note:
Please remember to RESET your class variables declared in StringIterator, as static/class variables are persisted across multiple test cases. Please see here for more details.

Example:

StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");


```
iterator.next(); // return 'L'
iterator.next(); // return 'e'
iterator.next(); // return 'e'
iterator.next(); // return 't'
iterator.next(); // return 'C'
iterator.next(); // return 'o'
iterator.next(); // return 'd'
iterator.hasNext(); // return true
iterator.next(); // return 'e'
iterator.hasNext(); // return false
iterator.next(); // return ' '
```

#### tips
使用正则表达式来分别且分出数字和字符即可 haha
#### mycode
```
import re
class StringIterator(object):
    def __init__(self, compressedString):
        """
        :type compressedString: str
        """
        times = re.split("[a-z]|[A-Z]", compressedString)
        chars = re.split("\\d+", compressedString)
        self.chars = [i for i in chars if i != ""]
        self.times = [int(i) for i in times if i != ""]
        self.currentPtr = 0


    def next(self):
        """
        :rtype: str
        """
        if self.hasNext():
            res = self.chars[self.currentPtr]
            self.times[self.currentPtr] -= 1
            if self.times[self.currentPtr] == 0:
                self.currentPtr += 1
            return res
        else:
            return " "

    def hasNext(self):
        """
        :rtype: bool
        """
        return self.currentPtr < len(self.chars)

        # Your StringIterator object will be instantiated and called as such:
        # obj = StringIterator(compressedString)
        # param_1 = obj.next()
        # param_2 = obj.hasNext()
```
