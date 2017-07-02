## 388. Longest Absolute File Path

Suppose we abstract our file system by a string in the following manner:

The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:


```
dir
    subdir1
    subdir2
        file.ext
```

The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:


```
dir
    subdir1
        file1.ext
        subsubdir1
    subdir2
        subsubdir2
            file2.ext
```

The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).

Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.

Note:
- The name of a file contains at least a . and an extension.
- The name of a directory or sub-directory will not contain a ..
- Time complexity required: O(n) where n is the size of the input string.
- 
- Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
- 


#### tips
这个题目的特点就是 构造一个多层的嵌套字典对象来 实现类似文件夹的嵌套 可以吧一个字典看做是文件夹 里面存放的字符串的键是文件名 如果键对应的对象仍然是一个字典 就说明他还是一个文件夹 用一个对象指针来跟中字典 来指示当前的文件夹 使用一个堆栈来跟中当前的绝对路径

最后通过递归 来遍历所有的文件夹 然后记录他们的路径 选出最长的路径即可

#### mycdoe

```
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
```

```

```
