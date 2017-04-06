# coding=utf-8
# Author: SS4G
# Date 2017/03/09
# Status:finished

import random


class SortFunc:
    """
    only for non-negative numbers
    """
    def __init__(self):
        self.MIN_INT = -(1 << 32)
        self.MAX_INT = ((1 << 32)-1)

    def my_min(self, a, begin=None, end=None):
        """
        :param a: 要排序的列表
        :param begin: 排序范围的起始下标（包含）
        :param end:   排序范围的终止下标（不含）
               若 begin 或者 end 是None 则默认范围为全部
        :return: a[begin:end] 的升序排序
        """
        val = self.MAX_INT
        if (begin is None) or (end is None):
            begin = 0
            end = len(a)
        min_index = -1
        for i in range(begin, end):
            if val > a[i]:
                val = a[i]
                min_index = i
        return (val, min_index)

    def my_max(self, a, begin=None, end=None):
        """
        :param a: 要排序的列表
        :param begin: 排序范围的起始下标（包含）
        :param end:   排序范围的终止下标（不含）
               若 begin 或者 end 是None 则默认范围为全部
        :return: a[begin:end] 的升序排序
        """
        val = self.MAX_INT
        if (begin is None) or (end is None):
            begin = 0
            end = len(a)

        max_index = -1
        for i in range(begin, end):
            if val < a[i]:
                val = a[i]
                max_index = i
        return (val, max_index)

    def get_bit(self, val, bit=1, radix=10):
        for i in range(bit-1):
            val //= radix
        return val % radix

    def merge_sorted_array(self, arr0, arr1):
        """
        合并两个已经 排序的数列
        :param arr0:
        :param arr1:
        :return:
        """
        new_list = [None, ]*(len(arr0)+len(arr1))
        i0 = 0
        i1 = 0
        j = 0
        while i0 < len(arr0) and i1 < len(arr1):
            if arr0[i0] <= arr1[i1]:  # 加等号以保证排序的稳定性
                new_list[j] = arr0[i0]
                i0 += 1
            else:
                new_list[j] = arr1[i1]
                i1 += 1
            j += 1

        while i0 < len(arr0):
            new_list[j] = arr0[i0]
            i0 += 1
            j += 1

        while i1 < len(arr1):
            new_list[j] = arr1[i1]
            i1 += 1
            j += 1

        return new_list

    def radix_sorted(self, array, radix=10):
        """
        基数排序
        :param array:
        :return:
        """
        length = len(array)
        a = []
        a[:] = array[:]
        buckets = [[] for i in range(radix)]
        if length == 0:  # handle empty sequence
            return []
        max_val = max(a)
        min_val = min(a)
        bits = 1
        n = radix**bits
        while max_val >= n:  # 求得序列中最大值有多少位 10 进制 也可以选取其他进制 此处使用10进制举例
            n = radix**bits
            bits += 1
        for i in range(1, bits + 2):
            new_buckets = [[] for i in range(radix)]  # 创建一组新的桶
            if i > 1:
                for bucket in buckets:  # 注意 这里虽然有两个循环但复杂度不是O(N^2)
                    for val in bucket:
                        new_buckets[self.get_bit(val, bit=i, radix=radix)].append(val)
            else:
                for val in a:
                    new_buckets[self.get_bit(val, bit=i, radix=radix)].append(val)
            buckets = new_buckets
        return buckets[0]

    def insert_sorted(self, array):
        """
        插入排序
        :param array:
        :return:
        """
        length = len(array)
        a = [None, ]*length
        a[:] = array[:]
        if length >= 2:
            for i in range(length):
                key = a[i]
                j = i-1
                while a[j] > key and j >= 0:
                    a[j+1] = a[j]
                    j -= 1
                if j < 0:
                    a[0] = key
                else:
                    a[j+1] = key
        return a

    def bubble_sorted(self, array):
        """
        冒泡排序
        :param array:
        :return:
        """
        length = len(array)
        a = [None, ]*length
        a[:] = array[:]
        for i in range(length):
            for j in range(i, length):
                if a[j] < a[i]:  # stable operation
                    a[j], a[i] = a[i], a[j]  # exchange
        return a

    def select_sorted(self, array):
        """
        选择排序
        :param array:
        :return:
        """
        length = len(array)
        a = [None, ]*length
        a[:] = array[:]
        for i in range(length):
            (val, index) = self.my_min(a, i, length)
            a[i], a[index] = a[index], a[i]
        return a

    def merge_sorted(self, array):
        """
        归并排序
        :param array:
        :return:
        """
        length = len(array)
        a = [None, ]*length
        a[:] = array[:]
        if length == 0:
            return []
        elif length == 1:
            return a
        else:
            sorted_0 = self.merge_sorted(array[:length >> 1])
            sorted_1 = self.merge_sorted(array[length >> 1:])
            return self.merge_sorted_array(sorted_0, sorted_1)

    def quick_sort_inplace(self, arr, start, end):
        """
        原地快速排序
        :param arr: 要排序的序列
        :param start: 起始下标 包含这个下标
        :param end: 结束下标 不包含这个下标
        :return:
        """
        key_index = start
        small_index = start
        large_index = end-1
        compare_small_flag = True
        if end - start == 2:  # 数组长度为2时直接排序返回数组
            max_val = max(arr[start:end])
            min_val = min(arr[start:end])
            arr[start] = min_val
            arr[end-1] = max_val
        elif end - start == 1:
            pass
        elif start >= end:
            pass
        else:
            key_val = arr[small_index]

            # 以下是选定arr[start] 为基准 然后将比他大或者比他小的数分别一道他两边的逻辑
            # 交替的查找最大和最小的逻辑
            # 使用一个临时变量将基准保存起来 以空出一个位置可以腾挪其他元素
            while small_index < large_index:  # 将大于key的值全部放在key右边 将小于key的
                while small_index < large_index and arr[large_index] >= key_val:
                    large_index -= 1
                arr[small_index] = arr[large_index]

                while small_index < large_index and arr[small_index] < key_val:
                    small_index += 1
                arr[large_index] = arr[small_index]

            arr[small_index] = key_val
            self.quick_sort_inplace(arr, start, small_index)
            self.quick_sort_inplace(arr, small_index+1, end)

    def quick_sorted(self, array):
        a = []
        a[:] = array[:]
        length = len(array)
        if length <= 1:
            return a
        else:
            self.quick_sort_inplace(a, 0, length)
            return a

    def insert_by_step(self, array, step):
        """
        以对应的间隔进行插入排序 是希尔排序的基本单元
        :param array:
        :param step:
        :return:
        """
        arr_len = len(array)
        for offset in range(step):
            i = 0
            key_index = offset  # offset 是相对于起点的偏移量
            while key_index < arr_len:
                key_val = array[key_index]
                j = key_index - step
                while j >= 0:
                    if key_val < array[j]:
                        array[j+step] = array[j]
                        j -= step
                    else:
                        array[j+step] = key_val
                        break
                array[j+step] = key_val
                key_index += step

    def shell_sorted(self, array):
        a = []
        a[:] = array[:]
        length = len(array)
        gap = length >> 1
        while gap > 0:
            self.insert_by_step(a, gap)
            gap >>= 1  # 希尔排序的 间隔按照 除以二缩小直到间隔变为1 也就是进行最简单的插入排序
            # 此时的插入排序需要移动的次数已经很少了
        return a

    # below is heap sort func
    def adjust_heap(self, heap, top_index, heap_len):
        """
        调整为小顶堆
        :param heap: 堆的数组
        :param top_index: 当前操作的子堆的堆顶
        :return:
        """
        l_child = top_index*2+1
        r_child = top_index*2+2
        if heap_len == 0:
            return None
        if l_child >= heap_len:  # 表明当前的节点是叶子节点
            return None
        elif r_child >= heap_len:  # 表明当前节点只有左孩子
            if heap[l_child] < heap[top_index]:
                heap[l_child], heap[top_index] = heap[top_index], heap[l_child]
            return None

        smaller_index = l_child if heap[l_child] < heap[r_child] else r_child  # 获取左右孩子的较大节点
        if heap[top_index] > heap[smaller_index]:
            heap[top_index], heap[smaller_index] = heap[smaller_index], heap[top_index]
            self.adjust_heap(heap, smaller_index, heap_len)  # 递归的调整小顶堆

    def heap_sorted(self, array):
        a = []
        a[:] = array[:]
        length = len(array)
        res = [None, ]*length
        for i in [length-1-j for j in range(length)]:  # 堆化数组 需要从叶子节点开始由下至上进行堆化
            self.adjust_heap(a, i, len(a))
        for i in range(length):  # 从堆顶删除元素
            res[i] = a[0]
            a[0] = a[length-1]
            length -= 1
            self.adjust_heap(a, 0, length)
        return res


