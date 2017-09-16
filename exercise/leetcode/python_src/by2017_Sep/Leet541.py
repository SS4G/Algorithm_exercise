class Solution:
    def reverse_part(self, list0, start, end):
        if len(list0) == 0:
            return
        i = start
        j = end-1
        while i < j:
            list0[i], list0[j] = list0[j], list0[i]
            i += 1
            j -= 1

    def reverseStr(self, s, k):
        """
        :type s: str
        :type k: int
        :rtype: str
        """
        list0 = list(s)
        length = len(list0)
        i = 0
        segment = 2*k
        while length - i > 2*k:
            self.reverse_part(list0, i, i+k)
            i += segment
        if length - i >= k:
            self.reverse_part(list0, i, i + k)
        else:
            self.reverse_part(list0, i, length)
        return "".join(list0)
if __name__ == '__main__':
    testcase = [
        ("abc")
    ]