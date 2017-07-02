class Solution(object):
    def findSubsequences(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        allSubSequence = []
        for j in range(len(nums)):
            thisTurnArr = []
            for i in range(j, len(nums)):
                if nums[i] >= nums[j]:
                    thisTurnArr.append(nums[i])
            allSubSequence.append(thisTurnArr)
        #print(allSubSequence)
        finalRes = set([])
        for i in allSubSequence:
            finalRes = finalRes | self.genSubseq(i)
        return [list(i) for i in finalRes]

    def genSubseq(self, arr):
        stack = []
        output = set([])
        self.genSubSeqRecursive(arr, 0, stack, output)
        return output

    def genSubSeqRecursive(self, arr, stIndex, stack, output):
        if stIndex == len(arr):
            return
        else:
            stack.append(arr[stIndex])
            if stIndex != 0:
                output.add(tuple(stack[:]))
            for i in range(stIndex + 1, len(arr)):
                if arr[i] >= arr[stIndex]:
                    self.genSubSeqRecursive(arr, i, stack, output)
            stack.pop()
            return

if __name__ == "__main__":
    s = Solution()
    nums = [4, 6, 7, 7]
    print(s.findSubsequences(nums))