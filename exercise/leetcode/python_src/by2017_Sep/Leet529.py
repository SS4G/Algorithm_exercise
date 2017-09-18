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