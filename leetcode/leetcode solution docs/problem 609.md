## 609. Find Duplicate File in System My SubmissionsBack To Contest

Given a list of directory info including directory path, and all the files with contents in this directory, you need to find out all the groups of duplicate files in the file system in terms of their paths.

A group of duplicate files consists of at least two files that have exactly the same content.

A single directory info string in the input list has the following format:


```
"root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"
```


It means there are n files (f1.txt, f2.txt ... fn.txt with content f1_content, f2_content ... fn_content, respectively) in directory root/d1/d2/.../dm. Note that n >= 1 and m >= 0. If m = 0, it means the directory is just the root directory.

The output is a list of group of duplicate file paths. For each group, it contains all the file paths of the files that have the same content. A file path is a string that has the following format:


```
"directory_path/file_name.txt"
```


Example 1:
Input:

```
["root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"]
```
Output:  

```
[["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]
```
##### Note:
- No order is required for the final output.
- You may assume the directory name, file name and file content only has letters and digits, and the length of file content is in the range of [1,50].
- The number of files given is in the range of [1,20000].
- You may assume no files or directories share the same name in a same directory.
- You may assume each given directory info represents a unique directory. Directory path and file infos are separated by a single blank space.


##### Follow up beyond contest:
1. Imagine you are given a real file system, how will you search files? DFS or BFS ?
1. If the file content is very large (GB level), how will you modify your solution?
1. If you can only read the file by 1kb each time, how will you modify your solution?
1. What is the time complexity of your modified solution? What is the most time consuming part and memory consuming part of it? How to optimize?
1. How to make sure the duplicated files you find are not false positive?



#### tips
题目很长 但是不难 只是一个对字符串的简单解析 然后用一个字典就可以解决   
[官方题解](https://leetcode.com/articles/find-duplicate/)

#### mycode
```
class Solution(object):
    def findDuplicate(self, paths):
        """
        :type paths: List[str]
        :rtype: List[List[str]]
        """
        conentDict = {}
        for i in paths:
            files = self.parser(i)
            for file in files:
                if file[1] not in conentDict:
                    conentDict[file[1]] = [file[0], ]
                else:
                    conentDict[file[1]].append(file[0])

        return [conentDict[content] for content in conentDict if len(conentDict[content]) >= 2]

    def parser(self, item):
        splited = item.split()
        path = splited[0]
        nameContents = splited[1:]
        fullNames = []
        for nameContent in nameContents:
            j = 0
            nameEnd = 0
            contenTEnd = 0
            while j < len(nameContent):
                if nameContent[j] == '(':
                    nameEnd = j
                elif nameContent[j] == ')':
                    contenTEnd = j
                    break
                j += 1
            name = nameContent[0: nameEnd]
            content = nameContent[nameEnd+1: contenTEnd]
            fullName = "".join([path, "/", name])
            fullNames.append((fullName, content))
        return fullNames

if __name__ == "__main__":
    s = Solution()
    names = ["root/a 1.txt(abcd) 2.txt(efsfgh)","root/c 3.txt(abdfcd)","root/c/d 4.txt(efggdfh)"]

    print(s.findDuplicate(names))
```
