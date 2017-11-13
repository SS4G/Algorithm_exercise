## 053. Maximum Subarray
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.
#### tip 
各种复杂度的解法
尤其注意第四种解法中对于序列中全部是负数情况的考虑
注意不能使用极差的方法来做这个问题 因为算极差能满足的情况必须同时保证
极小值出现在极大值的左侧才可以
#### mycode

```
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
        
        第一种情况是总序列中的所有值全是负值 直接选取其中的最大值即可
        
        这样来理解：
        假设序列中的最大子序列为 full_arrsy[max_l:max_r+1]
        max_l max_r 分别为最大子序列的左边界和右边界 （包含这两点）
        那么因为是最大子序列 所以 所有以 max_l-1
        为右边界的序列之和都为负 
        所有以max_r+1 为左边界的子序列之和都为负
        
        现在的问题是 我们不知到具体的最大子序列在哪里
        但是我们如果从右向左使用this_sum来当做一个子序列的和的时候
        只要按照下面代码的逻辑 this_sum为负就切换起点的话,
        肯定能够保证真正的最大子序列的起点肯定会被作为一个this_sum的
        起点 因为只要this_sum一旦碰到其值为负就会使用下一个点作为新的
        求和子序列的起点
        
        当然即使不是最大子序列max_l-1 this_sum
        也会碰到这样的情况，但是既然肯定会经过最大子序列的情况 我们就用max_sum来跟踪最大子序列 这样就可以获得最终结果
        
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
```
