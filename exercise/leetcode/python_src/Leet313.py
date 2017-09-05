class Solution(object):
    def nthSuperUglyNumber(self, n, primes):
        """
        :type n: int
        :type primes: List[int]
        :rtype: int
        """
        res = [1, ]
        primesIdx = [0, ] * len(primes)

        for i in range(1, n):
            nextUgly = 0xffffffff
            for j in range(len(primes)):
                nextUgly = min(nextUgly, res[primesIdx[j]] * primes[j])

            res.append(nextUgly)
            for j in range(len(primes)):
                if res[primesIdx[j]] * primes[j] == nextUgly:
                    primesIdx[j] += 1

        return res[n - 1]

if __name__ == "__main__":
    s = Solution()
    n = 100000
    primes = [7, 19, 29, 37, 41, 47, 53, 59, 61, 79, 83, 89, 101, 103, 109, 127, 131, 137, 139, 157, 167, 179, 181, 199, 211,
     229, 233, 239, 241, 251]
    print(s.nthSuperUglyNumber(n, primes))