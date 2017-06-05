class Solution(object):
    def nextGreaterElement(self, findNums, nums):
        """
        :type findNums: List[int]
        :type nums: List[int]
        :rtype: List[int]
        """
        length2 = len(nums)

        v_i_dict = {}
        for i in range(length2):
            v_i_dict[nums[i]] = i  # value:index
        res = []
        for j in findNums:
            index_in_nums = v_i_dict[j]  # get index of value to find
            if index_in_nums >= length2-1:  # last index
                res.append(-1)
                continue
            found_flag = False
            for index in range(index_in_nums+1, length2):
                if nums[index] > j:
                    res.append(nums[index])
                    found_flag = True
                    break
            if not found_flag:
                res.append(-1)
        return res

if __name__ == "__main__":
    s = Solution()
    nums1 = [4, 1, 2]
    nums2 = [1, 3, 4, 2]
    print(s.nextGreaterElement(nums1, nums2))
    nums1 = [2, 4]
    nums2 = [1, 2, 3, 4]
    print(s.nextGreaterElement(nums1, nums2))
