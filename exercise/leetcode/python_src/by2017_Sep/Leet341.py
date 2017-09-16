# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
class NestedInteger(object):
   def isInteger(self):
       """
       @return True if this NestedInteger holds a single integer, rather than a nested list.
       :rtype bool
       """

   def getInteger(self):
       """
       @return the single integer that this NestedInteger holds, if it holds a single integer
       Return None if this NestedInteger holds a nested list
       :rtype int
       """

   def getList(self):
       """
       @return the nested list that this NestedInteger holds, if it holds a nested list
       Return None if this NestedInteger holds a single integer
       :rtype List[NestedInteger]
       """

class NestedIterator(object):
    def __init__(self, nestedList):
        """
        Initialize your data structure here.
        :type nestedList: List[NestedInteger]
        """
        self.flatres = []
        for obj in nestedList:
            self.flat(obj, self.flatres)
        self.ptr = 0

    def flat(self, obj, flated):
        ptr = 0
        if obj.isisInteger():
            flated.append(obj.getInteger())
        else:
            listobj = obj.getList()

        while ptr < len(listobj):
            self.flat(listobj[ptr], flated)
            ptr += 1

    def next(self):
        """
        :rtype: int
        """
        res = self.flatres[self.ptr]
        self.ptr += 1
        return res

    def hasNext(self):
        """
        :rtype: bool
        """
        return self.ptr < len(self.flatres)


        # Your NestedIterator object will be instantiated and called as such:
        # i, v = NestedIterator(nestedList), []
        # while i.hasNext(): v.append(i.next())

if __name__ == "__main__":
    nestedList = [[],[],[]]
    i, v = NestedIterator(nestedList), []
    while i.hasNext():
        v.append(i.next())
    print(v)
