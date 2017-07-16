class Solution(object):
    def maxProfit(self, prices):
        """
        :type prices: List[int]
        :rtype: int
        """
        # 套路就是找出一个合理的状态转移方程并实现
        if len(prices) <= 1:
            return 0
        # state s is the money i have now
        s0 = [None, ] * len(prices)  # have 0 stock
        s1 = [None, ] * len(prices)  # have 1 stock
        s2 = [None, ] * len(prices)  # cool Down
        s0[0] = 0
        s1[0] = -prices[0]
        s2[0] = -0xffffffff
        for i in range(1, len(prices)):
            s0[i] = max(s2[i - 1], s0[i - 1])
            s1[i] = max(s0[i - 1] - prices[i], s1[i - 1])
            s2[i] = s1[i - 1] + prices[i]
        return max(s0[len(prices) - 1], s2[len(prices) - 1])

if __name__ == "__main__":
    s = Solution()
    prices = [1, 2, 3, 0, 2]
    prices = [1,2,3,1,2,3,2,1,2,3]
    prices = [1,1,0]
    prices = [6, 1, 3, 2, 4, 7]
    print(s.maxProfit(prices))

