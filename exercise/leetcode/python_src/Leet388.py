class Solution(object):
    def lengthLongestPath(self, input):
        """
        :type input: str
        :rtype: int
        """
        FILE = 22
        DIR = 11
        splited = input.split("\n")
        rootDir = {}
        stack = [rootDir]
        curLayer = 0  # amount of \t  +1
        for i in splited:
            layer, name = self.parser(i)
            while curLayer >= layer:
                stack.pop()
                curLayer -= 1
            if "." in name:
                stack[-1][name] = FILE  # add file to cwd
            else:
                stack[-1][name] = {}
                stack.append(stack[-1][name])  # chdir to cwd.subdir
                curLayer += 1
        # print(rootDir)
        stack2 = []
        output = []
        self.recursive(stack[0], stack2, output)
        max0 = 0
        for o in output:
            thisLen = len(o) - 1 + sum([len(j) for j in o])
            max0 = max(max0, thisLen)
        return max0

    def parser(self, part):
        i = 0
        while part[i] == '\t':
            i += 1
        return i + 1, part[i:]

    def recursive(self, dir, stack, output):
        for key in dir:
            stack.append(key)
            if isinstance(dir[key], int):
                output.append(stack[:])
            else:
                self.recursive(dir[key], stack, output)
            stack.pop()

if __name__ == "__main__":
    s = Solution()
    input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
    input = "a"
    print(s.lengthLongestPath(input))


