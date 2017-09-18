class Solution(object):
    def distributeCandies(self, candies):
        """
        :type candies: List[int]
        :rtype: int
        """
        kinds = len(set(candies))
        length = len(candies)
        return kinds if kinds < (length >> 1) else length >> 1

