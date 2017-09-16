class Solution(object):
    def cal_distance(self,pointa,pointb):
        """
        pointa :[x,y]
        pointb :[x,y]
        """
        return (pointa[0]-pointb[0])**2+(pointa[1]-pointb[1])**2

    def combine(self,x):
        if x<2:
            return 0
        else:
            return x*(x-1)//2                        
        
    def numberOfBoomerangs(self, points):
        """
        :type points: List[List[int]]
        :rtype: int
        """
        mat=[]
        length=len(points)
        for i in range(length):
            mat.append([0,]*length)       
        
        for i in range(length):
            for j in range(length):
                if i<=j :
                    mat[i][j]=self.cal_distance(points[i],points[j])
                    mat[j][i]=mat[i][j]    
        sum=0
        for i in range(length):
            mat[i].sort()
            print(mat[i])
            cur_cmp_val=mat[i][0]
            pre_index=0
            
            index=0
            while(index<length):
                if mat[i][index]!=cur_cmp_val:
                    cur_cmp_val=mat[i][index]
                    sum+=self.combine(index-pre_index)
                    pre_index=index 
                elif index==length-1:
                    cur_cmp_val=mat[i][index]
                    sum+=self.combine(index-pre_index+1)
                    pre_index=index
                index+=1
        return sum*2       
        


        
        
s=Solution()
a=[[0,0],[1,0],[2,0]]
a=[[0,0],[1,0],[-1,0],[0,1],[0,-1]]
print(s.numberOfBoomerangs(a))
