# leetcode 409
## Question
#### Longest Palindrome
Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

```
Example:

Input:
"abccccdd"

Output:
7
```

- Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.
Subscribe to see which companies asked this question

## Answer
用字典 没什么好说的
##### code (beat 78%)

```
class Solution(object):
    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: int
        """
        letter_dict={}
        ori="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        for i in ori:
            letter_dict[i]=0
        #letter_dict is buileded up 
        for i in s:
            letter_dict[i]+=1
            
        all_even=True
        length=0
        for i in letter_dict:
            if letter_dict[i]&0x01:#odd
                all_even=False
                length+=letter_dict[i]-1
            else:
                length+=letter_dict[i]
        if all_even:
            return length 
        else:
            return length+1
```
