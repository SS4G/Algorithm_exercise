class Solution(object):
    def removeKdigits(self, num, k):
        """
        :type num: str
        :type k: int
        :rtype: str
        """
        mark = [True, ]*len(num)
        zeroCnt = 0
        for i in num:
            if i == '0':
                zeroCnt += 1
        if k >= (len(num) - zeroCnt):  # 剥除次数大于非零字符的总数 全剥 完了 就只能是0了
            return "0"

        stack = []
        abortCnt = 0
        endFlag = False
        stack.append(num[0])
        checkedCnt = 1
        for i in num[1:]:
            if ord(i) < ord(stack[-1]):
                while len(stack) > 0 and ord(i) < ord(stack[-1]):
                    stack.pop()
                    abortCnt += 1
                    if abortCnt == k:
                        endFlag = True
                        break
                if endFlag:  # 已经删除了足够多的数字
                    break
                else:
                    stack.append(i)
            else:
                stack.append(i)
            checkedCnt += 1
            if endFlag:
                break

        if endFlag:
            for i in range(checkedCnt, len(num)):
                stack.append(num[i])
        else:
            for i in range(abortCnt, k):
                stack.pop()

        # strip leading zero
        j = 0
        while stack[j] == '0':
            j += 1

        return "".join(stack[j:])

if __name__ == "__main__":
    s = Solution()
    print(s.removeKdigits("100234567", 3))
    print(s.removeKdigits("1002345697654489", 2))
    #print(s.removeKdigits("10", 2))
    #print(s.removeKdigits("10", 1))
    #print(s.removeKdigits("1", 1))