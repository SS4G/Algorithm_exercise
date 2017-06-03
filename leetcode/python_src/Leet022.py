class Solution(object):
    def generateParenthesis(self, n):
        result = []
        self.addParenthesis(result, "", 0, 0, n)
        return result

    def addParenthesis(self, res, partRes, openP, closeP, n):
        if len(partRes) == n << 1:
            res.append(partRes)
            return
        if openP < n:
            self.addParenthesis(res, partRes+"(", openP+1, closeP, n)
        if closeP < openP:
            self.addParenthesis(res, partRes + ")", openP, closeP+1, n)


if __name__ == "__main__":
    s = Solution()
    print(s.generateParenthesis(6))

