# -*- coding:utf-8 -*-
class Solution:
    # s 源字符串
    def replaceSpace(self, s):
        # write code here
        newBuffer = []
        for c in s:
            if c == ' ':
                newBuffer.append("%20")
            else:
                newBuffer.append(c)
        return "".join(newBuffer)

if __name__ == "__main__":
    s = Solution()
    print(s.replaceSpace("hello world! by jeff!"))
