class Solution(object):
    def islandPerimeter(self, grid):
        """
        :type grid: List[List[int]]
        :rtype: int
        """
        height=len(grid)
        width=0
        sum=0;
        if height>0:
            width=len(grid[0])
        if height>0 and width>0:
            if height==1 and width==1:
                return 4
            else:           
                for i in range(height):
                    for j in range(width):
                        up      =False
                        down    =False
                        left    =False
                        right   =False
                        if grid[i][j]:
                            if i==0 :
                                sum+=1
                                up=True
                            if j==0:
                                sum+=1
                                left=True
                            if i==height-1:
                                sum+=1
                                down=True
                            if j==width-1:
                                sum+=1
                                right=True
                            if not up     and grid[i-1][j]==0:
                                sum+=1  
                            if not down   and grid[i+1][j]==0:
                                sum+=1    
                            if not left   and grid[i][j-1]==0:
                                sum+=1    
                            if not right  and grid[i][j+1]==0:
                                sum+=1
                return sum
        else:
            return 0
s=Solution()
grid0=[ [0,1,0,0],
        [1,1,1,0],
        [0,1,0,0],
        [1,1,0,0]]
print(s.islandPerimeter(grid0))
                                
                            
        
            
                
        
        