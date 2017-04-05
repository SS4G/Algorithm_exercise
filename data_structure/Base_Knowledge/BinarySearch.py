# coding=utf-8
class BinarySearch:
    """
    标准二分查找模板
    """
    def __init__(self):
        pass

    def binarysearch0(self, target_list, target_element):
        """
        较为复杂的一种实现
        Complexity:O(log(n))
        :param target_list: the list which we search in
               the list must be sorted in ascending order
        :param target_element: the element we are about to find
        :return: index of target element
                return -1 if target element not found
        """
        list_length = len(target_list)  # O(1) operation in python realization
        left = 0
        right = list_length-1
        while right-left > 1:
            if target_list[right] == target_element:  # or use equals method
                return right
            elif target_list[left] == target_element:  # or use equals method
                return left
            else:
                middle = (left+right) >> 1  # middle=(left+right)/2
                if target_element > target_list[middle]:
                    left = middle
                else:
                    right = middle
        if target_list[right] == target_element:
            return right
        elif target_list[left] == target_element:
            return left
        else:
            return -1

    def binarysearch1(self, target_list, target_element):
        """
        较为简单的一种实现 以后以这种二分搜索为标准
        这种看起来有点奇怪但是包含了 超照的元素在 left 或者
        在right的多种特殊的边界情形
        关键点： 如果存在要查找的元素那么 左右边界之间的区间必然包含这个元素
        每次迭代区间必然会缩小 和夹逼定理类似
        由数学归纳法得出 最后 left-right 总会缩小到 2 或者1  这两种基准情形
        这两种基准情形在下面的代码中都可以被处理 所以最终的结果是正确的
        Complexity:O(log(n))
        :param target_list: the list which we search in
               the list must be sorted in ascending order
        :param target_element: the element we are about to find
        :return: index of target element
                return -1 if target element not found
        """
        list_length = len(target_list)  # O(1) operation in python realization
        left = 0
        right = list_length - 1
        while left<=right:
            middle = (left + right) >> 1  # middle=(left+right)/2
            if target_list[middle]==target_element:
                return middle
            if target_element > target_list[middle]:
                left = middle+1
            else:
                right = middle-1
        return -1

# Test
if __name__ == "__main__":
    testcase0 = [1, 2, 3, 4, 5, 6, 7, 8, ]
    testcase1 = ["a", "b", "c", "d", "e", "f", "g", "h", ]
    testcase2 = ["a", ]
    testcase3 = ["a", "b"]
    t = BinarySearch()
    assert t.binarysearch0(testcase0, 6) == 5, "return index ERROR"
    assert t.binarysearch0(testcase1, "e") == 4, "return index ERROR"
    assert t.binarysearch0(testcase2, "a") == 0, "return index ERROR"
    assert t.binarysearch0(testcase3, "b") == 1, "return index ERROR"
    assert t.binarysearch0(testcase0, 100) == -1, "return index ERROR"

    assert t.binarysearch1(testcase0, 6) == 5, "return index ERROR"
    assert t.binarysearch1(testcase1, "e") == 4, "return index ERROR"
    assert t.binarysearch1(testcase2, "a") == 0, "return index ERROR"
    assert t.binarysearch1(testcase3, "b") == 1, "return index ERROR"
    assert t.binarysearch1(testcase0, 100) == -1, "return index ERROR"
    print("all testcase passed")
