class Solution(object):
    def findMinDifference(self, timePoints):
        """
        :type timePoints: List[str]
        :rtype: int
        """
        times = [self.toAbsolutSeconds(time) for time in timePoints]
        times.sort()
        return min([24*60 - times[-1] + times[0], ]+[times[i] - times[i - 1] for i in range(1, len(times))])

    def toAbsolutSeconds(self, timestr):
        l = [int(s) for s in timestr.split(":")]
        return l[0]*60 + l[1]

if __name__ == "__main__":
    s = Solution()
    print(s.findMinDifference(["12:12", "00:13"]))