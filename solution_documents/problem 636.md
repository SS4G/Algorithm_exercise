## 636. Exclusive Time of Functions
Given the running logs of n functions that are executed in a nonpreemptive single threaded CPU, find the exclusive time of these functions.

Each function has a unique id, start from 0 to n-1. A function may be called recursively or by another function.

A log is a string has this format : function_id:start_or_end:timestamp. For example, "0:start:0" means function 0 starts from the very beginning of time 0. "0:end:0" means function 0 ends to the very end of time 0.

Exclusive time of a function is defined as the time spent within this function, the time spent by calling other functions should not be considered as this function's exclusive time. You should return the exclusive time of each function sorted by their function id.

Example 1:
Input:
n = 2
logs = 

```
["0:start:0",
 "1:start:2",
 "1:end:5",
 "0:end:6"]
```

Output:[3, 4]
Explanation:
Function 0 starts at time 0, then it executes 2 units of time and reaches the end of time 1. 
Now function 0 calls function 1, function 1 starts at time 2, executes 4 units of time and end at time 5.
Function 0 is running again at time 6, and also end at the time 6, thus executes 1 unit of time. 
So function 0 totally execute 2 + 1 = 3 units of time, and function 1 totally execute 4 units of time.  


Note:
- Input logs will be sorted by timestamp, NOT log id.
- Your output should be sorted by function id, which means the 0th element of your output corresponds to the exclusive time of function 0.
- Two functions won't start or end at the same time.
- Functions could be called recursively, and will always end.
- 1 <= n <= 100


#### tips
注意这个题目中的结束时间，这个时间是要加一的 比如"1:end:5" 代表的是 在时刻6的开始结束的 题目原本的意思是 在5区间的末尾结束 所以要对数据做一个预处理

然后使用堆栈来解决这个题目

这里可以看做是多层嵌套，每个最外层的嵌套中的一个任务所占用的时间 等于他起止区间的总长度减去他内部第一层嵌套占用的总时间 注意这里不要算的重叠了 比如例子中所给 任务1占用时间4个单位 那么任务0的时间就是 7 - 4 = 3 个 即用外层减去内层的占用
一个任务可能出现多次 可能出现在多层， 所以要记得使用hashmap来累加的记录每个任务的总占用时间

程序中需要使用一个变量level来记录当前处于第几层的嵌套

#### mycode

```
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
```
