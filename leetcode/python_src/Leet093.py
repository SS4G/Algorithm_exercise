class Solution(object):
    def restoreIpAddresses(self, s):
        """
        :type s: str
        :rtype: List[str]
        """
        if len(s) < 4:
            return []
        stack = []
        output = []
        self.restoreRest(s, 1, stack, output)
        distinct = set([])
        for l in output:
            distinct.add(tuple(l))
        distinct = [[str(c) for c in list(item)] for item in distinct]
        return [".".join(item) for item in distinct]

    def restoreRest(self, s, step, stack, output):
        if step == 4:  # last part of the IP
            if len(s) > 3 or len(s) <= 0:  # length invalid in the final step
                return
            if s[0] == '0' and len(s) > 1:
                return
            if int(s) > 255:
                return
            else:
                stack.append(s)
                output.append([int(i) for i in stack])
                stack.pop()
        else:
            if len(s) <= 0:
                return
            splitLim = min(4, len(s)+1) if s[0] != '0' else 2
            for splitSize in range(1, splitLim):
                i = int(s[:splitSize])
                if int(i) <= 255:
                    stack.append(s[:splitSize])
                    self.restoreRest(s[splitSize:], step+1, stack, output)
                    stack.pop()
                else:
                    return

if __name__ == "__main__":
    s = Solution()
    print(s.restoreIpAddresses("010010"))
    # Output:
    # ["0.1.0.010", "0.1.00.10", "0.1.001.0", "0.10.0.10", "0.10.01.0", "0.100.1.0", "01.0.0.10", "01.0.01.0",
    #  "01.00.1.0", "010.0.1.0"]
    # Expected:
    # ["0.10.0.10", "0.100.1.0"]
