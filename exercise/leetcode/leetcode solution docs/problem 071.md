## 71. Simplify Path

Given an absolute path for a file (Unix-style), simplify it.

For example,

```
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
```

#### tips
不说了 用一个堆栈 加上一个当前指针去跟踪当前所处的目录即可

需要注意的特殊情况是 当当前已经是根目录的情况下 如果还在要求返回上级目录 那么仍然保持根目录即可

#### mycode

```
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
```
