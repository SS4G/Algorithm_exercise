class Solution(object):
    def decodeString(self, s):
        """
        :type s: str
        :rtype: str
        """
        stack = []
        tobeMul = []
        i = 0
        while i < len(s):
            if self.isDigit(s[i]):
                stack.append(s[i])
            elif s[i] == '[':
                stack.append(s[i])
            elif s[i] == ']':
                GET_STR = 1
                GET_AMOUNT = 2
                QUIT = 3
                state = GET_STR
                tobeMul = []
                repeatTimeLi = []
                while state != QUIT:
                    if state == GET_STR:
                        pops = stack.pop()
                        if pops != '[':
                            tobeMul.append(pops)
                        else:
                            state = GET_AMOUNT
                    elif state == GET_AMOUNT:
                        if len(stack) > 0 and self.isDigit(stack[-1]):
                            repeatTimeLi.append(stack.pop())
                        else:
                            repeatTimeLi.reverse()
                            tobeMul.reverse()
                            stack.extend(tobeMul*int("".join(repeatTimeLi)))
                            state = QUIT
            else:
                stack.append(s[i])
            i += 1
        return "".join(stack)

    def isDigit(self, x):
        return x in "0123456789"

if __name__ == "__main__":
    s = Solution()
    print(s.decodeString("10[a]2[bc]"))
    print(s.decodeString("abdf5[a]2[bc]"))
    print(s.decodeString("10[a]2[bc]"))
    print(s.decodeString("10[a]ccc2[b5[kvl]c]"))
    print(s.decodeString("abcdefgh"))
    print(s.decodeString("a"))
    print(s.decodeString(""))