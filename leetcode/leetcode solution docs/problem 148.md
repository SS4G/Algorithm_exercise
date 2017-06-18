## 148. Sort List Add to List
Sort a linked list in O(n log n) time using constant space complexity.
#### tips
因该使用快慢指针来去顶一个链表的中点 只需要常规方法时间的 1/3
然后使用合并俩表的方法来完成 归并排序
#### mycode
```Python
# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution(object):
    def sortList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if head is None:
            return None
        wholeSize = self.size(head)
        listHeads = []
        i = 0
        tmp = head
        while i < wholeSize:
            if i % 4 == 0:  # %4 == 0
                listHeads.append(tmp)
                tmp = tmp.next
            elif i % 4 == 3:  # %4 == 3
                n = tmp.next
                tmp.next = None
                tmp = n
            else:
                tmp = tmp.next
            i += 1
        for l in listHeads:
            self.showList(l)
        tmpList = []
        for l0 in listHeads:
            a = self.link2Arr(l0)
            a.sort()  # sort size < 4
            tmpList.append(self.genList(a))

        listHeads = tmpList
        while len(listHeads) > 1:
            newlistHeads = []
            i = 0
            while i < len(listHeads):
                if (i == len(listHeads)-1) and (i & 0x01 == 0):
                    newlistHeads.append(listHeads[i])
                else:
                    newlistHeads.append(self.merge2list(listHeads[i], listHeads[i + 1]))
                i += 2
            listHeads = newlistHeads
        return listHeads[0]

    def merge2list(self, l1, l2):
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

    def size(self, head):
        cnt = 0
        while head is not None:
            head = head.next
            cnt += 1
        return cnt

    def toPosition(self, head, pos):
        while pos > 0:
            head = head.next
            pos -= 1
        return head

    def link2Arr(self, head):
        output = []
        while head is not None:
            output.append(head.val)
            head = head.next
        # print(output)
        return output

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

if __name__ == "__main__":
    s = Solution()
    t1 = [1, 10, 3, 5, 6, 7, 9, 10]
    t1c = s.genList(t1)
    res = s.sortList(t1c)
    t1.sort()
    s.showList(res)
    assert t1 == s.link2Arr(res), "WA"
```
