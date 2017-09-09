## 134. Gas Station

There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
- The solution is guaranteed to be unique.
- 


#### tips
将gas数组和cost数组逐项相加 如 gas[0]+cost[0]
相加后的结果 就是到 某个站点 的燃料净增量

我们要寻找这样一条路径，路径中经过的所有点 加上这些点的净增量 邮箱中的燃料总量总是>=0的 

需要证明的一个命题就是 当总燃料量>=总消耗量 必然存在一个解
这个可以用反证法来证明

后面就是寻找这样的一个起始点。
选定起点0 
沿着 0->1->2->3....的路径走下去，当某个点的邮箱燃料储量为负时 以这个点的下一个点 作为起点从新开始 知道 检索指针超过第一次的起点0

（当然可以理解成在环形数组中找，最大的连续子序列一样 就像用dp找最大连续子序列一样）

所以 第一步 用总和来判断是否有这样的路径
第二步 用上述方法去找这样的路径

#### mycode

```
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
```
