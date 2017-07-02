class Solution(object):
    def partition(self, s):
        """
        :type s: str
        :rtype: List[List[str]]
        """
        if len(s) == 0:
            return [""]
        record = [None for i in range(len(s))]  # include
        record[0] = [[s[0]]]
        for i in range(1, len(s)):
            tmpRes = []
            for otherPalind in self.findlastPalindrome(s, i + 1):
                if otherPalind - 1 >= 0:
                    for j in record[otherPalind - 1]:
                        cp = j[:]
                        cp.append(s[otherPalind: i + 1])
                        tmpRes.append(cp)
                else:
                    tmpRes.append([s[: i+1]])
            record[i] = tmpRes
        return record[i]

    def findlastPalindrome(self, s, ed):
        res = []
        for i in range(ed):
            if self.validPalindrome(s, i, ed):
                res.append(i)
        return res

    def validPalindrome(self, s, st, ed):
        i0 = st
        i1 = ed - 1
        while i0 < i1:
            if s[i0] == s[i1]:
                i0 += 1
                i1 -= 1
            else:
                return False
        return True

if __name__ == "__main__":
    s = Solution()
    str0 = "xaax"
    res = s.partition(str0)
    #print(s.findlastPalindrome(str0, len(str0)))
    for r in res:
        print(r)