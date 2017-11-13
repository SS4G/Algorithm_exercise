## 21. Merge Two Sorted Lists
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

#### tips
合并两个有序链表最重要的是要注意边界情况 比如一个链表为空
所以为了避免过多的代码，可以在结果链表中使用头结点，这样对边界的处理就和一般的情况一样了

#### mycode

```
class Leet021 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyRes = new ListNode(3);
        ListNode ptr1 = l1; //dummy1;
        ListNode ptr2 = l2; //dummy2;
        ListNode ptrRes = dummyRes;
        while (ptr1 != null && ptr2 != null) {
            if (ptr1.val > ptr2.val) {
                ptrRes.next = ptr2;
                ptr2 = ptr2.next;
            }
            else {
                ptrRes.next = ptr1;
                ptr1 = ptr1.next;
            }
            ptrRes = ptrRes.next;
        }

        while (ptr1 != null) {
            ptrRes.next = ptr1;
            ptr1 = ptr1.next;
            ptrRes = ptrRes.next;
        }

        while (ptr2 != null) {
            ptrRes.next = ptr2;
            ptr2 = ptr2.next;
            ptrRes = ptrRes.next;
        }
        return dummyRes.next;
    }
}
```
