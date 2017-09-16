class Solution(object):
    def permuteUnique(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        nums.sort()
        dicts = {}
        for i in nums:
            if i not in dicts:
                dicts[i] = 1
            else:
                dicts[i] += 1
        keys = list(dicts.keys())
        keys.sort()
        marked = [False, ]*len(nums)
        stack = []
        output = []
        finalOutput = []

        #print(keys)
        self.genOutput(dicts, keys, 0, marked, stack, output)
        #print(output)
        for o in output:
            partRes = [None, ] * len(nums)
            for i in range(len(o)):
                for index in o[i]:
                    partRes[index] = keys[i]
            finalOutput.append(partRes)
        return finalOutput

    def genOutput(self, dicts, keys, index, marked, stack, output):
        noMarkedYet = list(filter(lambda x: not marked[x], range(len(marked))))
        thisKeyCombines = self.getCombine(noMarkedYet, dicts[keys[index]])
        for combine in thisKeyCombines:
            stack.append(combine) # push
            self.changeMark(combine, marked, True)
            if index == len(keys) - 1:
                output.append(stack[:])
            else:
                self.genOutput(dicts, keys, index + 1, marked, stack, output)
            self.changeMark(combine, marked, False)
            stack.pop()  # pop

    def changeMark(self, list, marked, flag=True):
        for i in list:
            marked[i] = flag

    def getCombine(self, list, amount):
        stack = []
        output = []
        self.fillCombine(list, amount, 0, stack, output, True)
        finalOutput = [[list[i] for i in o] for o in output]
        return finalOutput

    def fillCombine(self, list, amount, pos, stack, output, firstInvoke=False):
        if firstInvoke:
            for i in range(len(list)):
                if len(list) - i < amount:
                    return
                stack.append(i)
                if pos == amount-1:
                    output.append(stack[:])
                else:
                    self.fillCombine(list, amount, 1, stack, output)
                stack.pop()
        else:
            for i in range(stack[-1]+1, len(list)):
                if len(list) - i < (amount - pos):
                    return
                stack.append(i)
                if pos == amount-1:
                    output.append(stack[:])
                else:
                    self.fillCombine(list, amount, pos+1, stack, output)
                stack.pop()
            return

if __name__ == "__main__":
    s = Solution()
    #arr = [1, 2, 3, 4, 4, 4]
    #res = s.permuteUnique(arr)
    arrk = [1, 1, 3]
    # arrk = [1]
    #print(s.permuteUnique(arrk))
    print(s.permuteUnique(arrk))