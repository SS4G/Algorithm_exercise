from AlgorithmTraining.G55Utils.Py.Utils import *

class Solution(object):
    def deleteDuplicates(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        dummy = ListNode(-0xffffffff)
        dummy.next = head
        pre = dummy
        tmp = head
        while tmp is not None:
            if tmp.next is None:
                break
            if tmp.next.val != tmp.val:
                pre = tmp
                tmp = tmp.next
            else:
                thisVal = tmp.val
                while tmp is not None and tmp.val == thisVal:
                    tmp = tmp.next

                if tmp is None:
                    pre.next = None
                    break
                else:
                    pre.next = tmp
        return dummy.next

if __name__ == "__main__":
    s = Solution()
    arr = []
    head = LinkedListUtil.genList(arr)
    newHead = s.deleteDuplicates(head)
    LinkedListUtil.showList(newHead)

