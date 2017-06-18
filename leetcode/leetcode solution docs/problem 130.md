## 130. Surrounded Regions Add to List

Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,

```
X X X X
X O O X
X X O X
X O X X
```

After running your function, the board should be:
```
X X X X
X X X X
X X X X
X O X X
```
#### tips
是一个典型的求连通性的问题 ， 可以用DFS BFS UF来做
DFS因为本题的特殊性， 使用递归会爆战 使用BFS可以做
但是bfs有一个要注意的小问题， 已经拉入到队列中的 点一定要标记 否则 这个点会被拉入好几次

- bfs 步骤

1. 访问当前点  
1. 将当前点的邻接点拉入队列  并标记 （如果不标记 会被已经在队列中的其他点再次拉入）
1. 直到队列中的所有点都被访问
1. 结束
1. note 标记表示的是已拉入队列 待访问 访问时将相邻节点拉入

#### mycode

```Python
class Solution(object):

    def solve(self, board):
        """
        :type board: List[List[str]]
        :rtype: void Do not return anything, modify board in-place instead.
        """
        rLength = len(board)
        if rLength == 0:
            return []
        cLength = len(board[0])

        for i in range(rLength):
            if board[i][0] == 'O':
                self.extendSideO(board, rLength, cLength, i, 0)
            if board[i][cLength-1] == 'O':
                self.extendSideO(board, rLength, cLength, i, cLength-1)

        for i in range(cLength):
            if board[0][i] == "O":
                self.extendSideO(board, rLength, cLength, 0, i)
            if board[rLength-1][i] == "O":
                self.extendSideO(board, rLength, cLength, rLength-1, i)

        for r in range(rLength):
            for c in range(cLength):
                if board[r][c] == 'O':
                    board[r][c] = 'X'
                elif board[r][c] == 'P':
                    board[r][c] = 'O'

        return board

    def getadJance(self, rLength, cLength, r, c):
        adjance = []
        if r + 1 < rLength:
            adjance.append((r + 1, c))
        if c + 1 < cLength:
            adjance.append((r, c + 1))
        if r - 1 >= 0:
            adjance.append((r - 1, c))
        if c - 1 >= 0:
            adjance.append((r, c - 1))
        return adjance

    def extendSideO(self, board, rL, cL, r, c):
        stack = []
        stack.append((r, c))
        board[r][c] = 'P'
        i = 0
        while i < len(stack):
            for pos in self.getadJance(rL, cL, stack[i][0], stack[i][1]):
                if board[pos[0]][pos[1]] == 'O':
                    board[pos[0]][pos[1]] = 'P'
                    stack.append((pos[0], pos[1]))
            i += 1
```
