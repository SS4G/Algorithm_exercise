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
