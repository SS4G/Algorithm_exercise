# Definition for singly-linked list with a random pointer.
from AlgorithmTraining.G55Utils.Py.Utils import *


class RandomListNode(object):
    def __init__(self, x):
        self.label = x
        self.next = None
        self.random = None


class Solution(object):
    def copyRandomList(self, head):
        """
        :type head: RandomListNode
        :rtype: RandomListNode
        """
        dict0 = {}
        if head is None:
            return None
        else:
            tmp = head
            i = 0
            while tmp is not None:
                dict0[tmp] = i
                i += 1
                tmp = tmp.next
            size = i
            dummy = RandomListNode(0xffffffff)

            tmp0 = head
            tmp1 = dummy
            nodes = []
            while tmp0 is not None:
                tmp1.next = RandomListNode(tmp0.label)
                tmp1 = tmp1.next
                nodes.append(tmp1)
                tmp0 = tmp0.next
            i = 0
            tmp1 = dummy.next
            tmp0 = head
            while i < size:
                if tmp0.random is not None:
                    tmp1.random = nodes[dict0[tmp0.random]]
                else:
                    tmp1.random = None
                i += 1
                tmp1 = tmp1.next
                tmp0 = tmp0.next
            return dummy.next

if __name__ == "__main__":
    list0 = []
    n0 = RandomListNode(0)
    n1 = RandomListNode(1)
    n2 = RandomListNode(2)
    n3 = RandomListNode(3)
    list0.append(n0)
    list0.append(n1)
    list0.append(n2)
    list0.append(n3)
    n0.next = n1
    n1.next = n2
    n2.next = n3
    n0.random = n3
    n1.random = n3
    n2.random = n0
    n3.random = None

    s = Solution()
    nn0 = s.copyRandomList(n0)
    nn1 = nn0.next
    nn2 = nn1.next
    nn3 = nn2.next
    print(nn0.label)
    print(nn1.label)
    print(nn2.label)
    print(nn3.label)
    print("------")
    print(nn0.random.label)
    print(nn1.random.label)
    print(nn2.random.label)
    print(nn3.random)











