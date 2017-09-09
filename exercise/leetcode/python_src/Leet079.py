class Solution(object):
    def exist(self, board, word):
        """
        :type board: List[List[str]]
        :type word: str
        :rtype: bool
        """
        rL = len(board)
        if rL == 0:
            return False
        cL = len(board[0])
        marked = []
        for i in range(rL):
            marked.append([False, ]*cL)
        flag = False
        for r in range(rL):
            for c in range(cL):
                if board[r][c] == word[0]:
                    flag = flag or self.dfsHelper(r, c, rL, cL, board, word, 0, marked)
        return flag

    def dfsHelper(self, r, c, rL, cL, board, word, charIndex, marked):
        if not marked[r][c]:
            if board[r][c] == word[charIndex]:
                marked[r][c] = True  # if the char is on the path
                if charIndex == len(word)-1:
                    return True
                adjs = self.getAdj(r, c, rL, cL)
                foundTarget = False
                for adj in adjs:
                    foundTarget = foundTarget or self.dfsHelper(adj[0], adj[1], rL, cL, board, word, charIndex+1, marked)
                marked[r][c] = False
                return foundTarget
            else:
                return False
        else:
            return False

    def getAdj(self, r, c, rL, cL):
        rs = []
        cs = []
        if r + 1 < rL:
            rs.append(r + 1)
        if r - 1 >= 0:
            rs.append(r - 1)
        if c + 1 < cL:
            cs.append(c + 1)
        if c - 1 >= 0:
            cs.append(c - 1)
        adj = []
        for rx in rs:
            adj.append((rx, c))
        for cx in cs:
            adj.append((r, cx))
        return adj

if __name__ == "__main__":
    s = Solution()
    board = [
      ['A', 'B', 'C', 'E'],
      ['S', 'F', 'C', 'S'],
      ['A', 'D', 'E', 'E']
    ]
    r = s.exist(board, "ABCCED")
    r = s.exist(board, "ABCA")
    # r = s.exist(board, "DEE")
    print(r)