# Test code
def generate_testcase():
    testcase_amount = 10000  # 总的测试用例的数目
    testcase_len_MAX = 100  # 总的测试用例的长度
    testcase_MAX_VAL = 255  # 单个测试数据的最大值 所有测试数据 的值都在 [0,test_case_MAX_VAL] 之间
    testcase_len = []  # 装载测试用例长度信息的列表  一个元素对应一个长度值
    test_cases = []
    # 特殊测试用例的添加
    test_cases.append([1, 3, 2, 4])
    test_cases.append([10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 0, 0, 0])
    test_cases.append([1, ])
    test_cases.append([testcase_MAX_VAL, ])
    test_cases.append([x for x in range(testcase_len_MAX)])  # 全部正向排序好的用例
    reversed_case = [x for x in range(testcase_len_MAX)]
    reversed_case.reverse()
    test_cases.append(reversed_case)  # 全部反向排序好的用例

    for i in range(testcase_amount):
        testcase_len.append(random.randint(0, testcase_len_MAX))

    for length in testcase_len:
        case = []
        for j in range(length):
            case.append(random.randint(0, testcase_MAX_VAL))  # 每个测试用例的范围在[0,1000000]
        test_cases.append(case)

    f = open("SortFunc_testcase.txt", "w", encoding="utf-8")
    for case in test_cases:
        case0 = [str(num) for num in case]
        case0.append("\n")
        f.write("\t".join(case0))
    f.close()


