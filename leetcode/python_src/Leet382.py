# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None
from AlgorithmTraining.G55Utils.Py.Utils import *
import random

class Solution(object):
    def __init__(self, head):
        """
        @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node.
        :type head: ListNode
        """
        self.head = head
        self.cnt = 0
        self.pre = None

    def getRandom(self):
        """
        Returns a random node's value.
        :rtype: int
        """
        tmp = self.head
        cnt = 0
        r = None
        while tmp is not None:
            rand = random.randint(0, cnt)
            if rand == 0:
                r = tmp.val
            cnt += 1
            tmp = tmp.next
        return r

        # Your Solution object will be instantiated and called as such:
        # obj = Solution(head)
        # param_1 = obj.getRandom()

if __name__ == "__main__":
    arr = [1, 2, 3, 4, 5, 6]

    s = Solution(LinkedListUtil.genList(arr))
    print(s.getRandom())
    print(s.getRandom())
    print(s.getRandom())
    print(s.getRandom())
    print(s.getRandom())
