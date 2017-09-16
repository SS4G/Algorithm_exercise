# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None
import AlgorithmTraining.exercise.leetcode.python_src.MyUtil.Utils as myu


class Solution(object):
    def rotateRight(self, head, k):
        """
        :type head: ListNode
        :type k: int
        :rtype: ListNode
        """
        tmp = head
        if tmp is None:
            return None
        length = 1

        while tmp.next is not None:
            length += 1
            tmp = tmp.next
        tmp.next = head  # make lisk a circle
        offset = length - (k % length)

        if offset == 0:
            head.next = None
        else:
            for i in range(offset - 1):
                head = head.next
            newHead = head.next
            head.next = None
        return newHead

if __name__ == "__main__":
    s = Solution()
    arr = [i for i in range(10)]
    list0 = myu.LinkedListUtil.genList(arr)
    head = s.rotateRight(list0, 2)
    myu.LinkedListUtil.showList(head)