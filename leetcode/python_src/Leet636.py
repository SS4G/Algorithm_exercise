class Solution:
    def exclusiveTime(self, n, logs):
        """
        :type n: int
        :type logs: List[str]
        :rtype: List[int]
        """
        logsFormated = [self.handleLog(i) for i in logs]
        excludeDict = {i: 0 for i in range(n)}
        stack = []
        level = 0
        levelDict = {}
        levelDict[0] = 0
        levelDict[1] = 0
        for log in logsFormated:
            if log[1] is True:
                stack.append(log)
                level += 1
                levelDict[level + 1] = 0
            else:
                lastLog = stack.pop()
                totalTime = log[2] - lastLog[2]
                lastLevelExclude = levelDict[level + 1]
                thisLevelTime = totalTime - lastLevelExclude
                levelDict[level] += totalTime
                excludeDict[log[0]] += thisLevelTime
                level -= 1
        #print(levelDict)
        #print(excludeDict)
        return [excludeDict[i] for i in sorted(list(excludeDict.keys()))]

    def handleLog(self, logx):
        sp = logx.split(":")
        logId = int(sp[0])
        logType = True if sp[1] == "start" else False
        logTs = int(sp[2]) if logType is True else int(sp[2]) + 1
        return logId, logType, logTs

if __name__ == "__main__":
    s = Solution()
    n = 2
    logs = [
     "0:start:0",
     "1:start:2",
     "1:end:5",
     "0:end:6"

    ]
    res = s.exclusiveTime(n, logs)
    print(res)