class Solution(object):
    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: str
        """
        # use Manacher Algorithm
        processed = self.preProcess(s)
        mx = 0
        id0 = 0
        resLongest = 0
        mid = -1
        mark = [1, ]*len(processed)
        for i in range(len(processed)):
            mark[i] = 1 if i > mx else min(mark[(id0 << 1) - i], mx - i + 1)
            while 0 <= i + mark[i] < len(processed) and 0 <= i - mark[i] < len(processed)\
                  and processed[i + mark[i]] == processed[i - mark[i]]:
                mark[i] += 1
            if i + (mark[i] - 1) > mx:
                id0 = i
                mx = i + (mark[i] - 1)
            if mark[i] > resLongest:
                resLongest = mark[i]
                mid = i

        return self.deProcesss(processed[mid - (mark[mid] - 1): mid + mark[mid]])

    def preProcess(self, s):
        res = []
        j = 0
        for i in range(len(s)*2 + 1):
            if i & 0x01 == 0:
                res.append('\0')
            else:
                res.append(s[j])
                j += 1
        return "".join(res)

    def deProcesss(self, s):
        return "".join([j for j in s if j != '\0'])


if __name__ == "__main__":
    s = Solution()
    print(s.longestPalindrome("xxabaa1abcdcbaswd"))
    print(s.longestPalindrome("abcba"))