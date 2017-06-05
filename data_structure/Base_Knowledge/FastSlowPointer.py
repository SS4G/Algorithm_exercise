class FastSlowPointer:
    """
    快慢指针可以用来查找链表是否有环 以及获取链表的中点
    """
    @staticmethod
    def getListMid(head):
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

