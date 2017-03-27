class Solution(object):
    def detectCapitalUse(self, word):
        """
        :type word: str
        :rtype: bool
        """
        # 使用状态机
        # 对于长度为 1 2 的边界情况 也成立
        is_up = lambda c: True if ord('A') <= ord(c) <= ord('Z') else False

        IDLE = -1
        FIRST_UP = 0
        ALL_UP = 1
        ALL_LOW = 2
        state = IDLE
        for i in word:
            if state == IDLE:
                if is_up(i):
                    state = FIRST_UP
                else:
                    state = ALL_LOW
            elif state == FIRST_UP:
                if is_up(i):
                    state = ALL_UP
                else:
                    state = ALL_LOW
            elif state == ALL_UP:  # 之后全部应该是大写
                if not is_up(i):
                    return False
            elif state == ALL_LOW:  # 之后应该全部是小写
                if is_up(i):
                    return False
        return True


