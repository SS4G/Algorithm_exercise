## 109. Convert Sorted List to Binary Search Tree

Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

#### tips
使用递归的方式来完成这个题目， 可以先把链表转化成数组 然后使用二分取中点的方法来递归的完成。
也可以使用 中序遍历的方法 每当应该生成中间的节点的时候就从链表中读取一个节点 生成这个中间的节点即可

#### mycode
```
class Solution(object):
    def sortedListToBST(self, head):
        """
        :type head: ListNode
        :rtype: TreeNode
        """
        if head is None:
            return None

        storage = []
        tmp = head
        while tmp is not None:
            storage.append(tmp.val)
            tmp = tmp.next
        return self.recursiveBuild(storage, 0, len(storage))

    def recursiveBuild(self, arr, lo, hi):
        if hi - lo == 0:
            #  print("A")
            return None
        elif hi - lo == 1:
            #  print("B")
            return TreeNode(arr[lo])
        elif hi - lo == 2:
            #  print("C")
            root = TreeNode(arr[hi - 1])
            leaf = TreeNode(arr[lo])
            root.left = leaf
            return root
        elif hi - lo == 3:
            #  print("D")
            root = TreeNode(arr[hi - 2])
            leaf0 = TreeNode(arr[lo])
            leaf1 = TreeNode(arr[hi - 1])
            root.left = leaf0
            root.right = leaf1
            return root
        else:
            # print("E")
            mid = (hi + lo) >> 1
            root = TreeNode(arr[mid])
            root.left = self.recursiveBuild(arr, lo, mid)
            root.right = self.recursiveBuild(arr, mid + 1, hi)
            return root
```
