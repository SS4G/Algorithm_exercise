# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None


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


# gcd
class MathUtils:
    @staticmethod
    def gcd0(x, y):
        """
        辗转相除法求最大公约数
        :param x:
        :param y:
        :return:
        """
        if x < y:
            x, y = y, x
        while x % y != 0:
            x, y = y, x % y
        return y

    @staticmethod
    def gcd1(x, y):
        """
        更相减损术求最大公约数
        :param x:
        :param y:
        :return:
        """
        if x < y:
            x, y = y, x
        while x - y != y:
            if x - y > y:
                x, y = x - y, y
            else:
                x, y = y, x - y
        return y


# binary Tree
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

# build and show Tree
class TreeUtil:
    @staticmethod
    def serialize(root):
        """Encodes a tree to a single string.

        :type root: TreeNode
        :rtype: str
        """

        if root is None:
            return ["null", ]
        stack = []
        output = []
        stack.append(root)
        output.append(root.val)
        serializedPtr = 0
        while serializedPtr < len(stack):
            if stack[serializedPtr].left is not None:
                stack.append(stack[serializedPtr].left)
                output.append(stack[serializedPtr].left.val)
            else:
                output.append(None)

            if stack[serializedPtr].right is not None:
                stack.append(stack[serializedPtr].right)
                output.append(stack[serializedPtr].right.val)
            else:
                output.append(None)
            serializedPtr += 1

        return output

    @staticmethod
    def deserialize(data):
        """Decodes your encoded data to tree.

        :type data: str
        :rtype: TreeNode
        """
        if len(data) == 0:
            return None
        datas = [i if i is not None else None for i in data]

        construct = []
        currentPtr = None
        LeftFlag = True
        for i in range(len(datas)):
            if i == 0:
                if datas[i] is None:
                    return None
                else:
                    construct.append(TreeNode(datas[i]))
                    currentPtr = 0
                    leftFlag = True
            else:
                if datas[i] is not None:
                    if leftFlag:
                        construct[currentPtr].left = TreeNode(datas[i])
                        construct.append(construct[currentPtr].left)
                    else:
                        construct[currentPtr].right = TreeNode(datas[i])
                        construct.append(construct[currentPtr].right)
                leftFlag = not leftFlag
                if leftFlag is True:
                    currentPtr += 1
        return construct[0]

    @staticmethod
    def showTree(root, layer):
        if root is None:
            # print(" "*layer+"*")
            return
        print("...."*layer+str(root.val))
        TreeUtil.showTree(root.left, layer+1)
        TreeUtil.showTree(root.right, layer+1)

if __name__ == "__main__":
    a0 = [1, 4, 5, 7, 8]
    a1 = [2, 4, 6, 8, 10]
    l0 = LinkedListUtil.genList(a0)
    l1 = LinkedListUtil.genList(a1)
    LinkedListUtil.showList(l0)
    LinkedListUtil.showList(l1)
    l2 = LinkedListUtil.merge2Ascendinglist(l0, l1)
    LinkedListUtil.showList(l2)