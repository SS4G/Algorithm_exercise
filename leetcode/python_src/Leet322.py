class Solution(object):
    def coinChange(self, coins, amount):
        """
        :type coins: List[int]
        :type amount: int
        :rtype: int
        """
        if amount == 0:
            return 0
        coins.sort()
        record = [-2, ]*(amount + 1)
        res = self.coinChangeRecursive(coins, amount, record)
        return res

    def coinChangeRecursive(self, coins, amount, record):
        if record[amount] != -2:
            return record[amount]
        else:
            minAmount = 0xffffffff
            for coin in coins:
                if amount - coin == 0:
                    record[amount] = 1
                    minAmount = -1
                    return 1
                elif amount - coin < 0:
                    break
                else:
                    lastAmount = self.coinChangeRecursive(coins, amount - coin, record)
                    if lastAmount != -1:
                        minAmount = min(lastAmount, minAmount)
                    else:
                        pass
            if minAmount == 0xffffffff:
                record[amount] = -1
                return -1
            else:
                record[amount] = minAmount + 1
                return minAmount + 1

if __name__ == "__main__":
    s = Solution()
    coins = [1, 2, 5]
    amount = 100
    print(s.coinChange(coins, amount))