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
    def sortList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if head is None:  # 0node
            return None
        elif head.next is None:  # 1node
            return head
        elif head.next.next is None:  # 2node
            tmp0 = head
            tmp1 = head.next
            if tmp1.val < tmp0.val:
                newHead = tmp1
                tmp1.next = tmp0
                tmp0.next = None
            else:
                newHead = tmp0
            return newHead
        else:  # at least 3node
            mid = self.getListMid(head)
            tmp = mid.next
            mid.next = None
            #LinkedListUtil.showList(head)
            #LinkedListUtil.showList(tmp)
            h1 = self.sortList(head)
            h2 = self.sortList(tmp)
            return self.merge2list(h1, h2)

    def getListMid(self, head):
        """
        使用快慢指针来获取链表的中点
        :param head:
        :return:
        """
        if head is None:  # 0node
            return None
        elif head.next is None:  # 1node
            return head
        elif head.next.next is None:  # 2node
            return head
        else:  # 3node
            slow = head.next
            fast = head.next.next
            while fast is not None:
                fn = fast.next
                if fn is None or fn.next is None:
                    break
                fast = fn.next
                slow = slow.next
            midPtr = slow
            return midPtr

    def merge2list(self, l1, l2):
        """
        将两个已排序的链表合并
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

if __name__ == "__main__":
    s = Solution()
    t1 = [1, 2, 3, 4, 5, 5, 4, 3, 2, 1]
    t2 = [1, 2, 3, 4]
    t3 = [1, 2, 3]
    t4 = [1, 2, ]
    t5 = [1, ]

    t1c = LinkedListUtil.genList(t1)
    t2c = LinkedListUtil.genList(t2)
    t3c = LinkedListUtil.genList(t3)
    t4c = LinkedListUtil.genList(t4)
    t5c = LinkedListUtil.genList(t5)
    """
    print(s.getListMid(t1c).val)
    print(s.getListMid(t2c).val)
    print(s.getListMid(t3c).val)
    print(s.getListMid(t4c).val)
    print(s.getListMid(t5c).val)
    """

    LinkedListUtil.showList(t1c)
    res = s.sortList(t1c)
    t1.sort()
    LinkedListUtil.showList(res)
    #assert t1 == LinkedListUtil.link2Arr(res), "WA"


