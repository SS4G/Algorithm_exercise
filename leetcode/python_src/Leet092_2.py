from AlgorithmTraining.G55Utils.Py.Utils import ListNode, LinkedListUtil
class Solution(object):
    def reverseBetween(self, head, m, n):
        """
        :type head: ListNode
        :type m: int
        :type n: int
        :rtype: ListNode
        """
        # 使用循环一部分链表去做
        dummyHead = ListNode(-1)
        dummyHead.next = head
        st = 0
        tmp = dummyHead
        while st < m:
            if st == m - 1:
                m_1Node = tmp
            st += 1
            tmp = tmp.next
        insertPtr = st
        n0 = m_1Node
        n1 = tmp
        n2 = tmp
        n3 = tmp.next
        while insertPtr < n:
            n4 = n3.next
            n0.next = n3
            n3.next = n1
            n2.next = n4

            # update ptrs
            n1 = n3
            n3 = n4
            insertPtr += 1
        return dummyHead.next

if __name__ == "__main__":
    s = Solution()
    arr = [1, 2, 3, 4, 5, 6, 7]
    head = LinkedListUtil.genList(arr)
    reversedHead = s.reverseBetween(head, 1, 1)
    LinkedListUtil.showList(reversedHead, 20)






