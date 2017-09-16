# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution(object):
    def oddEvenList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        oddHead = None
        evenHead = None
        eventmp = None
        oddtmp = None
        x = 0
        tmp = head
        while tmp is not None:
            if oddHead is None and (x & 0x01 == 0):
                oddHead = tmp
                oddtmp = oddHead
            elif evenHead is None and (x & 0x01 != 0):
                evenHead = tmp
                eventmp = evenHead
            elif x & 0x01 == 0:
                oddtmp.next = tmp
                oddtmp = oddtmp.next
            elif x & 0x01 == 1:
                eventmp.next = tmp
                eventmp = eventmp.next
            else:
                assert False, "shouldn't be here"
            tmp = tmp.next
            x += 1
        if oddtmp is not None:
            oddtmp.next = evenHead
        if eventmp is not None:
            eventmp.next = None
        return oddHead

    def showList(self, head):
        print("->".join([str(int0) for int0 in self.link2Arr(head)]))

    def genList(self, l):
        head = None
        tmp = head
        for val in l:
            if head is None:
                head = ListNode(val)
                tmp = head
            else:
                tmp.next = ListNode(val)
                tmp = tmp.next
        return head

    def link2Arr(self, head):
        output = []
        while head is not None:
            output.append(head.val)
            head = head.next
        # print(output)
        return output

if __name__ == "__main__":
    s = Solution()
    #a0 = [1, 2, 3, 4, 5, 6, 7]
    #a0 = [1, 2, ]
    a0 = [1, ]
    h = s.genList(a0)
    s.showList(h)
    s.showList(s.oddEvenList(h))