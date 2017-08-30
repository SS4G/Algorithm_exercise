import collections
import operator


class Solution:
    def findLongestChain(self, pairs):
        """
        :type pairs: List[List[int]]
        :rtype: int
        """
        if len(pairs) == 0:
            return 0
        dictTail = collections.defaultdict(list)
        for p in pairs:
            dictTail[p[1]].append(p)
        pairs.sort(key=operator.itemgetter(1))
        tails = list(dictTail.keys())
        tails.sort()
        dictEnd = {}
        for p in pairs:
            tailIdx = self.findLastLittle(tails, p[0])
            if tailIdx == -1:
                if p[1] not in dictEnd:
                    dictEnd[p[1]] = 1
                else:
                    dictEnd[p[1]] = max(1, dictEnd[p[1]])
            maxLen = -1

            for j in range(tailIdx + 1):
                maxLen = max(dictEnd[tails[j]], maxLen)

            if p[1] not in dictEnd:
                dictEnd[p[1]] = maxLen + 1
            else:
                dictEnd[p[1]] = max(maxLen + 1, dictEnd[p[1]])
        return max(dictEnd.values())

    def findLastLittle(self, arr, val):
        if val < arr[0]:
            return -1
        li = 0
        hi = len(arr) - 1
        while li <= hi:
            mid = (li + hi) >> 1
            if arr[mid] < val:
                li = mid + 1
            elif arr[mid] > val:
                hi = mid - 1
            else:
                return mid - 1
        return hi

if __name__ == "__main__":
    s = Solution()
    pairs = [[1, 2], [2, 3], [3, 4], [5, 6]]
    pairs = [[1, 5], [2, 3], [4, 5], [6, 7]]
    pairs = [[-275,-20],[-750,402],[35,618],[232,525],[769,939],[571,809],[-488,302],[429,557],[210,725],[55,862],[963,997],[217,491],[-336,94],[-849,54],[787,955],[-972,-801],[-555,692],[-954,-57],[-92,602],[640,782],[591,889]]
    print(s.findLongestChain(pairs))