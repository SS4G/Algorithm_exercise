class Solution(object):
    def canCompleteCircuit(self, gas, cost):
        """
        :type gas: List[int]
        :type cost: List[int]
        :rtype: int
        """
        if sum(gas) < sum(cost):
            return -1
        absGas = [gas[i] - cost[i] for i in range(len(gas))]
        startIndex = 0
        N = len(gas)
        totalLeft = 0
        index = startIndex
        while index < N - 1:
            totalLeft += absGas[index]
            if totalLeft < 0:
                totalLeft = 0
                startIndex = (index + 1) % N
            index += 1
        return startIndex

if __name__ == "__main__":
    s = Solution()
    gas = [2, 2, 0, 2, 2, 2]
    cost = [1, 1, 6, 1, 1, 1]
    print(s.canCompleteCircuit(gas, cost))

