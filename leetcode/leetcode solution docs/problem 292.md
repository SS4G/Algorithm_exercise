# leetcode 292
## Question
####  Nim Game
You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. You will take the first turn to remove the stones.
Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you can win the game given the number of stones in the heap.
For example, if there are 4 stones in the heap, then you will never win the game: no matter 1, 2, or 3 stones you remove, the last stone will always be removed by your friend.


## Answer
可以列出轮到我时还剩余的石子数来找规律
1. win
2. win
3. win
4. can't win
5. can goto 4 let friend fail  so win
6. can goto 4 let friend fail  so win
7. can goto 4 let friend fail  so win
8. can't win  不管怎么操作对方都会调到4，5，6  所以必输
9.  不管怎么操作都可以使对方调到8 对方必输
1.  不管怎么操作都可以使对方调到8 对方必输
1.  不管怎么操作都可以使对方调到8 对方必输
1.  不管怎么操作对方都会调到9，10，11  所以必输
。。。
所以 规律是 被4整除必输 反之必胜


#####  code
（code beat 70%）




```Python
class Solution(object):
    def canWinNim(self, n):
        """
        :type n: int
        :rtype: bool
        """
        if n&0x03 :#求 %4使用与运算是为了替代取余除法运算提升效率 对求2，4，8，  
            return True #16，。。。余数有效
        else :
            return False
```


