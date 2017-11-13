from AlgorithmTraining.G55Utils.Py.Utils import ListNode, LinkedListUtil

class Solution(object):
    def reverseBetween(self, head, m, n):
        """
        :type head: ListNode
        :type m: int
        :type n: int
        :rtype: ListNode
        """
        stack = []
        i = 0
        tmp = head

        while i < n:
            stack.append(tmp)
            i += 1
            if m > 1 and i == m - 1:
                mPre = tmp
            elif i == n:
                nNext = tmp.next
            tmp = tmp.next

        i -= 1
        while i >= m:
            stack[i].next = stack[i-1]
            i -= 1
        stack[m - 1].next = nNext
        if m > 1:
            mPre.next = stack[n - 1]
        else:
            head = stack[n - 1]
        return head

if __name__ == "__main__":
    s = Solution()
    arr = [1, 2, 3, 4, 5, 6, 7]
    head = LinkedListUtil.genList(arr)
    reversedHead = s.reverseBetween(head, 1, 1)
    LinkedListUtil.showList(reversedHead, 20)


