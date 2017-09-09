class Solution(object):
    def numDecodings(self, s):
        """
        :type s: str
        :rtype: int
        """
        if len(s) == 0:
            return 0

        record = [0, ] * len(s)  # include this idx
        for i in range(len(s)):
            if i == 0:
                if s[i] == '0':
                    return 0
                else:
                    record[0] = 1
            elif i == 1:
                if s[i] == '0':
                    record[i] = 1 if 1 <= int(s[i - 1]) <= 2 else 0
                else:
                    record[i] = 2 if 0 < int(s[i - 1: i + 1]) <= 26 else 1
            else:
                if s[i] == '0':
                    if 0 < int(s[i - 1]) <= 2:
                        record[i] = record[i - 2]
                    else:
                        record[i] = 0
                else:
                    thisAdd = record[i - 2] if s[i - 1] != '0' and 0 < int(s[i - 1: i + 1]) <= 26 else 0
                    record[i] = record[i - 1] + thisAdd
        #print(record)
        return record[len(s) - 1]

if __name__ == "__main__":
    s = Solution()
    # s0 = "110320"
    s0 = "3405"
    print(s.numDecodings(s0))