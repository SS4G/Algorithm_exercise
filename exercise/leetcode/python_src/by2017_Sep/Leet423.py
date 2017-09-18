class Solution(object):
    def originalDigits(self, s):
        """
        :type s: str
        :rtype: str
        """
        chDict = {}
        for c in set(list("onetwothreefourfivesixseveneightninezero")):
            chDict[c] = 0

        for c in s:
            chDict[c] += 1

        numL = ["zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", ]
        # numL = [, , ,   , "seven",, "nine", ]
        # z - 0
        # w - 2
        # x - 6
        # g - 8
        # u - 4
        # o - 1
        # r - 3
        # f - 5
        # s - 7
        # n - 9
        fingerPrint = [None, ]*10
        for i in range(10):
            chDicttmp = {}
            for c in numL[i]:
                if c not in chDicttmp:
                    chDicttmp[c] = 1
                else:
                    chDicttmp[c] += 1
            fingerPrint[i] = chDicttmp
        res = []
        i = 0
        order = [0, 2, 6, 8, 4, 1, 3, 5, 7, 9]
        while i < 10:
            breakFlag = False
            for c in fingerPrint[order[i]]:
                if chDict[c] >= fingerPrint[order[i]][c]:
                    continue
                else:
                    breakFlag = True
                    break
            if not breakFlag:
                res.append(order[i])
                for c in fingerPrint[order[i]]:
                    chDict[c] -= fingerPrint[order[i]][c]
            else:
                i += 1
        res.sort()
        return "".join([str(i) for i in res])

if __name__ == "__main__":
    s = Solution()
    chars = "onetwothreefourfivesixseveneightninezero"
    print(s.originalDigits(chars))
