# Definition for an interval.
import operator
class Interval(object):
    def __init__(self, s=0, e=0):
        self.start = s
        self.end = e

    @staticmethod
    def genIntervals(li):
        res = []
        for pair in li:
            res.append(Interval(s=pair[0], e=pair[1]))
        return res


class Solution(object):
    def merge(self, intervals):
        """
        :type intervals: List[Interval]
        :rtype: List[Interval]
        """
        if len(intervals) == 0:
            return []

        intervals = sorted(intervals, key=operator.attrgetter('start'))
        stack = []
        stack.append(intervals[0])
        for i in range(1, len(intervals)):
            if len(stack) > 0:
                res = self.mergeTwo(stack[-1], intervals[i])
                if len(res) == 2:
                    stack.append(intervals[i])
                else:
                    stack.pop()
                    stack.append(res[0])
            else:
                stack.append(intervals[i])

        return stack

    def mergeTwo(self, intervalA, intervalB):
        """

        :param intervalA: intervals
        :param intervalB: intervals
        :return:
        """
        if intervalA.start > intervalB.start:
            intervalA, intervalB = intervalB, intervalA
        # assure sa <= sb
        if intervalB.start > intervalA.end:
            return [intervalA, intervalB]
        else:
            return [Interval(s=min(intervalA.start, intervalB.start), e=max(intervalA.end, intervalB.end)), ]

if __name__ == "__main__":
    s = Solution()

    l0 = [[1, 10], [2, 9], [3, 8], [4, 7], [5, 6]]
    l1 = [[1, 3], [2, 4], [5, 7], [6, 8], [8, 10]]
    l2 = [[1, 2], [2, 3], [4, 5], [6, 7], [8, 9]]
    l0 = Interval.genIntervals(l0)
    l1 = Interval.genIntervals(l1)
    l2 = Interval.genIntervals(l2)
    for i in s.merge(l0):
        print(i.start, i.end)
    print("----------------")
    for i in s.merge(l1):
        print(i.start, i.end)
    print("----------------")
    for i in s.merge(l2):
        print(i.start, i.end)
