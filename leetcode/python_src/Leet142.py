# Definition for singly-linked list.
# Definition for singly-linked list.


class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None


class LinkedListUtil:
    @staticmethod
    def merge2Ascendinglist(l1, l2):
        """
        合并两个升序的链表
        合并后仍然是一个升序的链表
        :param l1:
        :param l2:
        :return:
        """
        t1 = l1
        t2 = l2
        newHead = None
        tmp = None
        while (t1 is not None) and (t2 is not None):
            if newHead is None:
                if t1.val < t2.val:
                    newHead = t1
                    t1 = t1.next
                else:
                    newHead = t2
                    t2 = t2.next
                tmp = newHead
            else:
                if t1.val < t2.val:
                    tmp.next = t1
                    t1 = t1.next
                else:
                    tmp.next = t2
                    t2 = t2.next
                tmp = tmp.next
        if t1 is not None:
            tmp.next = t1
        if t2 is not None:
            tmp.next = t2
        return newHead

    @staticmethod
    def size(head):
        cnt = 0
        while head is not None:
            head = head.next
            cnt += 1
        return cnt

    @staticmethod
    def toPosition(head, pos):
        while pos > 0:
            head = head.next
            pos -= 1
        return head

    @staticmethod
    def link2Arr(head):
        output = []
        while head is not None:
            output.append(head.val)
            head = head.next
        # print(output)
        return output

    @staticmethod
    def showList(head):
        print("->".join([str(int0) for int0 in LinkedListUtil.link2Arr(head)]))

    @staticmethod
    def genList(l):
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


class Solution(object):
    def detectCycle(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if head is None:
            return None
        try:
            fast = head.next.next
            slow = head.next
        except Exception:
            return None

        while fast is not slow:
            if (fast is not None) and (fast.next is not None):
                fast = fast.next.next
                slow = slow.next
            else:
                return None

        # must has cycle
        fast = head
        while fast is not slow:
            fast = fast.next
            slow = slow.next
        return slow

if __name__ == "__main__":
    s = Solution()
    head0 = ListNode(0)
    head0.next = head0

    head1 = LinkedListUtil.genList([1, 2, 3])
    head2 = LinkedListUtil.genList([1, 2, 3, 4])
    head3 = LinkedListUtil.genList([1, 2, 3, 4, 5])
    print(s.detectCycle(head0))
    print(s.detectCycle(head1))
    print(s.detectCycle(head2))
    print(s.detectCycle(head3))

    LinkedListUtil.toPosition(head3, 4).next = head2
    LinkedListUtil.toPosition(head2, 3).next = LinkedListUtil.toPosition(head3, 2)
    print(s.detectCycle(head3).val)