import operator
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
            # print(s, pattern)
            sts = self.findAll(s, pattern)
            for st in sts:
                intervals.append([st, st+len(pattern)])
        intervals.sort(key=operator.itemgetter(0))
        # print(intervals)
        if len(intervals) == 0:
            return s
        mergedIntervals = self.mergeintervals(intervals)
        # print(mergedIntervals)
        res = []
        intervalPtr = 0
        for i in range(len(s)+1):
            if intervalPtr < len(mergedIntervals):
                # print(intervalPtr)
                if i == mergedIntervals[intervalPtr][0]:
                    res.append("<b>")
                if i == mergedIntervals[intervalPtr][1]:
                    res.append("</b>")
                    intervalPtr += 1
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
        while st <= (len(s) - len(pattern)):
            r = s.find(pattern, st, len(s))
            # vprint(r)
            if r != -1:
                res.append(r)
                # st = r+len(pattern)
                st = r + 1  # 检索出类似于 aaa 中 查找 aa的 最后的区间应该是两个重叠的区间
            else:
                break
        return res

if __name__ == "__main__":
    s = Solution()
    str0 = "abcxyz123"
    dict = ["abc", "123"]
    # str0 = "aaabbcc"
    # dict = ["aaa", "aab", "bc"]
    str0 = "ababccababc"
    dict = "ab"
    str0 = "aaabbcc"
    dict = ["aaabbcc"]
    str0 = "qrzjsorbkmyzzzvoqxefvxkcwtpkhzbakuufbpgdkykmojwuennrjeciqvvacpzrrczfhxnsmginzwinzihpomxtmweyyzzmgcoiupjnidphvzlnxtcogufozlenjfvokztghwckzyvmktduqkizixzxpanjwrdeudjyftxksjgdklwxrhmudhrtemuvelykqaafzlqmennttkighcdxfozdcoqkyshhajipnsdrljrnlwmyjuwxsebpqm"
    dict = ["qr", "zj", "so", "rb", "km", "yz", "zz", "vo", "qx", "ef", "vx", "kc", "wt", "pk"]
    print(str0.find(dict[0], 0, len(str0)))
    # print(s.findAll("ababccababc", "ab"))
    print(s.addBoldTag(str0, dict))
