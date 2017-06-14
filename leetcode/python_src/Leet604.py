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

if __name__ == "__main__":
    # s = StringIterator("L1e2t1C1o1d1e1")
    s = StringIterator("")
    while s.hasNext():
        print(s.next())
    print(s.hasNext())
    print("s"+s.next()+"s")