def load_testcase():
    test_set = []
    std_set = []
    f = open("SortFunc_testcase.txt", "r", encoding="utf-8")

    for line in f:
        case_str = line.strip()
        std_case = []
        if len(case_str) > 1:
            one_case = [int(part) for part in case_str.split("\t")]
            test_set.append(one_case)

            std_case[:] = one_case[:]
            std_case.sort()
            std_set.append(std_case)
        else:
            test_set.append([])
            std_set.append([])
    f.close()
    return test_set, std_set


def run_test(test_fun, test_set, std_set):
    length = len(test_set)
    for i in range(length):
        result = test_fun(test_set[i])
        assert result == std_set[i], \
            "result error while running:"+test_fun.__name__ + \
            "\ncase      :"+str(test_set[i]) + \
            "\nstd result:"+str(std_set[i]) + \
            "\nmy  result:"+str(result)

if __name__ == "__main__":

    # operation = "generate testcase"
    operation = "run test"
    if operation == "generate testcase":
        generate_testcase()
    elif operation == "run test":
        test_set, std_set = load_testcase()
        fun_list = []
        s = SortFunc()

        fun_list.append(s.insert_sorted)  # pass
        fun_list.append(s.bubble_sorted)  # pass
        fun_list.append(s.select_sorted)  # pass
        fun_list.append(s.merge_sorted)  # pass
        fun_list.append(s.quick_sorted)  # pass
        fun_list.append(s.shell_sorted)  # pass
        fun_list.append(s.radix_sorted)  # pass
        fun_list.append(s.heap_sorted)  # pass

        for fun in fun_list:
            run_test(fun, test_set, std_set)
            print(fun.__name__+" all testcase pass")
    print("process terminate successfully!")
