class Solution:
    def countSubstrings(self, s):
        processed = self.preProcess(s)
        allLongest = []
        for i in range(len(processed)):
            radius = 1
            while i - radius >= 0 and i + radius < len(processed) and processed[i + radius] == processed[i - radius]:
                radius += 1
            radius -= 1
            if radius >= 1:
                allLongest.append(radius)

        sum = 0
        for i in allLongest:
            if i & 0x01 == 1:
                sum += ((i >> 1) + 1)
            else:
                sum += (i >> 1)
        return sum

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

    print(s.countSubstrings("aaa"))