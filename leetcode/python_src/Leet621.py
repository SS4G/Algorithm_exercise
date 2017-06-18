class Solution(object):
    def leastInterval(self, tasks, n):
        """
        :type tasks: List[str]
        :type n: int
        :rtype: int

        """
        # $todo Wrong Answer
        types = {}
        for t in tasks:
            if t not in types:
                types[t] = 1
            else:
                types[t] += 1

        totalTasks = [types[t] for t in types]
        totalTasks.sort(reverse=True)
        totalIntervals = 0
        while True:
            noneZero = self.getNoneZero(totalTasks)
            if 0 < noneZero < n + 1:
                totalIntervals += ((max(totalTasks)-1)*(n+1) + self.getEqualFirst(totalTasks)) # ??
                break
            elif noneZero == 0:
                break
            else:
                minx = min(totalTasks[0: n + 1])
                for i in range(n + 1):
                    totalTasks[i] -= minx
                totalIntervals += minx*(n + 1)
            totalTasks.sort(reverse=True)
        return totalIntervals

    def getNoneZero(self, arr):
        cnt = 0
        for i in arr:
            if i != 0:
                cnt += 1
            else:
                break
        return cnt

    def getEqualFirst(self, arr):
        cnt = 1
        assert len(arr) >= 1, "invalid args"
        for i in range(1, len(arr)):
            if arr[i] == arr[0]:
                cnt += 1
            else:
                break
        return cnt

if __name__ == "__main__":
    s = Solution()
    # tasks = ['A', 'A', 'A', 'B', 'B', 'B']
    # n = 2
    tasks = ['A', 'A', 'A', 'B', 'B', 'B', 'C', 'C']
    n = 1
    print(s.leastInterval(tasks, n))

