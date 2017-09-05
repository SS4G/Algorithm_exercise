class Solution(object):
    def isSubsequence(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """
        tDict = {}
        for c in set(list(t)):
            tDict[c] = []
        i = 0
        for c in t:
            tDict[c].append(i)
            i += 1
        lastIndex = -1
        for c0 in s:
            if c0 in tDict:
                greaterIndex = self.find1StGreater(tDict[c0], lastIndex)
                if greaterIndex == -1:
                    return False
                else:
                    lastIndex = greaterIndex
            else:
                return False
        return True

    def find1StGreater(self, l, target):
        """
        在有序的列表l中查找第一个大于target的数字
        :param l:
        :param target:
        :return: 返回结果 找不到返回 -1
        """
        lo = 0
        hi = len(l)-1
        bFlag = False
        if l[-1] <= target:  # not found
            return -1
        elif l[0] > target:
            return l[0]
        while hi >= lo:
            mid = (hi + lo) >> 1
            if l[mid] > target:
                hi = mid - 1
            elif l[mid] == target:
                bFlag = True
                break
            else:
                lo = mid + 1
        if bFlag:
            if mid < len(l) - 1:
                return l[mid+1]
            else:
                return -1
        else:
            return l[lo]

if __name__ == "__main__":
    s = Solution()
    print(s.find1StGreater([1, 3, 4, 5, 7, 9, 11], 11))
    assert s.isSubsequence("abc", "ahsdjbsdjkdsfkc") is True, "WA"
    assert s.isSubsequence("abu", "ahsdjbsdjkdsfkc") is False, "WA"
    assert s.isSubsequence("abc", "ahsdjabcjkdsfkc") is True, "WA"