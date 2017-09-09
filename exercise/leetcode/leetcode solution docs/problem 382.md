## 382. Linked List Random Node

Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.

Follow up:
- What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?

Example:

```
// Init a singly linked list [1,2,3].
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
Solution solution = new Solution(head);

// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
solution.getRandom();
```

#### tips
这个题目的 follow up要求 应该 使用水库抽样算法

本题保持水库的的大小为1 
即 对于新来的第i+1个元素 以概率1/i+1 将他放入水库 以 i/i+1不把他放入
每次返回水库中的值 

需要注意的是 水库抽样算法对于第一开始的 序列整个序列而言 最终返回的元素的概率是相同的 而不是每次放入水库的概率相同 即最后留在水库中的元素的概率 对于序列中所有元素是相同的这视为一次抽取

但是题目要求抽取n次 所以 要每次都从头开始 

#### mycode

import random
```
class Solution(object):
    def __init__(self, head):
        """
        @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node.
        :type head: ListNode
        """
        self.head = head
        self.cnt = 0
        self.pre = None

    def getRandom(self):
        """
        Returns a random node's value.
        :rtype: int
        """
        tmp = self.head
        cnt = 0
        r = None
        while tmp is not None:
            rand = random.randint(0, cnt)
            if rand == 0:
                r = tmp.val
            cnt += 1
            tmp = tmp.next
        return r
```
