class Solution(object):
    def __init__(self):
        self.curIndex = 0

    def isValidSerialization(self, preorder):
        """
        :type preorder: str
        :rtype: bool
        """
        if len(preorder) == 0:
            return False
        self.curIndex = 0
        preorder = preorder.split(",")
        return self.isValidRestPart(preorder, self.curIndex) and self.curIndex == len(preorder)

    def isValidRestPart(self, preorder, st):
        if preorder[st] != "#":
            self.curIndex += 1
            if len(preorder) == self.curIndex:
                return False
            resLeft = self.isValidRestPart(preorder, self.curIndex)
            if len(preorder) == self.curIndex:
                return False
            resRight = self.isValidRestPart(preorder, self.curIndex)
            return resLeft and resRight
        else:
            self.curIndex += 1
            return True

if __name__ == "__main__":
    s = Solution()
    ser = "9"
    print(s.isValidSerialization(ser))