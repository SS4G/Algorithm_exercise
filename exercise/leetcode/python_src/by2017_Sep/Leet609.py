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
