class Solution(object):
    def simplifyPath(self, path):
        """
        :type path: str
        :rtype: str
        """
        if len(path) <= 1:
            return "/"
        parts = [j for j in path.split("/") if len(j) > 0]
        pathStack = []
        for name in parts:
            if name not in (".", ".."):
                pathStack.append(name)
            elif name == ".":
                pass
            else:
                if len(pathStack) > 0:
                    pathStack.pop()
        finalParts = ["", ] + pathStack
        return "/".join(finalParts) if len(finalParts) > 1 else "/"

if __name__ == "__main__":
    s = Solution()
    path = "/home/"
    path = "/a/./b/../../c/"
    path = "/"
    path = ""
    path = "/.."
    path = "/../../../"
    print(s.simplifyPath(path=path))