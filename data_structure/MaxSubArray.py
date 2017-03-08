# coding=utf-8
class MaxSubArray:
    "最大和子序列的和求解"
    def __init__(self):
        pass

    def maxsubarray0(self, target_list):
        """
        穷举法(TLE)
        complexity:O(n^3)
        :param target_list:
        :return: tmp_sum of max sub array
        """
        current_max = -2**32  # set as min integer
        tmp_sum = 0
        length = len(target_list)
        for start in range(0, length):  # [0,N-1]
            for end in range(start, length):  # [start,N-1]
                for index in range(start, end+1):  # [start,end]
                    tmp_sum += target_list[index]

                if current_max <= tmp_sum:
                    current_max = tmp_sum
                tmp_sum = 0  # remember to clear result after each iteration
        return current_max

    def maxsubarray1(self, target_list):
        """
        累加法(TLE)
        complexity:O(n^3)
        :param target_list:
        :return: tmp_sum of max sub array
        """
        current_max = -2**32  # set as min integer
        tmp_sum = 0
        length = len(target_list)
        for start in range(0, length):  # [0,N-1]
            tmp_sum = 0  # remember to clear result after each iteration
            for end in range(start, length):  # [start,N-1]
                tmp_sum += target_list[end]  # 使用相对累加的方法求子序列的和 基于上一次的基础
                if current_max <= tmp_sum:
                    current_max = tmp_sum
        return current_max

    def maxsubarray2(self, target_list):
        """
        分治法(beat 5%)
        complexity:O(n*log(n))
        :param target_list:
        :return: tmp_sum of max sub array
        """
        length = len(target_list)
        if length == 0:
            return None

        if length == 1:     # if the size of target list is small calculate sum directly.
            return target_list[0]  # This value can greater than 1 or 2
        elif length == 2:
            return max(target_list[0], target_list[1], sum(target_list))
        else:
            middle_index = length >> 1
            left_list = target_list[:middle_index+1]
            right_list = target_list[middle_index+1:]

            # if max sub array contains element[middle_index]
            max_left_sum = -(1 << 32)
            max_right_sum = -(1 << 32)
            tmp_sum = 0
            left_index = middle_index
            right_index = middle_index+1
            while left_index >= 0:  # calculate max middle left sum must include element[middle_index]
                tmp_sum += target_list[left_index]
                left_index -= 1
                max_left_sum = tmp_sum if max_left_sum < tmp_sum else max_left_sum

            tmp_sum = 0
            while right_index < length:  # calculate max middle left sum must include element[middle_index]
                tmp_sum += target_list[right_index]
                right_index += 1
                max_right_sum = tmp_sum if max_right_sum < tmp_sum else max_right_sum
            max_middle_sum = max_left_sum + max_right_sum

            return max(self.maxsubarray2(left_list), self.maxsubarray2(right_list), max_middle_sum)

    def maxsubarray3(self, target_list):
        """
        (beat 77%)
        线性移动的方法--
        complexity:O(n)
        :param target_list:
        :return: tmp_sum of max sub array
        """
        this_sum = 0
        max_sum = -(1 << 32)
        max_val = max(target_list)
        if max_val < 0:
            return max_val

        length = len(target_list)
        for i in range(length):
            this_sum += target_list[i]
            if this_sum <= 0:
                this_sum = 0
            if max_sum < this_sum:
                max_sum = this_sum
        return max_sum

# Test
if __name__ == "__main__":
    t = MaxSubArray()
    testcase = [
        ([-2, 1, -3, 4, -1, 2, 1, -5, 4], 6),
        ([1, ], 1),
        ([-2, 1], 1),
        ([1, 2, -1], 3),
        ([-1, ], -1)
    ]
    for case in testcase:
        assert t.maxsubarray0(case[0]) == case[1], "result ERROR!  your result is %d require %d " \
                                                 % (t.maxsubarray0(case[0]), case[1])
    for case in testcase:
        assert t.maxsubarray1(case[0]) == case[1], "result ERROR!  your result is %d require %d " \
                                                 % (t.maxsubarray1(case[0]), case[1])
    for case in testcase:
        assert t.maxsubarray2(case[0]) == case[1], "result ERROR!  your result is %d require %d " \
                                                 % (t.maxsubarray2(case[0]), case[1])
    for case in testcase:
        assert t.maxsubarray3(case[0]) == case[1], "result ERROR!  your result is %d require %d " \
                                                 % (t.maxsubarray3(case[0]), case[1])

    print("test process terminate successfully!")


