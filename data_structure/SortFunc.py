# coding=utf-8
# Author: SS4G
# Date 2017/03/09
# Status:constructing


class SortFunc:
    """
    only for non-negative numbers
    """
    def __init__(self):
        self.MIN_INT = -(1 << 32)
        self.MAX_INT = ((1 << 32)-1)

    def my_sorted(self, a, key=None, reverse=False, sort_type="bubble"):
        pass

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
        new_list = [None,]*(len(arr0)+len(arr1))
        i0 = 0
        i1 = 0
        j = 0
        while i0 < len(arr0) and i1 < len(arr1):
            if arr0[i0] <= arr1[i1]:  # 加等号以保证排序的稳定性
                new_list[j] = arr0[i0]
                i0 += 1
            else:
                new_list[j] = arr0[i1]
                i1 += 1
            j+=1

        while i0 < len(arr0):
            new_list[j] = arr0[i0]
            i0 += 1
            j += 1

        while i1 < len(arr0):
            new_list[j] = arr0[i1]
            i1 += 1
            j += 1

        return new_list

    def radix_sorted(self, array):
        """
        基数排序
        :param array:
        :return:
        """
        length = len(array)
        a = []
        a[:] = array[:]
        buckets = [[] for i in range(10)]
        max_val = max(a)
        min_val = min(a)
        bits = 1
        n = 10**bits
        while max_val < n:
            n = 10**bits
            bits += 1
        for num in a:
            bit_val = self.get_bit(num, bit=1)
            buckets[bit_val].append(num)
        if bits >= 2:
            for i in range(2, bits + 1):
                new_buckets = [[] for i in range(10)]
                for bucket in buckets:  # 注意 这里虽然有两个循环但复杂度不是O(N^2)
                    for val in bucket:
                        new_buckets[self.get_bit(val, bit=i)].append(val)
                buckets = new_buckets
        return a

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

    def selection_sorted(self, array):
        """
        选择排序
        :param array:
        :return:
        """
        length = len(array)
        a = [None, ]*length
        a[:] = array[:]
        for i in range(length):
            (val, index) = self.my_min(a, i+1, length)
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
        if end - start == 2:
            max_val = max(arr[start:end])
            min_val = min(arr[start:end])
            arr[start] = max_val
            arr[end-1] = min_val
        elif end - start == 1:
            pass
        elif start == end:
            pass
        else:
            while small_index < large_index:  # 将大于key的值全部放在key右边 将小于key的
                if compare_small_flag:
                    if arr[small_index] > arr[key_index]:
                        arr[small_index], arr[key_index] = arr[key_index], arr[small_index]
                        key_index = small_index
                        compare_small_flag = False
                    else:
                        if small_index >= key_index:
                            compare_small_flag = False
                        small_index += 1
                else:
                    if arr[large_index] < arr[key_index]:
                        arr[large_index], arr[key_index] = arr[key_index], arr[large_index]
                        key_index = large_index
                        compare_small_flag = True
                    else:
                        if large_index <= key_index:
                            compare_small_flag = True
                        large_index -= 1
            self.quick_sort_inplace(arr, 0, key_index)
            if key_index < end-1:
                self.quick_sort_inplace(arr, key_index+1, end)

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
        以对应的间隔进行插入排序
        :param array:
        :param step:
        :return:
        """
        arr_len = len(array)
        for offset in range(step):
            i = 0
            key_index = i*step+offset
            while key_index < arr_len:
                key_val = array[key_index]
                j = key_index - step
                while j > 0:
                    if key_val < array[j]:
                        array[j+step] = array[j]
                        j -= step
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

    # below is heap sort func
    def adjust_heap(self, heap, this_index):
        l_child = this_index*2
        r_child = this_index*2+1
        father = this_index >> 1
        l_child_val = heap[l_child]
        r_child_val = heap[r_child]


    def heap_sorted(self, array):
        a = []
        a[:] = array[:]
        length = len(array)


# Test
if __name__ == "__main__":
    k = 12345
    z = SortFunc()
    for bit in range(1, 10):
        print(z.get_bit(k, bit=bit, radix=10))

