## 341. Flatten Nested List Iterator

Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:

```
Given the list [[1,1],2,[1,1]],
```

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

Example 2:

```
Given the list [1,[4,[6]]],
```

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].

#### tips
使用堆栈来对列表对象进行跟踪  使用提供给的函数 来完成对象类别的判断 这个题目不要求使用 isinstance() 来判断

#### mycode
```
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
        if obj.isInteger():
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
```
