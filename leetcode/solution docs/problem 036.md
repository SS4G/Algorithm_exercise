# leetcode 36
## Question
#### Valid Sudoku
Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

![ii](http://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Sudoku-by-L2G-20050714.svg/250px-Sudoku-by-L2G-20050714.svg.png)\

A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
## Answer
分为三个方向遍历
横向
纵向
大格遍历
简单就不多说了
##### code

```
class Solution(object):
    def isValidSudoku(self, board):
        """
        :type board: List[List[str]]
        :rtype: bool
        """
        num_list=[]
        #vertical judge
        for i in range(9):
            num_list=[]
            for j in range(9):
                if (board[i][j] not in num_list) and (board[i][j]!='.'):
                    num_list.append(board[i][j])
                elif  (board[i][j]=='.'):                   
                    continue
                else :
                    return False 
              
        #horizon judge
        num_list=[]
        for j in range(9):
            num_list=[]
            for i in range(9):
                if (board[i][j] not in num_list) and (board[i][j]!='.'):
                    num_list.append(board[i][j])
                elif  (board[i][j]=='.'):
                    continue
                else :
                    return False                
        #block judge
        
        for b in range(9):
            x=b%3
            y=b//3
            num_list=[]
            for i in range(y*3,(y+1)*3):
                for j in range(x*3,(x+1)*3):
                    if (board[i][j] not in num_list) and (board[i][j]!='.'):
                        num_list.append(board[i][j])
                    elif  (board[i][j]=='.'):
                        continue
                    else :
                        return False            
        
        return True
```

