# leetcode
## Question
#### Bulls and Cows
You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.

For example:

Secret number:  "1807"
Friend's guess: "7810"
Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. In the above example, your function should return "1A3B".

Please note that both secret number and friend's guess may contain duplicate digits, for example:

Secret number:  "1123"
Friend's guess: "0111"
In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, and your function should return "1A1B".
You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
## Answer
主要是先分别统计guess 和 secret 中 0~9的数量讲这些数量按照索引放在字典中
然后同时以相同的索引来遍历guess 和secret
如果发现在相同的位置上有相同的数字
那么就分别从对应的两个字典的对应数字上减去1
总共减去的次数即为bulls
两个字典中剩下的内容说明就是位置不匹配的数字 然后取两者中较小者即为同时存在但数量不匹配的情况,求其和即为最终的cows
##### code
```
class Solution(object):
    def getHint(self, secret, guess):
        """
        :type secret: str
        :type guess: str
        :rtype: str
        """
        sec_dict={}
        gue_dict={}
        sec_l=list(secret)
        gue_l=list(guess)
        bull=0 
        cow=0
        for j in "0123456789":
            sec_dict[j]=sec_l.count(j)
            gue_dict[j]=gue_l.count(j)
        length=len(guess)
        for i in range(length):
            if secret[i]==guess[i]:
                bull+=1
                sec_dict[secret[i]]-=1
                gue_dict[secret[i]]-=1
        
        for i in "0123456789":
            cow+=gue_dict[i] if sec_dict[i]>gue_dict[i] else sec_dict[i]
        return str(bull)+"A"+str(cow)+"B"
```
