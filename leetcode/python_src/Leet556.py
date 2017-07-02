class Solution(object):
    def nextGreaterElement(self, n):
        """
        :type n: int
        :rtype: int
        """
        numl = [int(i) for i in list(str(n))]
        j = len(numl) - 1
        flag = False
        while j > 0:
            if numl[j - 1] < numl[j]:
                flag = True
                break
            j -= 1

        if not flag:
            return -1
        else:
            # firstBitIndex = j - 1
            arr = list(set(numl[j - 1:]))
            arr.sort()
            i0 = arr.index(numl[j - 1])
            newFirstBit = arr[i0 + 1]
            flag2 = False
            newRes = []
            for i in numl[j - 1:]:
                if flag2:
                    newRes.append(i)
                else:
                    if i != newFirstBit:
                        newRes.append(i)
                    else:
                        flag2 = True
            newRes.sort()
            finalRes = numl[:j - 1] + [newFirstBit, ] + newRes
            finalVal = int("".join([str(i) for i in finalRes]))
            if finalVal > 0x7fffffff:
                return -1
            else:
                return finalVal

if __name__ == "__main__":
    s = Solution()
    n = 2147481200
    print(s.nextGreaterElement(n))