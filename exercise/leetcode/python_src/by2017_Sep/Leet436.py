# Definition for an interval.
import operator


class Interval(object):
    def __init__(self, s=0, e=0):
        self.start = s
        self.end = e


class Solution(object):
    def findRightInterval(self, intervals):
        """
        :type intervals: List[Interval]
        :rtype: List[int]
        """
        newIntervals = [(intervals[i].start, intervals[i].end, i) for i in range(len(intervals))]
        newIntervals.sort()
        intervalDict = {}
        for i in newIntervals:
            intervalDict[i[0]] = i

        result = [-1, ]*len(intervals)
        startIndexs = list(intervalDict.keys())
        startIndexs.sort()
        j = 0
        for i in intervals:
            ed = i.end
            edIndex = self.findNextGreater(startIndexs, ed)
            if edIndex < len(startIndexs):
                result[j] = intervalDict[startIndexs[edIndex]][2]
                # min([interval[2] for interval in intervalDict[edIndex]])
            else:
                result[j] = -1
            j += 1
        return result

    def findNextGreater(self, arr, target):
        """
        assume no duplicates in arr
        :param arr:
        :param target:
        :return:
        """
        lo = 0
        hi = len(arr) - 1
        while lo <= hi:
            mid = (lo + hi) >> 1
            if arr[mid] < target:
                lo = mid + 1
            elif arr[mid] == target:
                return mid
            else:
                hi = mid - 1
        return lo

def genIntervals(intSrc):
    res = []
    for i in intSrc:
        res.append(Interval(s=i[0], e=i[1]))
    return res

if __name__ == "__main__":
    s = Solution()
    # intervalsSrc = [[3, 4], [2, 3], [1, 2]]
    intervalsSrc = [ [1,4], [2,3], [3,4] ]
    # intervalsSrc = [[3, 4], ]
    intervals = genIntervals(intervalsSrc)
    print(s.findRightInterval(intervals))










