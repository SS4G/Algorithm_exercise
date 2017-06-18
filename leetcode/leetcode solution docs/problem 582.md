## 582. Kill Process
Given n processes, each process has a unique PID (process id) and its PPID (parent process id).

Each process only has one parent process, but may have one or more children processes. This is just like a tree structure. Only one process has PPID that is 0, which means this process has no parent process. All the PIDs will be distinct positive integers.

We use two list of integers to represent a list of processes, where the first list contains PID for each process and the second list contains the corresponding PPID.

Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs of processes that will be killed in the end. You should assume that when a process is killed, all its children processes will be killed. No order is required for the final answer.


```
Example 1:
Input: 
pid =  [1, 3, 10, 5]
ppid = [3, 0, 5, 3]
kill = 5
Output: [5,10]
Explanation: 
           3
         /   \
        1     5
             /
            10
```

Kill 5 will also kill 10.
Note:
The given kill id is guaranteed to be one of the given PIDs.
n >= 1.
#### tips
先建立一个 ppid ->[pid0,pid1..]的映射
然后从kill开始 使用一个fifo来一边读取ppid一边将ppid对应的pid加入到fifo中
最后返回fifo即可
#### mycode
```Python
class Solution:
    def killProcess(self, pid, ppid, kill):
        """
        :type pid: List[int]
        :type ppid: List[int]
        :type kill: int
        :rtype: List[int]
        """
        ppidPidDict = {}
        for ppidPid in zip(ppid, pid):
            if ppidPid[0] not in ppidPidDict:
                ppidPidDict[ppidPid[0]] = []
                ppidPidDict[ppidPid[0]].append(ppidPid[1])
            else:
                ppidPidDict[ppidPid[0]].append(ppidPid[1])

        fifo = []
        fifo.append(kill)
        ptr = 0
        while ptr < len(fifo):
            if fifo[ptr] not in ppidPidDict:
                ptr += 1
                continue
            else:
                fifo.extend(ppidPidDict[fifo[ptr]])
                ptr += 1

        return fifo
```
