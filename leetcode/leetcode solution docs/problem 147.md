## 147. Insertion Sort List Add to List

Sort a linked list using insertion sort
#### tips
排序链表 可以每次从头部开始检索 然后找到合适的位置插入
但是这样对于python会超时 测试用例中会有一个很长的升序排序的序列 就是为了卡 O(n)复杂度而设计的 所以python过不了
所以得把链表变为双向链表 然后插入排序 这样才能完成 接近O(n) 时间复杂度 以及O(1)空间  
插入链表要注意的是 要区分头部 尾部 等特殊的边界情况 指针来回转换比较复杂
如果不限制空间复杂度的情况下 可以考虑放到数组里面排序后 再转换为链表
#### mycode
```Python
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

class DoubleListNode(ListNode):
    def __init__(self, x):
        super(DoubleListNode,self).__init__(x)
        self.pre = None

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

    @staticmethod
    def singleLinkedListToDoubleLinkedList(head):
        """
        将单链表复制为双链表
        :param head: DoubleListNode
        :return:
        """
        newHead = None
        pre = None
        tmp = head
        while tmp is not None:
            if newHead is None:
                newHead = DoubleListNode(tmp.val)
                pre = newHead
            else:
                pre.next = DoubleListNode(tmp.val)
                pre.next.pre = pre
                pre = pre.next
            tmp = tmp.next
        return newHead

    @staticmethod
    def ReverseDoubleLinkedListToArr(head):
        tmp = head
        output = []
        while tmp.next is not None:
            tmp = tmp.next

        while tmp is not None:
            output.append(tmp.val)
            tmp = tmp.pre

        return output

    @staticmethod
    def showReverseDoubleLinkedListToArr(head):
        print("->".join([str(i) for i in LinkedListUtil.ReverseDoubleLinkedListToArr(head)]))

class Solution(object):
    def insertionSortList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """

        if head is None:
            return None
        elif head.next is None:
            return head
        else:
            head = LinkedListUtil.singleLinkedListToDoubleLinkedList(head)
            tmp = head
            while tmp is not None:
                head, tmp = self.insert(head, tmp)
        return head

    def insert(self, head, this):
        """
        :param head:
        :param this:
        :return:
        """
        insertPtr = this.pre
        while insertPtr is not None and insertPtr.val > this.val:
            # print("D")
            insertPtr = insertPtr.pre
        if insertPtr is None:  # insert to head
            p1 = head
            p2 = this.pre
            p3 = this.next
            if p2 is None:
                return head, this.next
            else:
                p2.next = p3
                if p3 is not None:
                    p3.pre = p2
                head = this
                this.next = p1
                this.pre = None
                p1.pre = this
                return head, p3
        elif insertPtr is this.pre:
            return head, this.next
        else:
            p2 = this.pre
            p3 = this.next
            p1 = insertPtr.next
            p0 = insertPtr
            this.next = p1
            p1.pre = this
            p0.next = this
            this.pre = p0
            p2.next = p3
            if p3 is not None:
                p3.pre = p2
            return head, p3

if __name__ == "__main__":
    s = Solution()
    arr0 = [0, 1, 2, 3, 4, 5, 6, ]
    arr0 = [0, 1, 2, 3, ]
    arr1 = [0, 2, 1, 3, 0, 7, -1, ]
    arr2 = [6, 5, 4, 3, 2, 1, 0, ]
    arr3 = [6, ]
    arr4 = [6, 7, 8, 9, 1, 2, 3]

    arr = arr2
    l0 = LinkedListUtil.genList(arr)
    l1 = s.insertionSortList(l0)
    arrRes = LinkedListUtil.link2Arr(l1)
    arr.sort()
    assert arrRes == arr, "WA: res = "+str(arrRes)+" should be "+str(arr)
```
