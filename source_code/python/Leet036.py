class Solution(object):
    def isValidSudoku(self, board):
        """
        :type board: List[List[str]]
        :rtype: bool
        """
        for j in range(9):#horize
            for i in range(1,10):
                if board[j].count(str(i))>=2:
                    return False
        num_list=[]
        for j in range(9):#vertical
            for i in range(9):
                print(i,j)
                if (board[i][j] not in num_list) and board[i][j]!='.':
                    num_list.append(board[i][j])
                else:
                    return False
        print("x")           
        for block in range(9):
            x_block=block%3#0,1,2
            y_block=block//3#0,1,2
            num_list=[]
            for i in range(y_block*3,(y_block+1)*3):
                for j in range(x_block*3,(x_block+1)*3):
                    if board[i][j] not in num_list:
                        num_list.append(board[i][j])
                    elif board[i][j]=='.':
                        continue
                    else:
                        return False
           
        return True           
                 
s=Solution()
print(s.isValidSudoku([".87654321","2........","3........","4........","5........","6........","7........","8........","9........"]))                 
                        
                        