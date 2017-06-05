class Solution(object):
    def findRepeatedDnaSequences(self, s):
        """
        :type s: str
        :rtype: List[str]
        """
        dictS = {}
        output = set([])
        for i in range(len(s) - 10 + 1):
            k = s[i: i + 10]
            if k not in dictS:
                dictS[k] = 1
            else:
                output.add(k)
        return list(output)

if __name__ == "__main__":
    s = Solution()
    print(s.findRepeatedDnaSequences("AAAAAAAAAAAA"))