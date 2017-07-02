# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None
from AlgorithmTraining.G55Utils.Py.Utils import *
class Solution(object):
    def partition(self, head, x):
        """
        :type head: ListNode
        :type x: int
        :rtype: ListNode
        """
        lt = ListNode(0xffffffff)
        ge = ListNode(0xffffffff)

        ltPtr = lt
        gePtr = ge
        ptr = head
        while ptr is not None:
            if ptr.val < x:
                ltPtr.next = ListNode(ptr.val)
                ltPtr = ltPtr.next
            else:
                gePtr.next = ListNode(ptr.val)
                gePtr = gePtr.next
            ptr = ptr.next
        ltPtr.next = ge.next
        return lt.next

if __name__ == "__main__":
    s = Solution()
    arr = [1, 4, 3, 2, 5, 2]
    l = LinkedListUtil.genList(arr)
    l0 = s.partition(l, 3)
    LinkedListUtil.showList(l0)
