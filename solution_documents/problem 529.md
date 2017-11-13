## 529. Minesweeper Add to List

Let's play the minesweeper game (Wikipedia, online game)!

You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.

Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this position according to the following rules:

If a mine ('M') is revealed, then the game is over - change it to 'X'.
If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
Return the board when no more squares will be revealed.
Example 1:

```
Input: 

[['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'M', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E']]
```

Click : [3,0]


```
Output: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]
```

Explanation:
![img](https://leetcode.com/static/images/problemset/minesweeper_example_1.png)
Example 2:

```
Input: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]
```

Click : [1,2]


```
Output: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'X', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]
```


Explanation:
![img](https://leetcode.com/static/images/problemset/minesweeper_example_2.png)
#### Note:
- The range of the input matrix's height and width is [1,50].
- The click position will only be an unrevealed square ('M' or 'E'), which also means the input board contains at least one clickable square.
- The input board won't be a stage when game is over (some mines have been revealed).
- For simplicity, not mentioned rules should be ignored in this problem. For example, you don't need to reveal all the unrevealed mines when the game is over, consider any cases that you will win the game or flag any squares.

 
#### tips 
主要的难点在于如何去遍历相邻的空格 使用深度搜索就行了 搜索过的就肯定不是‘E’了
专门使用一个函数产生一个可迭代的列表 可以自动排除讨厌的越界情况 这个方法很好

#### mycode

```Python
class Solution(object):
    def updateBoard(self, board, click):
        """
        :type board: List[List[str]]
        :type click: List[int]
        :rtype: List[List[str]]
        """
        if not board:
            return []

        cr = click[0]
        cc = click[1]
        if board[cr][cc] == 'M':
            board[cr][cc] = 'X'
            return board
        if board[cr][cc] == 'E':
            adjList = self.getAdjance(board, cr, cc)
            # print("this=", cr, cc)
            # print(adjList)
            adjMineAmount = self.getMineAmount(board, adjList)
            if board[cr][cc] == "E" and adjMineAmount == 0:
                board[cr][cc] = 'B'
                for adj in adjList:
                    if board[adj[0]][adj[1]] == "E":
                        self.updateBoard(board, adj)
            elif board[cr][cc] == "E":
                board[cr][cc] = str(adjMineAmount)
            elif board[cr][cc] == 'B':
                pass
            else:
                assert False, "can't reach here"
            return board

    def getMineAmount(self, board, adjList):
        cnt = 0
        for adj in adjList:
            adr = adj[0]
            adc = adj[1]
            if board[adr][adc] == "M":
                cnt += 1
        return cnt

    def getAdjance(self, board, r, c):
        # assume board is nort empty
        totalR = len(board)
        totalC = len(board[0])
        up = r-1
        down = r+1
        left = c-1
        right = c+1
        res = []
        if up >= 0 and left >= 0:
            res.append([up, left])
        if up >= 0 and right < totalC:
            res.append([up, right])
        if up >= 0:
            res.append([up, c])

        if down < totalR and left >= 0:
            res.append([down, left])
        if down < totalR and right < totalC:
            res.append([down, right])
        if down < totalR:
            res.append([down, c])

        if left >= 0:
            res.append([r, left])
        if right < totalC:
            res.append([r, right])

        return res

if __name__ == "__main__":
    boards = ["EEEEEEEE","EEEEEEEM","EEMEEEEE","MEEEEEEE","EEEEEEEE","EEEEEEEE","EEEEEEEE","EEMMEEEE"]
    board = []
    for l in boards:
        board.append(list(l))
    s = Solution()
    click = [0, 0]

    adj = s.getAdjance(board, 2, 1)
    print(adj)

    res = s.updateBoard(board, click)



    for line in res:
        print(line)
```
