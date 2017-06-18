## 138. Copy List with Random Pointer

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

#### tips
注意是deepcopy 所以要注意不要把random指针copy到原来的列表中
可以用字典来记录对应random指针所指的对象在原列表中的位置

#### mycode

```
class RandomListNode(object):
    def __init__(self, x):
        self.label = x
        self.next = None
        self.random = None


class Solution(object):
    def copyRandomList(self, head):
        """
        :type head: RandomListNode
        :rtype: RandomListNode
        """
        dict0 = {}
        if head is None:
            return None
        else:
            tmp = head
            i = 0
            while tmp is not None:
                dict0[tmp] = i
                i += 1
                tmp = tmp.next
            size = i
            dummy = RandomListNode(0xffffffff)

            tmp0 = head
            tmp1 = dummy
            nodes = []
            while tmp0 is not None:
                tmp1.next = RandomListNode(tmp0.label)
                tmp1 = tmp1.next
                nodes.append(tmp1)
                tmp0 = tmp0.next
            i = 0
            tmp1 = dummy.next
            tmp0 = head
            while i < size:
                if tmp0.random is not None:
                    tmp1.random = nodes[dict0[tmp0.random]]
                else:
                    tmp1.random = None
                i += 1
                tmp1 = tmp1.next
                tmp0 = tmp0.next
            return dummy.next
```
