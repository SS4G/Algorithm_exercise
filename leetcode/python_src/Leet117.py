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
        if root is None:
            return None
        else:
            curLineStart = root
            nextLineStart = None
            while curLineStart is not None:
                lineRoot = curLineStart
                while lineRoot is not None:
                    buffer = []
                    #  将可能存在的下一个节点添加进队列中
                    if lineRoot.left is not None:
                        buffer.append(lineRoot.left)
                    if lineRoot.right is not None:
                        buffer.append(lineRoot.right)
                    if nextLineStart is None:  # 下一排的头部节点还没有构建
                        if len(buffer) == 2:
                            nextLineStart = buffer[0]
                            buffer[0].next = buffer[1]
                            nextLineAdd = buffer[1]
                        elif len(buffer) == 1:
                            nextLineStart = buffer[0]
                            nextLineAdd = buffer[0]
                    else:
                        if len(buffer) == 2:
                            nextLineAdd.next = buffer[0]
                            buffer[0].next = buffer[1]
                            nextLineAdd = buffer[1]
                        elif len(buffer) == 1:
                            nextLineAdd.next = buffer[0]
                            nextLineAdd = buffer[0]
                    lineRoot = lineRoot.next
                curLineStart = nextLineStart
                nextLineStart = None

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
    r1.right = None
    r2.left = None
    r2.right = r4
    s.connect(r0)
    print(r1.next.val)
    print(r3.next.val)
    print(r4.next)











