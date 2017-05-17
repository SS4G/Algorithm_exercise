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
if __name__ == "__main__":
    s = Solution()
    pid = [1, 3, 10, 5]
    ppid = [3, 0, 5, 3]
    kill = 5
    print(s.killProcess(pid, ppid, kill))
