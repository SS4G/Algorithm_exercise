class Solution(object):
    def findWords(self, words):
        """
        :type words: List[str]
        :rtype: List[str]
        """
        letter_dict={}
        line2="asdfghjkl"
        line2_g="ASDFGHJKL"

        line1_g="QWERTYUIOP"
        line1="qwertyuiop"

        line3_g="ZXCVBNM"
        line3="zxcvbnm"
        res_word_list = []
        for c in line1:
            letter_dict[c] = 1
        for c in line1_g:
            letter_dict[c] = 1
        for c in line2:
            letter_dict[c] = 2
        for c in line2_g:
            letter_dict[c] = 2
        for c in line3:
            letter_dict[c] = 3
        for c in line3_g:
            letter_dict[c] = 3

        for word in words:
            line_flag = 0
            judge_flag = True
            for letter in word:
                res = letter_dict[letter]
                if res != line_flag and line_flag != 0:
                    judge_flag = False
                    break
                elif line_flag == 0 and res != line_flag:
                    line_flag = res
                else:
                    line_flag = res
            if judge_flag :
                res_word_list.append(word)
        return res_word_list
s = Solution()
inputstr = ["Hello", "Alaska", "Dad", "Peace"]
print(s.findWords(inputstr))
