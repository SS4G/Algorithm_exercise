class Solution(object):
    def permute(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        nums.sort()
        output = []
        mark = [False, ]*len(nums)
        stack = []
        self.gen(nums, 0, mark, stack, output)
        return output

    def gen(self, arr, pos, mark, stack, output):
        useable = list(filter(lambda x: not mark[x], range(0, len(arr))))
        for i in useable:
            stack.append(arr[i])
            mark[i] = True
            if pos == len(arr) - 1:
                output.append(stack[:])
                stack.pop()
                mark[i] = False
            else:
                self.gen(arr, pos + 1, mark, stack, output)
                stack.pop()
                mark[i] = False
        return


if __name__ == "__main__":
    s = Solution()
    arr = [1, 2, 3, 4]
    res = s.permute(arr)
    print(len(res))
    for j in res:
        print(j)