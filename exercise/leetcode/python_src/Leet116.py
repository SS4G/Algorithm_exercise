# Definition for binary tree with next pointer.
class TreeLinkNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None
        self.next = None


class Solution:
    # @param root, a tree link node
    # @return nothing
    def connect(self, root):
        self.preOrderTraverse(root, None, False)

    def preOrderTraverse(self, root, lastRoot, isLeft):
        if root is None:
            return
        self.linkToRight(root, lastRoot, isLeft)
        self.preOrderTraverse(root.left, root, True)
        self.preOrderTraverse(root.right, root, False)

    def linkToRight(self, root, lastRoot, isLeft):
        if root is None:
            return
        else:
            if lastRoot is not None:  # non-root node
                if isLeft:
                    root.next = lastRoot.right
                else:
                    if lastRoot.next is not None:
                        root.next = lastRoot.next.left

if __name__ == "__main__":
    s = Solution()
    r0 = TreeLinkNode(0)
    r1 = TreeLinkNode(1)
    r2 = TreeLinkNode(2)
    r3 = TreeLinkNode(3)
    r4 = TreeLinkNode(4)
    r5 = TreeLinkNode(5)
    r6 = TreeLinkNode(6)
    r0.left = r1
    r0.right = r2
    r1.left = r3
    r1.right = r4
    r2.left = r5
    r2.right = r6
    s.connect(r0)
    print(r1.next.val)
    print(r3.next.val)
    print(r4.next.val)
    print(r5.next.val)
    print(r6.next)





