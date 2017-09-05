class Solution(object):
    def validUtf8(self, data):
        """
        :type data: List[int]
        :rtype: bool
        """

        i = 0
        while i < len(data):
            num = data[i]
            types = self.judge(num)
            if types == 6:
                return False
            # print("i=", i, " types=", types)
            j = 1
            while j < types:
                if i+j >= len(data):
                    return False
                num = data[i+j]
                if self.judge(num) != 5:
                    return False
                j += 1
            i = i + types
        return True

    def judge(self, x):
        x &= 0xff
        FIRSTMASK_2B = 0xc0
        FIRSTMASK_3B = 0xe0
        FIRSTMASK_4B = 0xf0
        SECONDMASK = 0x80
        #print("bin = ", bin(x), x)
        if x & 0x80 == 0:
            #print("type = 1")
            return 1  # 1byte 1st
        if x & 0xe0 == FIRSTMASK_2B:
            #print("type = 2")
            return 2  # 2byte 1st
        if x & 0xf0 == FIRSTMASK_3B:
            #print("type = 3")
            return 3  # 3byte 1st
        if x & 0xf8 == FIRSTMASK_4B:
            #print("type = 4")
            return 4  # 4byte 1st
        if x & 0xc0 == SECONDMASK:
            #print("type = 5")
            return 5  # nbyte start
        #print("type = 6")
        return 6 # unknow type

if __name__ == "__main__":
    s = Solution()
    data = [39, 89, 227, 83, 132, 95, 10, 0]
    # print(s.judge(227))
    print(s.validUtf8(data))
