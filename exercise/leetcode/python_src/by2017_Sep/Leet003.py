class Solution(object):
    def lengthOfLongestSubstring(self, s):
        """
        :type s: str
        :rtype: int
        """
        if len(s) == 0:
            return 0
        maxLen = 0
        ptr0 = 0
        ptr1 = 0
        uniqueSet = set([])
        while True:
            while ptr0 < len(s) and (s[ptr0] not in uniqueSet):
                uniqueSet.add(s[ptr0])
                ptr0 += 1
            if ptr0 == len(s):
                maxLen = max(ptr0 - ptr1, maxLen)
                break
            else:
                maxLen = max(ptr0 - ptr1, maxLen)
                while s[ptr1] != s[ptr0]:
                    uniqueSet.remove(s[ptr1])
                    ptr1 += 1
                ptr1 += 1
                ptr0 += 1
        return maxLen

if __name__ == "__main__":
    s = Solution()
    st = ""
    print(s.lengthOfLongestSubstring(st))


