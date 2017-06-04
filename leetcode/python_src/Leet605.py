class Solution(object):
    def canPlaceFlowers(self, flowerbed, n):
        """
        :type flowerbed: List[int]
        :type n: int
        :rtype: bool
        """
        i = 0
        if n == 0:
            return True
        elif len(flowerbed) == 1:  # n > 0
            if flowerbed[0] == 0 and n <= 1:
                return True
            else:
                return False

        continusO = 0
        i = 0
        while i < len(flowerbed):
            if i == 0:
                if flowerbed[0] == 0 and flowerbed[1] == 0:
                    n -= 1
                    flowerbed[0] = 1
                    i += 2
                else:
                    i += 1
            elif i == len(flowerbed) - 1:
                if flowerbed[i] == 0 and flowerbed[i - 1] == 0:
                    n -= 1
                    flowerbed[i] = 1
                    i += 2
                else:
                    i += 1
            else:
                if flowerbed[i] == 0 and flowerbed[i-1] == 0 and flowerbed[i+1] == 0:
                    flowerbed[i] = 1
                    n -= 1
                    i += 2
                else:
                    i += 1
        return not (n > 0)

if __name__ == "__main__":
    s = Solution()
    print(s.canPlaceFlowers([1, 0, 0, 0, 1], 1))
    print(s.canPlaceFlowers([1, 0, 0, 0, 1], 2))
    print(s.canPlaceFlowers([0, 1, 0, 1, 0], 2))
    print(s.canPlaceFlowers([0, 1, 0, 0, 1, 0], 2))
    print(s.canPlaceFlowers([0, ], 1))


