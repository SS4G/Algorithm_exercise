import random
class Solution(object):
    def __init__(self, nums):
        """
        :type nums: List[int]
        """
        self.original = nums

    def reset(self):
        """
        Resets the array to its original configuration and return it.
        :rtype: List[int]
        """
        return self.original

    def shuffle(self):
        """
        Returns a random shuffling of the array.
        :rtype: List[int]
        """
        res = []
        marked = [False, ]*len(self.original)
        for i in range(len(self.original)):
            randIndex = random.randint(0, len(self.original) - 1 - i)
            restElement = list(filter(lambda x: not marked[x], range(len(self.original))))
            marked[restElement[randIndex]] = True
            res.append(self.original[restElement[randIndex]])

        return res

        # Your Solution object will be instantiated and called as such:
        # obj = Solution(nums)
        # param_1 = obj.reset()
        # param_2 = obj.shuffle()

if __name__ == "__main__":
    arr = [1, 2, 3, 4, 5, 6, 7]
    s = Solution(arr)
    for i in range(10):
        r0 = s.shuffle()
        print(r0)
