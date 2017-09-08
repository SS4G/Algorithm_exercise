from AlgorithmTraining.G55Utils.Py.Utils import *
class Solution:
    # 返回从尾部到头部的列表值序列，例如[1,2,3]
    def printListFromTailToHead(self, listNode):
        # write code here
        stack = []
        ptr = listNode
        while ptr is not None:
            stack.append(ptr.val)
            ptr = ptr.next
        stack.reverse()
        return stack

if __name__ == "__main__":
    s = Solution()
    head = LinkedListUtil.genList([1, 2, 3, 4, 5])
    print(s.printListFromTailToHead(head))


