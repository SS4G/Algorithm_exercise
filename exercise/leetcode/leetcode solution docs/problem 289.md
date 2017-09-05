## 289. Game of Life

According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

- Any live cell with fewer than two live neighbors dies, as if caused by under-population.
- Any live cell with two or three live neighbors lives on to the next generation.
- Any live cell with more than three live neighbors dies, as if by over-population..
- Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state.

Follow up: 
Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?

#### tips
可以考虑使用正负号来记录信息 可以在原空间完成两次的转化

#### mycode

```
class Solution(object):
    def gameOfLife(self, board):
        """
        :type board: List[List[int]]
        :rtype: void Do not return anything, modify board in-place instead.
        """
        rL = len(board)
        if rL == 0:
            return []
        cL = len(board[0])
        newBoard = []

        for i in range(rL):
            for j in range(cL):
                if board[i][j] == 0:
                    board[i][j] = 2  # 1 live 2 dead

        for i in range(rL):
            newBoard.append([0, ]*cL)

        for i in range(rL):
            for j in range(cL):
                self.getNext(board, i, j, rL, cL)

        for i in range(rL):
            for j in range(cL):
                if board[i][j] > 0:
                    board[i][j] = 1
                else:
                    board[i][j] = 0
        return

    def getNext(self, board, r, c, rL, cL):
        liveCnt = 0
        adjs = self.getAdj(r, c, rL, cL)
        for adj in adjs:
            if abs(board[adj[0]][adj[1]]) == 1:
                liveCnt += 1
        if abs(board[r][c]) == 1:
            if liveCnt < 2:
                board[r][c] = -abs(board[r][c])
            elif 2 <= liveCnt <= 3:
                board[r][c] = abs(board[r][c])
            elif liveCnt > 3:
                board[r][c] = -abs(board[r][c])
            else:
                assert False, "invalid args"
        else:
            if liveCnt == 3:
                board[r][c] = abs(board[r][c])
            else:
                board[r][c] = -abs(board[r][c])

    def getAdj(self, r, c, rL, cL):
        rs = [r, ]
        cs = [c, ]
        if r + 1 < rL:
            rs.append(r + 1)
        if r - 1 >= 0:
            rs.append(r - 1)
        if c + 1 < cL:
            cs.append(c + 1)
        if c - 1 >= 0:
            cs.append(c - 1)
        adj = []
        for r0 in rs:
            for c0 in cs:
                if r0 == r and c0 == c:
                    continue
                adj.append((r0, c0))
        return adj

if __name__ == "__main__":
    s = Solution()
    board = [
        [1, 0, 1, 0],
        [1, 0, 1, 0],
        [1, 0, 1, 0],
        [1, 0, 1, 0],
    ]
    s.gameOfLife(board)
    for l in board:
        print(l)
```
