import re
class Solution:
    def addBoldTag(self, s, dict):
        """
        :type s: str
        :type dict: List[str]
        :rtype: str
        """
        if len(dict) == 0:
            return s
        intervals = []
        for pattern in dict:
            st = s.find(pattern)
            if st != -1:
                intervals.append([st, st+len(pattern)])
        intervals.sort()
        print(intervals)
        mergedIntervals = self.mergeintervals(intervals)
        print(mergedIntervals)
        res = []
        intervalPtr = 0
        for i in range(len(s)+1):
            if intervalPtr < len(intervals):
                if i == intervals[intervalPtr][0]:
                    res.append("<b>")
                if i == intervals[intervalPtr][1]:
                    res.append("</b>")
                    intervalPtr += 1
                if i < len(s):
                    res.append(s[i])
            else:
                if i < len(s):
                    res.append(s[i])
        return "".join(res)

    def mergeintervals(self, intrevals):
        stack = [intrevals[0]]
        for interval in intrevals[1:]:
            top = stack[-1]
            if top[0] <= interval[0] <= top[1]:
                top[1] = max(top[1], interval[1])
            else:
                stack.append(interval)
        return stack

    def findAll(self, s, pattern):
        st = 0
        res = []
        while st < (len(s) - len(pattern)):
            r = s.find(pattern, start=st, end=len(s))
            if r != -1:
                res.append(r)
                st += len(pattern)
            else:
                break
        return res

if __name__ == "__main__":
    s = Solution()
    str0 = "abcxyz123"
    dict = ["abc", "123"]
    #str0 = "aaabbcc"
    #dict = ["aaa", "aab", "bc"]

    print(s.findAll("ababccababc", "ab"))
    print(s.addBoldTag(str0, dict))