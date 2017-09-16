class Solution(object):
    def complexNumberMultiply(self, a, b):
        """
        :type a: str
        :type b: str
        :rtype: str
        """
        ac = self.get_real_and_img(a)
        bc = self.get_real_and_img(b)
        cc_r = ac[0]*bc[0]-ac[1]*bc[1]
        cc_i = ac[1]*bc[0]+ac[0]*bc[1]
        return str(cc_r)+"+"+str(cc_i)+"i"

    def get_real_and_img(self, complex_str):
        digits = "0123456789"
        real_neg_flag = False
        index = 0
        start_index = 0
        if complex_str[0] == "-":
            real_neg_flag = True
            index = 1
            start_index = 1
        if complex_str[0] == "+":
            real_neg_flag = False
            index = 1
            start_index = 1
        else:
            pass

        while complex_str[index] in digits:
            index += 1
        real = int(complex_str[start_index:index])
        real = -real if real_neg_flag else real



        index+=1
        start_index = index
        img_neg_flag = False
        if complex_str[index] == "-":
            img_neg_flag = True
            index += 1
            start_index = index
        if complex_str[0] == "+":
            img_neg_flag = False
            index += 1
            start_index = index
        else:
            pass

        while complex_str[index] in digits:
            index += 1
        img = int(complex_str[start_index:index])
        img = -img if img_neg_flag else img
        return (real,img)

if __name__ == "__main__":
    s = Solution()
    test_case = [
        ("1+2i", "3+4i"),
        ("1+-2i", "3+4i"),
        ("-1+-2i", "3+4i"),
                  ]
    for case in test_case:
        print(s.complexNumberMultiply(case[0], case[1]))
