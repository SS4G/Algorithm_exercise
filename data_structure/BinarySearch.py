# coding=utf-8
class BinarySearch:
    """
    标准二分查找模板
    """
    def __init__(self):
        pass

    def binarysearch(self, target_list, target_element):
        """
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

# Test
if __name__ == "__main__":
    testcase0 = [1, 2, 3, 4, 5, 6, 7, 8, ]
    testcase1 = ["a", "b", "c", "d", "e", "f", "g", "h", ]
    testcase2 = ["a", ]
    testcase3 = ["a", "b"]
    t = BinarySearch()
    assert t.binarysearch(testcase0, 6) == 5, "return index ERROR"
    assert t.binarysearch(testcase1, "e") == 4, "return index ERROR"
    assert t.binarysearch(testcase2, "a") == 0, "return index ERROR"
    assert t.binarysearch(testcase3, "b") == 1, "return index ERROR"
    assert t.binarysearch(testcase0, 100) == -1, "return index ERROR"


