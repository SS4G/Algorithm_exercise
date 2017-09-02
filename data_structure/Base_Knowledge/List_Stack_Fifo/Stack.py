# coding=utf-8
# Author: SS4G
# Date 2017/03/08
# all test case passed


class ArrayStack:
    """
    数组实现的栈
    stack top 指向一个空的空间
    """
    def __init__(self, stack_size=10):
        self.stack_size = stack_size
        self.stack_space = [None, ]*stack_size
        # 栈状态变量
        self.current_space = stack_size
        self.stack_index = 0  # 指向当前空的位置

    def push(self, element):
        self.stack_space[self.stack_index] = element
        self.current_space -= 1
        self.stack_index += 1

    def pop(self):
        assert self.stack_index > 0, "ERROR: stack already empty"
        self.stack_index -= 1
        self.current_space += 1
        return self.stack_space[self.stack_index]

    def get_top(self):
        return self.stack_space[self.stack_index-1]

    def is_empty(self):
        return True if self.stack_index == 0 else False

    def is_full(self):
        return True if self.current_space == 0 else False

    def empty_stack(self):
        self.stack_index = 0
        self.current_space = self.stack_size


class StackNode:
    def __init__(self, val=None):
        self.pre = None
        self.val = val


class LinkListStack:
    """
    数组实现的栈
    stack top 指向一个空的空间
    """
    def __init__(self, stack_size=10):
        self.stack_size = stack_size
        self.stack_ptr = StackNode()  # 先创建一个空节点
        # 栈状态变量
        self.current_space = stack_size
        self.stack_index = 0  # 指向当前空的位置

    def push(self, element):
        self.stack_ptr.val = element
        pre = self.stack_ptr
        self.stack_ptr = StackNode()  # create an empty Node
        self.stack_ptr.pre = pre

        self.current_space -= 1
        self.stack_index += 1

    def pop(self):
        assert self.stack_index > 0, "ERROR: stack already empty"
        self.stack_index -= 1
        self.current_space += 1

        pop_val = self.stack_ptr.pre.val
        self.stack_ptr = self.stack_ptr.pre
        return pop_val

    def is_empty(self):
        return True if self.stack_index == 0 else False

    def is_full(self):
        return True if self.current_space == 0 else False

    def empty_stack(self):
        self.stack_index = 0
        self.current_space = self.stack_size
        self.stack_ptr = StackNode()

# Test
if __name__ == "__main__":
    stack0 = ArrayStack(5)
    stack1 = LinkListStack(5)
    testcase = [
        ("push", 1),
        ("pop", 4),
        ("push", 1),
        ("pop", 1),  # empty
        ("push", 1),
        ("push", 2),
        ("push", 3),
        ("push", 5),
        ("pop", 5),
        ("pop", 5),
        ("pop", 5),
        ("pop", 5),  # empty
        ("push", 100),
        ("push", 100),
        ("push", 100),
        ("push", 100),
        ("push", 100),  # full

    ]
    op_time = 0
    for op in testcase:
        if op[0] == "push":
            stack0.push(op[1])
        else:
            print("stack pop :", stack0.pop())
        op_time += 1
        print("at op time empty ?", stack0.is_empty())
        print("at op time full ?", stack0.is_full())

    print("\n-----------------------------")
    op_time = 0
    for op in testcase:
        if op[0] == "push":
            stack1.push(op[1])
        else:
            print("stack pop :", stack1.pop())
        op_time += 1
        print("at op time empty ?", stack1.is_empty())
        print("at op time full ?", stack1.is_full())
