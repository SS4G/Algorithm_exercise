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

if __name__ == "__main__":
    s = Solution()
    board0 = [
    ['X', 'X', 'X', 'X',],
    ['X', 'O', 'O', 'X',],
    ['X', 'X', 'O', 'X',],
    ['X', 'O', 'X', 'X',],
    ]
    board1 = [
    ['X', 'X', 'X', 'X',],
    ['X', 'X', 'O', 'X',],
    ['X', 'O', 'O', 'X',],
    ['X', 'X', 'X', 'X',],
    ['X', 'O', 'O', 'X',],
    ['X', 'O', 'O', 'X',],
    ['X', 'O', 'X', 'X',],
    ]
    board2 = [
        ['O', 'O', 'O', 'O'],
        ['O', 'O', 'O', 'O'],
        ['O', 'O', 'O', 'O'],
        ['O', 'O', 'O', 'O'],
    ]
    changed = s.solve(board2)
    for line in changed:
        print("".join(line))