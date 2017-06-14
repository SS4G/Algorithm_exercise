# Definition for singly-linked list.
from AlgorithmTraining.G55Utils.Py.Utils import *

class Solution(object):
    def reorderList(self, head):
        """
        :type head: ListNode
        :rtype: void Do not return anything, modify head in-place instead.
        """
        preSlow = head
        if head is None:  # 0node
            return None
        slowPtr = head.next
        if slowPtr is None:  # 1node
            return head
        fastptr = head.next.next
        if fastptr is None:  # 2node
            return head

        frontPart = []
        frontPart.append(head)
        while fastptr is not None and fastptr.next is not None:
            frontPart.append(slowPtr)
            fastptr = fastptr.next.next
            preSlow = slowPtr
            slowPtr = slowPtr.next

        preSlow.next = None
        behindPart = []
        while slowPtr is not None:
            behindPart.append(slowPtr)
            slowPtr = slowPtr.next
        behindPart.reverse()

        for i in range(len(frontPart)-1):
            frontPart[i].next = behindPart[i]
            behindPart[i].next = frontPart[i+1]
        frontPart[len(frontPart)-1].next = behindPart[len(frontPart)-1]
        if len(behindPart) == len(frontPart):
            behindPart[len(frontPart) - 1].next = None
        else:
            behindPart[len(frontPart) - 1].next = behindPart[len(frontPart)]
            behindPart[len(frontPart)].next = None
        return

if __name__ == "__main__":
    s = Solution()
    arr0 = [0, 1, 2, 3, 4, 5, 6]
    arr1 = [0, 1, 2, 3, 4, 5]
    arr2 = [0, 1, 2]
    arr3 = [0, 1]
    arr4 = [0,]
    l = LinkedListUtil.genList(arr4)
    s.reorderList(l)
    LinkedListUtil.showList(l)








